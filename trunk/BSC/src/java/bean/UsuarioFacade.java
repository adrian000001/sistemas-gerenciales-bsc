/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Usuario;

/**
 *
 * @author Farfan
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "BSCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario login(String userName, String contrasena, String tipoUsuario) {
       
        Query query = this.em.createNamedQuery(Usuario.login);
        query.setParameter("nombre", userName);
        query.setParameter("contrasena", contrasena);
       
        char cad;
        cad = tipoUsuario.charAt(0); 
         System.out.println("joliss"+userName+"  "+contrasena+"  "+cad);
         query.setParameter("tipoUsuario", cad);

        try {
            return (Usuario) query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        }

    }

}
