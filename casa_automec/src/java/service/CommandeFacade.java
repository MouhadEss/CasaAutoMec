/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Client;
import bean.Commande;
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
public class CommandeFacade extends AbstractFacade<Commande> {

    @PersistenceContext(unitName = "casa_automecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandeFacade() {
        super(Commande.class);
    }
    
    public Commande findById(Long IdCmd){
        return (Commande) em.createQuery("SELECT cmd from Commande cmd where cmd.id="+IdCmd).getSingleResult();
    }
    
    public void create(Commande cmd){
        cmd.setId(generateId("Commande", "id"));
        cmd.setDatecommande(new Date());
        super.create(cmd);
    }
    
    public List<Commande> findByClient(Client c){
        return em.createQuery("SELECT C from Commande c WHERE c.client.idClient like"+c.getIdClient()+"'").getResultList();
    }
    
    public List<Commande> findByClientCmdNonT(Client c){
        return em.createQuery("SELECT c FROM Commande c WHERE c.client.idClient like '"+c.getIdClient()+"' AND c.traiter = 0").getResultList();
    }
    
    
    public List<Commande> findByClientCmdT(Client c){
        return em.createQuery("SELECT c FROM Commande c WHERE c.client.idClient like '"+c.getIdClient()+"' AND c.traiter = 1").getResultList();
    }
    
    
    public List<Commande> findByCmdNonT(){
        return em.createQuery("SELECT c FROM Commande c WHERE c.traiter = 0").getResultList();
    }
    
    public List<Commande> findByCmdT(){
        return em.createQuery("SELECT c FROM Commande c WHERE c.traiter = 1").getResultList();
    }

}