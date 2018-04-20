/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Personne;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author IlyassElfouih
 */
@Stateless
public class PersonneFacade extends AbstractFacade<Personne> {

    @PersistenceContext(unitName = "covoiturageV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonneFacade() {
        super(Personne.class);
    }
       public int login(Personne selected) {
        Personne loadedPersonne= find(selected.getEmail());
        if(loadedPersonne==null)
            return -1;
        else if(selected.getPassword()== loadedPersonne.getPassword())
            return -2;
        else 
            return 1;
    }
}
