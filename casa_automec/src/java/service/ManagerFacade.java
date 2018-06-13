/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Manager;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Stateless
public class ManagerFacade extends AbstractFacade<Manager> {

    @PersistenceContext(unitName = "casa_automecPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManagerFacade() {
        super(Manager.class);
    }

    public Manager findManagerById(String idAdmin) {
        return (Manager) getEntityManager().createQuery("SELECT ma FROM Manager ma WHERE ma.idAdmin like '" + idAdmin + "'").getSingleResult();
    }

    public int verifAdmin(String idAdmin, String password) {
        Manager manager = findManagerById(idAdmin);
        System.out.println(manager);
        if (manager.getPassword().equals(password)) {
            return 1;
        } else {
            return 2;
        }
    }

}
