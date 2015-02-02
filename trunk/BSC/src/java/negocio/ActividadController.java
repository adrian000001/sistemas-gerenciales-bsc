package negocio;

import modelo.Actividad;
import negocio.util.JsfUtil;
import negocio.util.JsfUtil.PersistAction;
import bean.ActividadFacade;
import bean.ObjetivoestrategicoFacade;
import informes.InformeActividad;
import informes.InformeObjEs;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Objetivoestrategico;

@ManagedBean(name = "actividadController")
@SessionScoped
public class ActividadController implements Serializable {

    @EJB
    private bean.ActividadFacade ejbFacade;
    @EJB
    private bean.ObjetivoestrategicoFacade ejbFacade1;
    private List<Actividad> items = null;
     private List<Objetivoestrategico> items2 = null;
    private Actividad selected;

    public ObjetivoestrategicoFacade getEjbFacade1() {
        return ejbFacade1;
    }

    public void setEjbFacade1(ObjetivoestrategicoFacade ejbFacade1) {
        this.ejbFacade1 = ejbFacade1;
    }

    public ActividadController() {
    }
    @PostConstruct
    public void init() {
        if (items == null) {
            items = getFacade().findAll();
        }
        
    }
    
public void generarReporte()
    {
        items2 = getEjbFacade1().findAll();
        
        InformeActividad inf=new InformeActividad("ACTIVIDADES",items2);
       inf.generarInforme();
    }
    public Actividad getSelected() {
        return selected;
    }

    public void setSelected(Actividad selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ActividadFacade getFacade() {
        return ejbFacade;
    }

    public Actividad prepareCreate() {
        selected = new Actividad();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, "Actividad Creada");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Actividad Actualizada");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Actividad Eliminada");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Actividad> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Actividad> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Actividad> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    private Object getEjbFacade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FacesConverter(forClass = Actividad.class)
    public static class ActividadControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ActividadController controller = (ActividadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "actividadController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Actividad) {
                Actividad o = (Actividad) object;
                return getStringKey(o.getIdActividad());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Actividad.class.getName()});
                return null;
            }
        }

    }

}
