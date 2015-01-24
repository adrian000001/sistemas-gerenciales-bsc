/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Objetivoestrategicoindicador;

/**
 *
 * @author Farfan
 */
@Stateless
public class ObjetivoestrategicoindicadorFacade extends AbstractFacade<Objetivoestrategicoindicador> {
    @PersistenceContext(unitName = "BSCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObjetivoestrategicoindicadorFacade() {
        super(Objetivoestrategicoindicador.class);
    }
    
    public List<Objetivoestrategicoindicador> getItemsMetas(int  idObjetivoEstrategico) {
        Query query = this.em.createNamedQuery(Objetivoestrategicoindicador.findByIdObjetivoEstrategico);
        query.setParameter("idObjetivoEstrategico",idObjetivoEstrategico);
        return query.getResultList();
    }
    
}
