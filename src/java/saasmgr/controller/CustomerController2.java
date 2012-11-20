/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package saasmgr.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import saasmgr.dao.CustomersFacade;
import saasmgr.entity.Customers;

/**
 *
 * @author SG0894243
 */
@Named(value = "customerController2")
@SessionScoped
public class CustomerController2 extends TemplateController implements Serializable {

    private List<SelectItem> colRegions; 
    @EJB
    private CustomersFacade ejbFacade;
    /**
     * Creates a new instance of CustomerController2
     */
    public CustomerController2() {
        super("saasmgr.controller.peopleController", "Customers");
        colRegions = null;
    }
    
    @Override
    protected CustomersFacade getFacade() {
        return ejbFacade;
    }

    @Override
    protected Object getNewObject() {
        return new Customers();
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

    @FacesConverter(forClass = Customers.class)
    public static class CustomerController2Converter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CustomerController2 controller = (CustomerController2) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "CustomerController2");
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
