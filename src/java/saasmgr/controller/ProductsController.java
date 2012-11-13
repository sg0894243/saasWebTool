package saasmgr.controller;

import saasmgr.entity.Products;
import saasmgr.controller.util.JsfUtil;
import saasmgr.controller.util.PaginationHelper;
import saasmgr.dao.ProductsFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

@Named("productsController")
@SessionScoped
public class ProductsController implements Serializable {

    private Products current;
    private DataModel items = null;    
    @EJB
    private saasmgr.dao.ProductsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Products[] selectedItems;
    private static final Logger logger = Logger.getLogger(
            "saasmgr.controller.peopleController");
    private int mngBean_ID;
    private List<SelectItem> colTowers;
    
    public ProductsController() {
        mngBean_ID = JsfUtil.getNextInt();
        colTowers = null;
        logger.log(Level.INFO, "[{0}" + "] " + "Initializing the Bean ProductsController with new ID", mngBean_ID);
    }
    
    public List<SelectItem> getColTowers() {
        if (colTowers == null) {
            colTowers = new ArrayList<SelectItem>();
            colTowers.add(new SelectItem("", "Select"));
            String[] towers = {"AirCenter","AirVision","SabreSonic","Leveraged"};
            for (int i = 0; i < towers.length; i++) {
                logger.info(towers[i]);
                colTowers.add(new SelectItem(towers[i]));
            }
        }
        return colTowers;
    }
    
    public Products[] getSelectedItems() {
        return selectedItems;
    }
    
    public void setSelectedItems(Products[] selectedItems) {
        this.selectedItems = selectedItems;
    }
    
    public Products getSelected() {
        if (current == null) {
            current = new Products();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ProductsFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public void prepareList() {
        recreateModel();
    }

    public void prepareView() {
        current = (Products) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
    }

    public void prepareCreate() {
        current = new Products();
        selectedItemIndex = -1;        
    }

    public void create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductsCreated"));
            prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public void prepareEdit() {
        current = (Products) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
    }

    public void update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductsUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public void destroy() {
        current = (Products) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
    }

    public void destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        recreateModel();
    }

    private void performDestroy() {
        try {
            for (Products p : selectedItems) {
                logger.log(Level.INFO, "[{0}" + "] " + "performDestroy being called for item " + p.getShortName(), mngBean_ID);
                getFacade().remove(p);
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductsDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public void next() {
        getPagination().nextPage();
        recreateModel();
    }

    public void previous() {
        getPagination().previousPage();
        recreateModel();
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public void onEdit(RowEditEvent event) {
        try {
            logger.log(Level.INFO, "[{0}" + "] " + "onEdit being called", mngBean_ID);
            getFacade().edit((Products) event.getObject());

            //return "View";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "[{0}" + "] " + "onEdit error... " + e.getMessage(), mngBean_ID);
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            prepareList();
            return;
            //return null;
        }
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductsUpdated"));
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Employee Cancelled", (((Products) event.getObject()).getIdProduct()).toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    @FacesConverter(forClass = Products.class)
    public static class ProductsControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProductsController controller = (ProductsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "productsController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Products) {
                Products o = (Products) object;
                return getStringKey(o.getIdProduct());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Products.class.getName());
            }
        }
    }
}
