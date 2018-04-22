/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Place;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Stateless
public class PlaceFacade extends AbstractFacade<Place> {

    @PersistenceContext(unitName = "covoituragev5PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlaceFacade() {
        super(Place.class);
    }
    
    public Place cloonnage(Place place){
        Place pl= new Place();
        pl.setAv(place.isAv());
        pl.setArrMilieu(place.isArrMilieu());
        pl.setArrGauche(place.isArrGauche());
        pl.setArrDroite(place.isArrDroite());
        return pl;
    }
    
    public void create(Place place){
        place.setId(generateId("Place", "id"));
        super.create(place);
    }
}
