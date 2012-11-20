package saasmgr.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import saasmgr.dao.AbstractFacade;
import saasmgr.entity.Projects;

@Named("projectsController")
@SessionScoped
public class ProjectsController extends TemplateController implements Serializable {
    @EJB
    private saasmgr.dao.ProjectsFacade ejbFacade;
    private List<SelectItem> colEnvs;

    public ProjectsController() {
        super("saasmgr.controller.peopleController","People");
        colEnvs = null;
    }

    @Override
    protected AbstractFacade getFacade() {
        return ejbFacade;
    }

    @Override
    protected Object getNewObject() {
        logger.log(Level.INFO, "[{0}" + "] " + "getNewObject being called", mngBean_ID);
        Projects p = new Projects();
        return p;
    }
    
    public List<SelectItem> getColEnvs() {
        if (colEnvs == null) {
            colEnvs = new ArrayList<SelectItem>();
            String[] regions = {"Int","Cert","Prod"};
            for (int i = 0; i < regions.length; i++) {
                logger.info(regions[i]);
                colEnvs.add(new SelectItem(regions[i]));
            }
        }
        return colEnvs;
    }

    @FacesConverter(forClass = Projects.class)
    public static class ProjectsControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProjectsController controller = (ProjectsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "projectsController");
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
            if (object instanceof Projects) {
                Projects o = (Projects) object;
                return getStringKey(o.getProjectId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Projects.class.getName());
            }
        }
    }
}
