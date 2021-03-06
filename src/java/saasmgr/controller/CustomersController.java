package saasmgr.controller;

import saasmgr.entity.Customers;
import saasmgr.controller.util.JsfUtil;
import saasmgr.controller.util.PaginationHelper;
import saasmgr.dao.CustomersFacade;

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

@Named("customersController")
@SessionScoped
public class CustomersController implements Serializable {

    private Customers current;
    private DataModel items = null;
    @EJB
    private saasmgr.dao.CustomersFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Customers[] selectedItems;
    private static final Logger logger = Logger.getLogger(
            "saasmgr.controller.peopleController");
    private int mngBean_ID;
    private List<SelectItem> colRegions;    
            

    public CustomersController() {
        mngBean_ID = JsfUtil.getNextInt();
        colRegions = null;
        logger.log(Level.INFO, "[{0}" + "] " + "Initializing the Bean CustomersController with new ID", mngBean_ID);
    }

    public Customers[] getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(Customers[] selectedItems) {
        this.selectedItems = selectedItems;
    }
    
    public List<SelectItem> getColRegions() {
        if (colRegions == null) {
            colRegions = new ArrayList<SelectItem>();
            colRegions.add(new SelectItem("", "Select"));
            String[] regions = {"North America","South and Central America","Europe, Africa and Asia"};
            for (int i = 0; i < regions.length; i++) {
                logger.info(regions[i]);
                colRegions.add(new SelectItem(regions[i]));
            }
        }
        return colRegions;
    }

    public Customers getSelected() {
        if (current == null) {
            current = new Customers();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CustomersFacade getFacade() {
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
        logger.log(Level.INFO, "[{0}" + "] " + "prepareList being called", mngBean_ID);
        recreateModel();
    }

    public void prepareView() {
        logger.log(Level.INFO, "[{0}" + "] " + "prepareView being called", mngBean_ID);
        current = (Customers) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
    }

    public void prepareCreate() {
        logger.log(Level.INFO, "[{0}" + "] " + "prepareCreate being called", mngBean_ID);
        current = new Customers();
        selectedItemIndex = -1;
    }

    public void create() {
        try {
            getFacade().create(current);
            prepareList();
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CustomersCreated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public void prepareEdit() {
        logger.log(Level.INFO, "[{0}" + "] " + "prepareEdit being called", mngBean_ID);
        current = (Customers) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
    }

    public void update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CustomersUpdated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public void destroy() {
        current = (Customers) getItems().getRowData();
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
            for (Customers c : selectedItems) {
                logger.log(Level.INFO, "[{0}" + "] " + "performDestroy being called for item " + c.getCustomerCode(), mngBean_ID);
                getFacade().remove(c);
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CustomersDeleted"));
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

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
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
            getFacade().edit((Customers) event.getObject());

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
        FacesMessage msg = new FacesMessage("Customer Cancelled", (((Customers) event.getObject()).getCustomerCode()).toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    @FacesConverter(forClass = Customers.class)
    public static class CustomersControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CustomersController controller = (CustomersController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "customersController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Customers) {
                Customers o = (Customers) object;
                return getStringKey(o.getCustomerCode());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Customers.class.getName());
            }
        }
    }
}
