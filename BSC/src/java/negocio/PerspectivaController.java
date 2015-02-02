package negocio;

import modelo.Perspectiva;
import negocio.util.JsfUtil;
import negocio.util.JsfUtil.PersistAction;
import bean.PerspectivaFacade;

import java.io.Serializable;
import java.util.ArrayList;
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

@ManagedBean(name = "perspectivaController")
@SessionScoped
public class PerspectivaController implements Serializable {

    @EJB
    private bean.PerspectivaFacade ejbFacade;
    private List<Perspectiva> items = null;
    private Perspectiva selected;
    private List<String> itemsNom = null;
    public PerspectivaController() {
    }

    public List<String> getItemsNom() {
        return itemsNom;
    }

    public void setItemsNom(List<String> itemsNom) {
        this.itemsNom = itemsNom;
    }
     @PostConstruct
    public void init(){
        itemsNom=new ArrayList();
         if (items == null) {
            items = getFacade().findAll();
            getItemsNombre();
        }
    }
    public Perspectiva getSelected() {
        return selected;
    }

    public void setSelected(Perspectiva selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PerspectivaFacade getFacade() {
        return ejbFacade;
    }

    public Perspectiva prepareCreate() {
        selected = new Perspectiva();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, "Guardado");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Actualizado");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Eliminado");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Perspectiva> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    public void getItemsNombre()
    {
        for(int i=0;i<items.size();i++)
        {
           itemsNom.add(items.get(i).getNombre());
        }
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

    public List<Perspectiva> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Perspectiva> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Perspectiva.class)
    public static class PerspectivaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PerspectivaController controller = (PerspectivaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "perspectivaController");
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
            if (object instanceof Perspectiva) {
                Perspectiva o = (Perspectiva) object;
                return getStringKey(o.getIdPerspectiva());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Perspectiva.class.getName()});
                return null;
            }
        }

    }

}
