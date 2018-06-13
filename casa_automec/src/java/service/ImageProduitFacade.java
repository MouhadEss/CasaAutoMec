/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.ImageProduit;
import bean.Produit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Stateless
public class ImageProduitFacade extends AbstractFacade<ImageProduit> {

    @PersistenceContext(unitName = "casa_automecPU")
    private EntityManager em;
    private int i =0;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImageProduitFacade() {
        super(ImageProduit.class);
    }
    
    public void create(List<String> urls,String type,Produit p){
            for (String url : urls) {
        ImageProduit ip =new ImageProduit();
        ip.setProduit(p);
        ip.setId(generateId("ImageProduit", "id"));
        ip.setNameimg(url);
        ip.setTypeimg(type);
        super.create(ip);
        }
    }
    
    public void SupprimerImgProduit(ImageProduit ip){
        ImageProduit imageProduit=find(ip);
        System.out.println(imageProduit);
        System.out.println(ip);
        super.remove(imageProduit);
    }
    
    public Long returnId(){
        return generateId("ImageProduit", "id");
    }
    
}
