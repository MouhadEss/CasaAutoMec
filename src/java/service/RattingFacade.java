/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Personne;
import bean.Ratting;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author IlyassElfouih
 */
@Stateless
public class RattingFacade extends AbstractFacade<Ratting> {

    @PersistenceContext(unitName = "covoiturageV2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RattingFacade() {
        super(Ratting.class);
    }

    public List<Integer> findScoreByPersonne(Personne personne) {

        return getEntityManager().createQuery("select r.score from Ratting r"
                + " where r.personne.email='" + personne.getEmail() + "'").getResultList();
    }

    public double calculeScoreMoyen(Personne personne) {
        List<Integer> res = findScoreByPersonne(personne);
        System.out.println(res);
        if (res != null) {
            float scoreMoyen = 0;
            int somme = 0;
            for (int i = 0; i < res.size(); i++) {
                if (null != res.get(i)) {
                    somme += res.get(i);
                }
            }
            scoreMoyen = somme / (res.size());
            System.out.println("le score moyenne est " + scoreMoyen);
            return scoreMoyen;
        } else {
            return 0.0;
        }
    }
}
