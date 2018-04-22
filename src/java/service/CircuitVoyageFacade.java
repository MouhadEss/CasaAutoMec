/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.CircuitVoyage;
import bean.Ville;
import bean.Voyage;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author IlyassElfouih
 */
@Stateless
public class CircuitVoyageFacade extends AbstractFacade<CircuitVoyage> {

    @PersistenceContext(unitName = "covoiturageV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CircuitVoyageFacade() {
        super(CircuitVoyage.class);
    }
    
    public List<Ville> getVilleCircuitByVoyage(Voyage voyage){
        return  getEntityManager().createQuery("select DISTINCT c.villeDep,c.villeArr "
                + "from CircuitVoyage c where c.voyage.id="+voyage.getId()+"")
                .getResultList();
    }
    
}
