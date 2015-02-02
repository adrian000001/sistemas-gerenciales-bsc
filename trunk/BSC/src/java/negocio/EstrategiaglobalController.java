package negocio;

import modelo.Estrategiaglobal;
import negocio.util.JsfUtil;
import negocio.util.JsfUtil.PersistAction;
import bean.EstrategiaglobalFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "estrategiaglobalController")
@SessionScoped
public class EstrategiaglobalController implements Serializable {

    @EJB
    private bean.EstrategiaglobalFacade ejbFacade;
    private List<Estrategiaglobal> items = null;
    private Estrategiaglobal selected;
    private String estrategia;

    public EstrategiaglobalController() {
    }
    
    public void guardar(){
        this.setSelected(new Estrategiaglobal());
        this.getSelected().setEstrategia(estrategia);
        this.create();
    }
    
    public void preparaNuevo(){
        System.out.println("nuevo");
        estrategia="";
    }

    public Estrategiaglobal getSelected() {
        return selected;
    }

    public void setSelected(Estrategiaglobal selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EstrategiaglobalFacade getFacade() {
        return ejbFacade;
    }

    public Estrategiaglobal prepareCreate() {
        
        System.out.println("dsfds");
        selected = new Estrategiaglobal();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        
        persist(PersistAction.CREATE, "Estrategia Global Guardada");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Estrategia Global Actualizado");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Estrategia Global Eliminada");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Estrategiaglobal> getItems() {
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

    public List<Estrategiaglobal> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Estrategiaglobal> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the estrategia
     */
    public String getEstrategia() {
        return estrategia;
    }

    /**
     * @param estrategia the estrategia to set
     */
    public void setEstrategia(String estrategia) {
        this.estrategia = estrategia;
    }

    @FacesConverter(forClass = Estrategiaglobal.class)
    public static class EstrategiaglobalControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstrategiaglobalController controller = (EstrategiaglobalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estrategiaglobalController");
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
            if (object instanceof Estrategiaglobal) {
                Estrategiaglobal o = (Estrategiaglobal) object;
                return getStringKey(o.getIdEstrategiaGlobal());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Estrategiaglobal.class.getName()});
                return null;
            }
        }

    }

}
