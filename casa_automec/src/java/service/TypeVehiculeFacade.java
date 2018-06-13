/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Produit;
import bean.TypeProduit;
import bean.TypeVehicule;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Stateless
public class TypeVehiculeFacade extends AbstractFacade<TypeVehicule> {

    @PersistenceContext(unitName = "casa_automecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @EJB
    ProduitFacade produitFacade;
    @EJB
    TypeProduitFacade typeProduitFacade;

    public TypeVehiculeFacade() {
        super(TypeVehicule.class);
    }

    public void create(TypeVehicule typeVehicule) {
        System.out.println(typeVehicule);
        typeVehicule.setId(generateId("TypeVehicule", "id"));
        super.create(typeVehicule);
    }

    public void supprimer(TypeVehicule typeVehicule) {
        List<Produit> produits = em.createQuery("SELECT p FROM Produit p WHERE p.typeProduit.typeVehicule.id =" + typeVehicule.getId() + "").getResultList();
        System.out.println(produits);
        for (Produit produit : produits) {
            produitFacade.suprimerProduit(produit);
        }
        List<TypeProduit> typeProduits = em.createQuery("SELECT tp FROM TypeProduit tp WHERE tp.typeVehicule.id =" + typeVehicule.getId() + "").getResultList();
        System.out.println(typeProduits);
        for (TypeProduit typeProduit : typeProduits) {
            typeProduitFacade.supprimer(typeProduit);
        }
        super.remove(typeVehicule);
    }

    public void editerV(TypeVehicule typeVehicule) {
        TypeVehicule tv = find(typeVehicule.getId());
        System.out.println(tv);
        tv.setVehicule(typeVehicule.getVehicule());
        super.edit(tv);
    }

}
