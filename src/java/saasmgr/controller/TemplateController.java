package saasmgr.controller;
import saasmgr.controller.util.JsfUtil;
import saasmgr.controller.util.PaginationHelper;
import saasmgr.dao.AbstractFacade;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.event.RowEditEvent;

public abstract class TemplateController {

    protected Object current;
    private DataModel items = null;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Object[] selectedItems;
    protected int mngBean_ID;
    protected static Logger logger;
    private String beanName;
            

    public TemplateController(String loggerName, String beanName) {
        this.beanName = beanName;
        mngBean_ID = JsfUtil.getNextInt();
        logger = Logger.getLogger(loggerName);
        logger.log(Level.INFO, "[{0}" + "] " + "Initializing the "+beanName+"Controller Bean with new ID", mngBean_ID);
    }
    
    public TemplateController() {
        this.beanName = "Projects";
        mngBean_ID = JsfUtil.getNextInt();
        logger = Logger.getLogger("saasmgr.controller.templateController");
        logger.log(Level.INFO, "[{0}" + "] " + "Initializing a Controller Bean with new ID", mngBean_ID);
    }
    
    protected abstract Object getNewObject();

    public Object[] getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(Object[] selectedItems) {
        this.selectedItems = selectedItems;
    }

    public Object getSelected(){
     if (current == null) {
            current = getNewObject();
        }
        return current;   
    }

    protected abstract AbstractFacade getFacade();

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
        current = getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
    }

    public void prepareCreate()
    {
        logger.log(Level.INFO, "[{0}" + "] " + "prepareCreate being called", mngBean_ID);
        current = getNewObject();
        //current.setEnv("Int");
    }

    public void create() {
        try {
            getFacade().create(current);
            prepareList();
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString(beanName + "Created"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public void prepareEdit() {
        logger.log(Level.INFO, "[{0}" + "] " + "prepareEdit being called", mngBean_ID);
        current = getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
    }

    public void update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString(beanName + "Updated"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public void destroy() {
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
            for (Object o : selectedItems) {
                logger.log(Level.INFO, "[{0}" + "] " + "performDestroy being called for one item", mngBean_ID);
                getFacade().remove(o);
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString(beanName + "Deleted"));
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
        return JsfUtil.getSelectItems(getFacade().findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getFacade().findAll(), true);
    }

    public void onEdit(RowEditEvent event) {
        try {
            logger.log(Level.INFO, "[{0}" + "] " + "onEdit being called", mngBean_ID);
            getFacade().edit(event.getObject());

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
        FacesMessage msg = new FacesMessage("Customer Cancelled");

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
