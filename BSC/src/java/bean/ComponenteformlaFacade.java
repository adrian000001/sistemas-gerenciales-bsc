/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Componenteformla;

/**
 *
 * @author Farfan
 */
@Stateless
public class ComponenteformlaFacade extends AbstractFacade<Componenteformla> {
    @PersistenceContext(unitName = "BSCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComponenteformlaFacade() {
        super(Componenteformla.class);
    }
    
}
