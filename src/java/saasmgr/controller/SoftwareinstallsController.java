package saasmgr.controller;

import saasmgr.entity.Softwareinstalls;
import saasmgr.controller.util.JsfUtil;
import saasmgr.controller.util.PaginationHelper;
import saasmgr.dao.SoftwareinstallsFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("softwareinstallsController")
@SessionScoped
public class SoftwareinstallsController implements Serializable {

    private Softwareinstalls current;
    private DataModel items = null;
    @EJB
    private saasmgr.dao.SoftwareinstallsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public SoftwareinstallsController() {
    }

    public Softwareinstalls getSelected() {
        if (current == null) {
            current = new Softwareinstalls();
            current.setSoftwareinstallsPK(new saasmgr.entity.SoftwareinstallsPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private SoftwareinstallsFacade getFacade() {
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

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Softwareinstalls) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Softwareinstalls();
        current.setSoftwareinstallsPK(new saasmgr.entity.SoftwareinstallsPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SoftwareinstallsCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Softwareinstalls) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SoftwareinstallsUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Softwareinstalls) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SoftwareinstallsDeleted"));
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

    @FacesConverter(forClass = Softwareinstalls.class)
    public static class SoftwareinstallsControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SoftwareinstallsController controller = (SoftwareinstallsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "softwareinstallsController");
            return controller.ejbFacade.find(getKey(value));
        }

        saasmgr.entity.SoftwareinstallsPK getKey(String value) {
            saasmgr.entity.SoftwareinstallsPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new saasmgr.entity.SoftwareinstallsPK();
            key.setIdSoftware(Integer.parseInt(values[0]));
            key.setHostname(values[1]);
            return key;
        }

        String getStringKey(saasmgr.entity.SoftwareinstallsPK value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value.getIdSoftware());
            sb.append(SEPARATOR);
            sb.append(value.getHostname());
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Softwareinstalls) {
                Softwareinstalls o = (Softwareinstalls) object;
                return getStringKey(o.getSoftwareinstallsPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Softwareinstalls.class.getName());
            }
        }
    }
}
