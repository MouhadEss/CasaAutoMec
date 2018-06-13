/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Client;
import controller.util.HashageUtil;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> {

    @PersistenceContext(unitName = "casa_automecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }

    public Client findClientById(String idClient) {
        System.out.println("hana3awtani");
        return (Client) getEntityManager().createQuery("SELECT cl FROM Client cl WHERE cl.idClient like '" + idClient + "'").getSingleResult();
    }

    public int verifClient(String idClient, String password) {
        Client client = findClientById(idClient);
        System.out.println(client);
        if (client.getPassword().equals(HashageUtil.sha256(password))) {
            return 1;
        } else {
            return 2;
        }

    }

    public void suprimerClient(Client client) {
        System.out.println(client);
        em.createQuery("DELETE FROM Client WHERE Clinet.idClient like '" + client.getIdClient() + "' ");

        super.remove(client);
    }

    public void bloquerClient(Client client) {
        if (client.isBloquer() == false) {
            client.setBloquer(true);
            super.edit(client);
        } else {
            client.setBloquer(false);
            super.edit(client);
        }
    }

    public void create(Client client) {
        client.setPassword(HashageUtil.sha256(client.getPassword()));
        super.create(client);
    }
    
    public void editerClient(Client client,String ancienId){
        Client clientedit=findClientById(ancienId);
        clientedit.setNomClient(client.getNomClient());
        clientedit.setPrenomClient(client.getPrenomClient());
        clientedit.setNomSociete(client.getNomSociete());
        clientedit.setPassword(HashageUtil.sha256(client.getPassword()));
        clientedit.setTel(client.getTel());
        clientedit.setCin(client.getCin());
        clientedit.setAdresse(client.getAdresse());
        clientedit.setEmail(client.getEmail());
        super.edit(clientedit);
    }
    
    public List<Client> findByAttribut(String search){
        List<Client> clients=new ArrayList();
        List<Client> idClients= em.createQuery("SELECT cl FROM Client cl WHERE cl.idClient like '%"+search+"%' ").getResultList();
        if(idClients!=null){
            for (Client idClient : idClients) {
                clients.add(idClient);
            }
        }
        List<Client> emailClients= em.createQuery("SELECT cl FROM Client cl WHERE cl.email like '%"+search+"%' ").getResultList();
        if(emailClients!=null){
            for (Client idClient :emailClients) {
                clients.add(idClient);
            }
        }
        List<Client> societeClients=em.createQuery("SELECT cl FROM Client cl WHERE cl.nomSociete like '%"+search+"%' ").getResultList();
        if(societeClients!=null){
            for (Client idClient :societeClients) {
                clients.add(idClient);
            }
        }
        List<Client> nomClients =em.createQuery("SELECT cl FROM Client cl WHERE cl.nomClient like '%"+search+"%' ").getResultList();
        if(nomClients!=null){
            for (Client idClient :nomClients) {
                clients.add(idClient);
            }
        }
        List<Client> prenomClients =em.createQuery("SELECT cl FROM Client cl WHERE cl.prenomClient like '%"+search+"%' ").getResultList();
        if(prenomClients!=null){
            for (Client idClient :prenomClients) {
                clients.add(idClient);
            }
        }
        List<Client> cinClients =em.createQuery("SELECT cl FROM Client cl WHERE cl.cin like '%"+search+"%' ").getResultList();
        if(cinClients!=null){
            for (Client idClient :cinClients) {
                clients.add(idClient);
            }
        }
        return clients;
    }
    
    public List<Client> Chercher(String search){
        return em.createQuery("SELECT cl FROM Client cl WHERE cl.idClient like '%"+search+"%' OR cl.email like '%"+search+"%' OR cl.cin like '%"+search+"%' OR cl.nomSociete like '%"+search+"%' OR cl.nomClient like '%"+search+"%' OR cl.prenomClient like '%"+search+"%' ").getResultList();
    }
}
