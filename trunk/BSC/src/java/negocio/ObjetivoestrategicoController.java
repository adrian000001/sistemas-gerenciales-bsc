package negocio;

import modelo.Objetivoestrategico;
import negocio.util.JsfUtil;
import negocio.util.JsfUtil.PersistAction;
import bean.ObjetivoestrategicoFacade;
import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
import modelo.Indicador;
import modelo.Objetivoestrategicoindicador;
import modelo.ObjetivoestrategicoindicadorPK;

@ManagedBean(name = "objetivoestrategicoController")
@SessionScoped
public class ObjetivoestrategicoController implements Serializable {

    @EJB
    private bean.ObjetivoestrategicoFacade ejbFacade;
    private List<Objetivoestrategico> items = null;
    private Objetivoestrategico selected;
    private Objetivoestrategicoindicador metaSeleccionada;
    private Objetivoestrategicoindicador nuevoObjetivoestrategicoindicador;
    int idProvisional;

    public ObjetivoestrategicoController() {
    }
    
    public Objetivoestrategico preparaNuevo(){
        idProvisional=0;
        nuevoObjetivoestrategicoindicador=new Objetivoestrategicoindicador();
        nuevoObjetivoestrategicoindicador.setObjetivoestrategico(selected);
        nuevoObjetivoestrategicoindicador.setIndicador(new Indicador());
        nuevoObjetivoestrategicoindicador.setObjetivoestrategicoindicadorPK(new ObjetivoestrategicoindicadorPK(0, idProvisional));
        return this.prepareCreate();
    }
    
    public void agregarIndicador(){
        this.selected.getObjetivoestrategicoindicadorCollection().add(nuevoObjetivoestrategicoindicador);
        nuevoObjetivoestrategicoindicador=new Objetivoestrategicoindicador();
        nuevoObjetivoestrategicoindicador.setObjetivoestrategico(selected);
        nuevoObjetivoestrategicoindicador.setIndicador(new Indicador());
        idProvisional++;
        nuevoObjetivoestrategicoindicador.setObjetivoestrategicoindicadorPK(new ObjetivoestrategicoindicadorPK(0, idProvisional));
    }

    public Objetivoestrategico getSelected() {
        return selected;
    }

    public void setSelected(Objetivoestrategico selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ObjetivoestrategicoFacade getFacade() {
        return ejbFacade;
    }

    public Objetivoestrategico prepareCreate() {
        selected = new Objetivoestrategico();
        initializeEmbeddableKey();
        selected.setObjetivoestrategicoindicadorCollection(new ArrayList<>());
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoestrategicoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoestrategicoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ObjetivoestrategicoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Objetivoestrategico> getItems() {
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

    public List<Objetivoestrategico> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Objetivoestrategico> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the metaSeleccionada
     */
    public Objetivoestrategicoindicador getMetaSeleccionada() {
        return metaSeleccionada;
    }

    /**
     * @param metaSeleccionada the metaSeleccionada to set
     */
    public void setMetaSeleccionada(Objetivoestrategicoindicador metaSeleccionada) {
        this.metaSeleccionada = metaSeleccionada;
    }

    /**
     * @return the nuevoObjetivoestrategicoindicador
     */
    public Objetivoestrategicoindicador getNuevoObjetivoestrategicoindicador() {
        return nuevoObjetivoestrategicoindicador;
    }

    /**
     * @param nuevoObjetivoestrategicoindicador the nuevoObjetivoestrategicoindicador to set
     */
    public void setNuevoObjetivoestrategicoindicador(Objetivoestrategicoindicador nuevoObjetivoestrategicoindicador) {
        this.nuevoObjetivoestrategicoindicador = nuevoObjetivoestrategicoindicador;
    }

    @FacesConverter(forClass = Objetivoestrategico.class)
    public static class ObjetivoestrategicoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ObjetivoestrategicoController controller = (ObjetivoestrategicoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "objetivoestrategicoController");
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
            if (object instanceof Objetivoestrategico) {
                Objetivoestrategico o = (Objetivoestrategico) object;
                return getStringKey(o.getIdObjetivoEstrategico());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Objetivoestrategico.class.getName()});
                return null;
            }
        }

    }

}
