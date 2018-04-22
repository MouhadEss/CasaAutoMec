/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.BagageVoyage;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author IlyassElfouih
 */
@Stateless
public class BagageVoyageFacade extends AbstractFacade<BagageVoyage> {

    @PersistenceContext(unitName = "covoiturageV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BagageVoyageFacade() {
        super(BagageVoyage.class);
    }
    
}
