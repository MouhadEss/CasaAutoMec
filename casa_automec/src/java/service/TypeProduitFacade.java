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
public class TypeProduitFacade extends AbstractFacade<TypeProduit> {

    @PersistenceContext(unitName = "casa_automecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @EJB
    ProduitFacade produitFacade;

    public TypeProduitFacade() {
        super(TypeProduit.class);
    }

    public void create(TypeProduit tp) {
        tp.setId(generateId("TypeProduit", "id"));
        super.create(tp);
    }

    public void editeP(TypeProduit tp) {
        TypeProduit typeProduit = find(tp.getId());
        System.out.println(typeProduit);
        typeProduit.setType(tp.getType());
        super.edit(typeProduit);
    }

    public List<TypeProduit> findTypeProduitsbyVehicule(TypeVehicule tv) {
        return em.createQuery("SELECT tp FROM TypeProduit tp WHERE tp.typeVehicule.id =" + tv.getId() + "").getResultList();
    }

    public void supprimer(TypeProduit typeProduit) {
        List<Produit> produits = em.createQuery("SELECT p FROM Produit p WHERE p.typeProduit.id =" + typeProduit.getId() + "").getResultList();
        System.out.println(produits);
        for (Produit produit : produits) {
            produitFacade.suprimerProduit(produit);
        }
        super.remove(typeProduit);
    }
}
