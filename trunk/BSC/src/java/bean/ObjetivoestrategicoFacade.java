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
import modelo.Objetivoestrategico;

/**
 *
 * @author Farfan
 */
@Stateless
public class ObjetivoestrategicoFacade extends AbstractFacade<Objetivoestrategico> {
    @PersistenceContext(unitName = "BSCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObjetivoestrategicoFacade() {
        super(Objetivoestrategico.class);
    }
    
    public Objetivoestrategico getSelected(int  idObjetivoEstrategico) {
        List<Objetivoestrategico> res;
        Query query = this.em.createNamedQuery(Objetivoestrategico.findByIdObjetivoEstrategico);
        query.setParameter("idObjetivoEstrategico",idObjetivoEstrategico);
        res=query.getResultList();
        if (res.size()>0)
            return res.get(0);
        else
            return null;
    }
}
