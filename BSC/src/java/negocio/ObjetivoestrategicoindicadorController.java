package negocio;

import bean.HistorialFacade;
import modelo.Objetivoestrategicoindicador;
import negocio.util.JsfUtil;
import negocio.util.JsfUtil.PersistAction;
import bean.ObjetivoestrategicoindicadorFacade;
import bean.SemaforoFacade;
import informes.Informe;

import java.io.Serializable;
import java.util.ArrayList;
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
import modelo.Historial;
import modelo.Indicador;
import modelo.Semaforo;

@ManagedBean(name = "objetivoestrategicoindicadorController")
@SessionScoped
public class ObjetivoestrategicoindicadorController implements Serializable {

    @EJB
    private bean.ObjetivoestrategicoindicadorFacade ejbFacade;
    @EJB
    private HistorialFacade ejbFacadeHistorial;
    @EJB
    private SemaforoFacade ejbFacadeSemaforo;
    private List<Objetivoestrategicoindicador> items = null;
    private Objetivoestrategicoindicador selected;

   
    public void generarReporte()
    {
        System.out.println("holissssaaaaawww");
       items = getFacade().findAll();
       Informe inf=new Informe("Cuadro mando Integral",items);
       inf.generarInforme();
    }
    
    public ObjetivoestrategicoindicadorController() {
    }

    public Objetivoestrategicoindicador getSelected() {
        return selected;
    }

    public void setSelected(Objetivoestrategicoindicador selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getObjetivoestrategicoindicadorPK().setIdIndicador(selected.getIndicador().getIdIndicador());
        selected.getObjetivoestrategicoindicadorPK().setIdObjetivoEstrategico(selected.getObjetivoestrategico().getIdObjetivoEstrategico());
    }

    protected void initializeEmbeddableKey() {
        selected.setObjetivoestrategicoindicadorPK(new modelo.ObjetivoestrategicoindicadorPK());
    }

    private ObjetivoestrategicoindicadorFacade getFacade() {
        return ejbFacade;
    }

    public Objetivoestrategicoindicador prepareCreate() {
        selected = new Objetivoestrategicoindicador();
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

    public List<Objetivoestrategicoindicador> getItems() {
        System.out.println("ACTUALIZO DATOS");
        items = getFacade().findAll();
        for (int i=0;i<items.size();i++){
            List <Historial> histrorial = ejbFacadeHistorial.getSemaforosIndicador(items.get(i).getIndicador().getIdIndicador());
            List <Semaforo> semaforos = ejbFacadeSemaforo.getSemaforosIndicador(items.get(i).getIndicador().getIdIndicador());
            items.get(i).getIndicador().setHistorialCollection(histrorial);
            items.get(i).getIndicador().setSemaforoCollection(semaforos);
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

    public List<Objetivoestrategicoindicador> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Objetivoestrategicoindicador> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Objetivoestrategicoindicador.class)
    public static class ObjetivoestrategicoindicadorControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ObjetivoestrategicoindicadorController controller = (ObjetivoestrategicoindicadorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "objetivoestrategicoindicadorController");
            return controller.getFacade().find(getKey(value));
        }

        modelo.ObjetivoestrategicoindicadorPK getKey(String value) {
            modelo.ObjetivoestrategicoindicadorPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new modelo.ObjetivoestrategicoindicadorPK();
            key.setIdObjetivoEstrategico(Integer.parseInt(values[0]));
            key.setIdIndicador(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(modelo.ObjetivoestrategicoindicadorPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdObjetivoEstrategico());
            sb.append(SEPARATOR);
            sb.append(value.getIdIndicador());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Objetivoestrategicoindicador) {
                Objetivoestrategicoindicador o = (Objetivoestrategicoindicador) object;
                return getStringKey(o.getObjetivoestrategicoindicadorPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Objetivoestrategicoindicador.class.getName()});
                return null;
            }
        }

    }

}
