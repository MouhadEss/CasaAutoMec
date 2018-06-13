/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.ImageProduit;
import bean.Produit;
import java.util.Date;
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
public class ProduitFacade extends AbstractFacade<Produit> {

    @EJB
    private ImageProduitFacade imageProduitFacade;

    @PersistenceContext(unitName = "casa_automecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    

    public ProduitFacade() {
        super(Produit.class);
    }

    public void create(Produit produit) {
        produit.setDateAjout(new Date());
        super.create(produit);
    }

    public void suprimerProduit(Produit p) {
        List<ImageProduit> imgs = em.createQuery("SELECT i FROM ImageProduit i WHERE i.produit.reference like '" + p.getReference() + "'").getResultList();
        System.out.println(imgs);
        if (imgs != null) {
            for (ImageProduit img : imgs) {
                System.out.println(img);
                
                if (img != null) {
                    
                    imageProduitFacade.remove(img);
                }
            }
        }
        System.out.println(p);
        
        super.remove(p);
    }
    
    public void editerProduit(Produit p){
        Produit produit=(Produit) em.createQuery("SELECT p FROM Produit p WHERE p.reference like '"+p.getReference()+"'").getSingleResult();
        System.out.println(produit);
        produit.setDescription(p.getDescription());
        produit.setNbrStock(p.getNbrStock());
        produit.setPrix(p.getPrix());
        produit.setTypeProduit(p.getTypeProduit());
        super.edit(produit);
    }
    
     public List<Produit> findByType(String typeP, String typeV) {
        return em.createQuery("SELECT p FROM Produit p WHERE p.typeProduit.type like '" + typeP + "' AND p.typeProduit.typeVehicule.vehicule like '" + typeV + "'").getResultList();
    }
    public Produit findByRef(String reference) {
        return (Produit) em.createQuery("SELECT p FROM Produit p WHERE p.reference like" + reference +"'").getSingleResult();
    }
    public List<Produit> findByDesc(String searchRef){
        return em.createQuery("SELECT p FROM Produit p WHERE p. like '%"+searchRef+"%' ").getResultList();
    }
    
    public List<ImageProduit> findImgs(Produit p){
        return em.createQuery("SELECT img FROM ImageProduit img WHERE img.produit.reference like '"+p.getReference()+"'").getResultList();
    }

}
