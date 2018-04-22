/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Message;
import bean.MessageDetail;
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

    @PersistenceContext(unitName = "covoituragev5PU")
    private EntityManager em;
    
    @EJB
    MessageDetailFacade messageDetailFacade;


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageFacade() {
        super(Message.class);
    }
    
    public void createMessage(Message message, List<MessageDetail> messageDetail) {
        create(message);
        for (MessageDetail messageDetail1 : messageDetail) {
            messageDetail1.setMessage(message);
            messageDetailFacade.create(messageDetail1);

        }

    }
    
    @Override
    public void create(Message message) {
        message.setId(generateId("Message", "id"));
        super.create(message);
    }

    public List<Message> findMssageByPersonne(String email) {
        return getEntityManager().createQuery("select up from Message up join MessageDetail "
                + "m where up.id=m.message.id and m.personne.email='"+email+"'  ").getResultList();
        //To change body of generated methods, choose Tools | Templates.
    }

    
}
