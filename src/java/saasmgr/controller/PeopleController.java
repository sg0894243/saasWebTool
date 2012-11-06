package saasmgr.controller;

import saasmgr.entity.People;
import saasmgr.controller.util.JsfUtil;
import saasmgr.controller.util.PaginationHelper;
import saasmgr.dao.PeopleFacade;

import java.io.Serializable;
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
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@Named("peopleController")
@SessionScoped
public class PeopleController implements Serializable {

    private static final Logger logger = Logger.getLogger(
            "saasmgr.controller.peopleController");
    private int mngBean_ID;
    //private People current;
    private People selectedEmployee;
    private DataModel items = null;
    @EJB
    private saasmgr.dao.PeopleFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private boolean isForEditing;
    private boolean noRowSelected;
    private List<People> filteredPeople;

    private final static String[] roles;
    static {
        roles = new String[2];
        roles[0] = "Architect";
        roles[1] = "Project Manager";
    }
    public String[] getRoles() {
        return roles;
    }
    
    public List<People> getFilteredPeople() {
        return filteredPeople;
    }

    public void setFilteredPeople(List<People> filteredPeople) {
        this.filteredPeople = filteredPeople;
    }

    public int getMaxIDEmployees()
    {
        int result = 0;
        try
        {
            result = ejbFacade.getMaxID();
        }
        catch(Exception e)
        {
            logger.log(Level.INFO,"[{0}" + "] " + "Error llamando a getMaxIDEmployees", mngBean_ID);
            logger.log(Level.INFO,"[{0}" + "] " + "Error: "+ e.getMessage(), mngBean_ID);
        }
        return result;
    }
    
    
    public int getCantmployees()
    {
        int result = 0;
        try
        {
            result = ejbFacade.count();
        }
        catch(Exception e)
        {
            logger.log(Level.INFO,"[{0}" + "] " + "Error llamando a getCantmployees", mngBean_ID);
            logger.log(Level.INFO,"[{0}" + "] " + "Error: "+ e.getMessage(), mngBean_ID);
        }
        return result;
    }
            
    public boolean getNoRowSelected() {
        if(noRowSelected)
        {
            logger.log(Level.INFO,"[{0}" + "] " + "noRowSelected is True", mngBean_ID);
        }
        else
        {
            logger.log(Level.INFO,"[{0}" + "] " + "noRowSelected is False", mngBean_ID);
        }
        
        return noRowSelected;
    }

    public People getSelectedEmployee() {
        if (selectedEmployee == null) {
            logger.log(Level.INFO,"[{0}" + "] " + "Initializing the selectedEmployee because it was null", mngBean_ID);
            selectedEmployee = new People();
            selectedItemIndex = -1;
        }
        return selectedEmployee;
    }

    public void setSelectedEmployee(People selectedEmployee) {
        if(selectedEmployee == null){
            logger.log(Level.INFO,"[{0}" + "] " + "Setting the selectedEmployee with null", mngBean_ID);
        }
        else{
            logger.log(Level.INFO,"[{0}" + "] " + "Setting the selectedEmployee with "+selectedEmployee.getName(), mngBean_ID);
        }
            
        this.selectedEmployee = selectedEmployee;
    }

    public boolean getIsForEditing() {
        return isForEditing;
    }
    
    public boolean getIsForCreating() {
        return !isForEditing;
    }

    public PeopleController() {
        noRowSelected = true;
        selectedEmployee = null;
        mngBean_ID = (new java.util.Random()).nextInt();
        isForEditing = false;
        logger.log(Level.INFO,"[{0}" + "] " + "Initializing the Bean with new ID", mngBean_ID);
    }

    public People getSelected() {
        if (selectedEmployee == null) {
            selectedEmployee = new People();
            selectedItemIndex = -1;
        }
        return selectedEmployee;
    }
    
    public void setSelected(People selected) {
        selectedEmployee = selected;
    }

    private PeopleFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10000) {
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
        //return "List";
    }

    public void prepareView() {
        isForEditing = true;
        //current = (People) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        logger.log(Level.INFO, "[{0}" + "] " + "prepareView being called", mngBean_ID);
        //return "View";
    }

    public void prepareCreate() {
        isForEditing = false;
        if(!noRowSelected){
            this.onRowUnselect(null);
        }
        logger.log(Level.INFO, "[{0}" + "] " + "prepareCreate being called", mngBean_ID);
        this.selectedEmployee = new People();
        selectedItemIndex = -1;
            //return "Create";
    }

    public void create() {
        try {
            logger.log(Level.INFO, "[{0}" + "] " + "create being called", mngBean_ID);
            People tempP = new People();
            tempP.setIdEmployee(this.selectedEmployee.getIdEmployee());
            tempP.setName(this.selectedEmployee.getName());
            tempP.setRole(this.selectedEmployee.getRole());
            tempP.setEmail(this.selectedEmployee.getEmail());
            getFacade().create(tempP);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeopleCreated"));
            //prepareCreate();
            prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            //return null;
        }
    }

    public void prepareEdit() {
        isForEditing = true;
        logger.log(Level.INFO, "[{0}" + "] " + "prepareEdit being called", mngBean_ID);
        logger.log(Level.INFO, "[{0}" + "] " + "with selectedEmployee "+this.selectedEmployee.getName(), mngBean_ID);
        //current = (People) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        //return "Edit";
    }

    public void update() {
        try {
            logger.log(Level.INFO, "[{0}" + "] " + "update being called", mngBean_ID);
            getFacade().edit(this.selectedEmployee);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeopleUpdated"));
            //return "View";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "[{0}" + "] " + "update error... " + e.getMessage(), mngBean_ID);
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            //return null;
        }
    }

    public void destroy() {
        this.selectedEmployee = (People) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        //return "List";
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
            getFacade().remove(selectedEmployee);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeopleDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        logger.log(Level.INFO, "[{0}" + "] " + "updateCurrentItem being called", mngBean_ID);
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
            selectedEmployee = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        //logger.log(Level.INFO, "[{0}" + "] " + "getItems being called", mngBean_ID);
        if (items == null) {
            logger.log(Level.INFO, "[{0}" + "] " + "getItems from Facade", mngBean_ID);
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

    public void onRowSelect(SelectEvent event) {
        noRowSelected = false;
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        logger.log(Level.INFO, "[{0}" + "] " + "SelectEvent being called", mngBean_ID);
        if(selectedEmployee == null)
        {
            logger.log(Level.INFO,"[{0}" + "] " + "selectedEmployee is null!!", mngBean_ID);
        }
        else
        {
            logger.log(Level.INFO,"[{0}" + "] " + "selectedEmployee ID: "+ selectedEmployee.getIdEmployee() + " with index "+ selectedItemIndex, mngBean_ID);
        }
        FacesMessage msg = new FacesMessage("Index Selected", selectedEmployee.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        noRowSelected = true;
        logger.log(Level.INFO,"[{0}" + "] " + "UnselectEvent being called", mngBean_ID);
        //FacesMessage msg = new FacesMessage("Person Unselected", ((People) event.getObject()).getIdEmployee().toString());
        if(selectedEmployee == null)
        {
            logger.log(Level.INFO,"[{0}" + "] " + "selectedEmployee is null :)", mngBean_ID);
        }
        else
        {
            logger.log(Level.INFO,"[{0}" + "] " + "selectedEmployee!! with ID: "+ selectedEmployee.getIdEmployee(), mngBean_ID);
        }
        //this.selectedEmployee = null;
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onShowing() {
        logger.log(Level.INFO,"[{0}" + "] " + "onShowing being called with selected emplyee", mngBean_ID);
        try{
            logger.log(Level.INFO,"[{0}" + "] " + selectedEmployee.getName(), mngBean_ID);
        }
        catch(Exception e){
            logger.log(Level.INFO,"[{0}" + "] " + "Error during onShowing... "+e.getMessage(), mngBean_ID);
        }
            
        //FacesMessage msg = new FacesMessage("Person Unselected", ((People) event.getObject()).getIdEmployee().toString());
        //FacesMessage msg = new FacesMessage("Person selected2", selectedEmployee.getName());
        //this.selectedEmployee = null;
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void onEdit(RowEditEvent event) {
        try {
            logger.log(Level.INFO, "[{0}" + "] " + "onEdit being called", mngBean_ID);
            getFacade().edit((People) event.getObject());
            
            //return "View";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "[{0}" + "] " + "onEdit error... " + e.getMessage(), mngBean_ID);
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return;
            //return null;
        }
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PeopleUpdated"));
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Employee Cancelled", (((People) event.getObject()).getIdEmployee()).toString());
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  

    @FacesConverter(forClass = People.class)
    public static class PeopleControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PeopleController controller = (PeopleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "peopleController");
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
            if (object instanceof People) {
                People o = (People) object;
                return getStringKey(o.getIdEmployee());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + People.class.getName());
            }
        }
    }
}
