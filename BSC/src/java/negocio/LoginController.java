/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import bean.UsuarioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Usuario;

/**
 *
 * @author adrian
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    private bean.UsuarioFacade ejbFacade;
    private String nombre;
    private String contrasena;
    private String tipo;

    private HttpServletRequest httpServletRequest;
    private FacesContext faceContext;
    private FacesMessage facesMessage;
    private Usuario userSession;

    public String getTipo() {
        return tipo;
    }

    public Usuario getUserSession() {
        return userSession;
    }

    public void setUserSession(Usuario userSession) {
        this.userSession = userSession;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public UsuarioFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(UsuarioFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void verficarLogin() {

        System.out.println("holisss" + nombre + "pass" + contrasena);

        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        Usuario user = this.getEjbFacade().login(nombre, contrasena, tipo);

        if (tipo.equals("0")) {
            if (user != null) {
                try {
                    httpServletRequest.getSession().setAttribute("sessionUsuario", nombre);
                    httpServletRequest.getSession().setAttribute("tipoSession", "Usuario");
                    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto", null);
                    this.userSession = user;
                    faceContext.addMessage(null, facesMessage);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/BSC/faces/estrategiaglobal/inicio.xhtml");

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrecto", null);
                faceContext.addMessage(null, facesMessage);
            }
        } else {
            if(tipo.equals("1"))
            {
                
            
            if (user != null) {
                try {
                    facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrecto", null);
                    faceContext.addMessage(null, facesMessage);
                    httpServletRequest.getSession().setAttribute("sessionUsuario", nombre);
                    httpServletRequest.getSession().setAttribute("tipoSession", "Administrador");
                    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto", null);
                    faceContext.addMessage(null, facesMessage);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/BSC/faces/estrategiaglobal/List.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrecto", null);
                faceContext.addMessage(null, facesMessage);
            }
            }
            else
            {
                
            
            if (user != null) {
                try {
                    facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrecto", null);
                    faceContext.addMessage(null, facesMessage);
                    httpServletRequest.getSession().setAttribute("sessionUsuario", nombre);
                    httpServletRequest.getSession().setAttribute("tipoSession", "AdminInd");
                    facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Correcto", null);
                    faceContext.addMessage(null, facesMessage);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/BSC/faces/objetivoestrategico/ListInd.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrecto", null);
                faceContext.addMessage(null, facesMessage);
            }
            }
        }
    }
    public void Logout() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            faceContext.addMessage(null, facesMessage);
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }



    public LoginController() {
    }

}
