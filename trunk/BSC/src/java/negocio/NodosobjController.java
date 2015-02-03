package negocio;

import modelo.util.JsfUtil;
import modelo.util.JsfUtil.PersistAction;
import bean.NodosobjFacade;
import bean.ObjetivoestrategicoFacade;
import generadormapaestrategico.MapaEstrategico;
import generadormapaestrategico.NewClass;
import grafico.GenerarMapa;

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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Nodosobj;
import modelo.Objetivoestrategico;

@ManagedBean(name = "nodosobjController")
@SessionScoped
public class NodosobjController implements Serializable {
    @EJB
    private bean.ObjetivoestrategicoFacade ejbEstragias;
    @EJB
    private bean.NodosobjFacade ejbFacade;
    private List<Nodosobj> items = null;
    private Nodosobj selected;
    private List<Objetivoestrategico> objetivosEstrategicosLista;
    private String ruta;
     @PostConstruct
    public void init(){
          if (items == null) {
            items = getFacade().findAll();
        }
          
    }

    public NodosobjFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(NodosobjFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
      private ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

    public void generarArbol() {
       objetivosEstrategicosLista = ejbEstragias.findAll();
        GenerarMapa bean = new GenerarMapa(objetivosEstrategicosLista,items);
        bean.main();
  
    }
    public NodosobjController() {
    }

    public Nodosobj getSelected() {
        return selected;
    }

    public void setSelected(Nodosobj selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private NodosobjFacade getFacade() {
        return ejbFacade;
    }

    public Nodosobj prepareCreate() {
        selected = new Nodosobj();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE,"NodosobjCreated");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "NodosobjUpdated");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "NodosobjDeleted");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Nodosobj> getItems() {
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

    public List<Nodosobj> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Nodosobj> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Nodosobj.class)
    public static class NodosobjControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NodosobjController controller = (NodosobjController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "nodosobjController");
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
            if (object instanceof Nodosobj) {
                Nodosobj o = (Nodosobj) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Nodosobj.class.getName()});
                return null;
            }
        }

    }

    public ObjetivoestrategicoFacade getEjbEstragias() {
        return ejbEstragias;
    }

    public void setEjbEstragias(ObjetivoestrategicoFacade ejbEstragias) {
        this.ejbEstragias = ejbEstragias;
    }

    public void setObjetivosEstrategicosLista(List<Objetivoestrategico> objetivosEstrategicosLista) {
        this.objetivosEstrategicosLista = objetivosEstrategicosLista;
    }
    

}
