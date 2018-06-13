/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Commande;
import bean.CommandeItem;
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
public class CommandeItemFacade extends AbstractFacade<CommandeItem> {

    @PersistenceContext(unitName = "casa_automecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    @EJB
    CommandeFacade ejbCommande;

    public CommandeItemFacade() {
        super(CommandeItem.class);
    }
    
    public void createCItem(Commande cmd,List<CommandeItem> cmdItems){
        ejbCommande.create(cmd);
        for (CommandeItem cmdItem : cmdItems) {
            cmdItem.setId(generateId("CommandeItem", "id"));
            cmdItem.setCommande(cmd);
            super.create(cmdItem);
            
        }
    }
    
    public CommandeItem findCmdItemById(Long idItem) {
        return  (CommandeItem) em.createQuery("SELECT ci FROM CommandeItem ci WHERE ci.id="+idItem).getSingleResult();
    }
    
    public void deleteCItem(CommandeItem cmdItem){
         em.createQuery("DELETE FROM CommandeItem WHERE CommandeItem.id"+cmdItem.getId());
        super.remove(cmdItem);
    }
    
}
