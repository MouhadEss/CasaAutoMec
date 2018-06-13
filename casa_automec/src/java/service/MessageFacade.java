/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Message;
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
public class MessageFacade extends AbstractFacade<Message> {

    @PersistenceContext(unitName = "casa_automecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageFacade() {
        super(Message.class);
    }
    
    @EJB
    ManagerFacade managerFacade;
    
    public void create(Message message){
        System.out.println(message);
        message.setId(generateId("Message", "id"));
        message.setManager(managerFacade.findManagerById("mouhad"));
        message.setDateEnvoie(new Date());
        System.out.println(message);
        super.create(message);
    }
    
    public List<Message> findMessages(){
        return getEntityManager().createQuery("SELECT m FROM Message m ORDER BY m.dateEnvoie DESC").getResultList();
    }
    
}
