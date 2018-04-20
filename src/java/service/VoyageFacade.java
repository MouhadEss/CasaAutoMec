/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Ville;
import bean.Voyage;
import controller.util.DateUtil;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author IlyassElfouih
 */
@Stateless
public class VoyageFacade extends AbstractFacade<Voyage> {

    @PersistenceContext(unitName = "covoiturageV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VoyageFacade() {
        super(Voyage.class);
    }
     @Override
    public void create(Voyage voyage){
         System.out.println("hana dkhelt override create");
        voyage.setId(generateId("Voyage", "id"));
        super.create(voyage);
        
    }
    
     public List<Voyage> chercherInVoyage(Ville villeDep, Ville villeArr, Date dateVoyage,Double prixMax,Double prixMin) {
      
        String query="SELECT DISTINCT v FROM Voyage v  LEFT JOIN CircuitVoyage c  "
                        + " where v.id=c.voyage.id  "
                        + "and  v.nbrPlaceMax != v.nbrPlaceOuccuper ";
        
        if(villeDep!=null)
            query+=" and ( v.villeDepart.id="+villeDep.getId()+" or c.villeDep.id="+villeDep.getId()+" )";
        if(villeArr!=null)
            query+="  and (v.villeArriver.id="+villeArr.getId()+" or c.villeArr.id="+villeArr.getId()+" )";
        if(dateVoyage!=null)
            query+=" and v.dateVoyage='" + DateUtil.formateDate("yyyy-MM-dd", dateVoyage)+ "'";
        if(prixMax!=null)
            query+=" and ( (v.prix < "+prixMax+" or c.prix < "+prixMax+" )";
        if(prixMin!=null)
            query+=" and (v.prix > "+prixMin+" or c.prix > "+prixMin+" ) )";
       
        System.out.println(query);
        return  getEntityManager().createQuery(query).getResultList();
    }
}
