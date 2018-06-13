/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.ImgCommande;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Stateless
public class ImgCommandeFacade extends AbstractFacade<ImgCommande> {

    @PersistenceContext(unitName = "casa_automecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImgCommandeFacade() {
        super(ImgCommande.class);
    }
    
    public Long returnId(){
        return generateId("ImgCommande", "id");
    }
    
    public void create(ImgCommande ic,List<String> urls){
        for (String url : urls) {
            ImgCommande imgC=new ImgCommande();
            imgC.setClient(ic.getClient());
            imgC.setId(generateId("ImgCommande", "id"));
            imgC.setCommentaire(ic.getCommentaire());
            imgC.setName(url);
            imgC.setDateCmd(new Date());
            super.create(imgC);
        }
    }
    
}
