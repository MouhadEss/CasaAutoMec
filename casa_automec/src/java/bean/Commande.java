/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Entity
public class Commande implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Client client;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datecommande;
    private boolean vueC;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateVueC;
    private boolean traiter;
    @OneToMany(mappedBy = "commande")
    private List<CommandeItem> commandeItems;
    private float prixTotal;

    public Commande() {
    }

    public Commande(Long id) {
        this.id = id;
    }

    public boolean isTraiter() {
        return traiter;
    }

    public void setTraiter(boolean traiter) {
        this.traiter = traiter;
    }

    
    
    public boolean isVueC() {
        return vueC;
    }

    public void setVueC(boolean vueC) {
        this.vueC = vueC;
    }

    public Date getDateVueC() {
        return dateVueC;
    }

    public void setDateVueC(Date dateVueC) {
        this.dateVueC = dateVueC;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(Date datecommande) {
        this.datecommande = datecommande;
    }

    public List<CommandeItem> getCommandeItems() {
        return commandeItems;
    }

    public void setCommandeItems(List<CommandeItem> commandeItems) {
        this.commandeItems = commandeItems;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", client=" + client + ", datecommande=" + datecommande + ", commandeItems=" + commandeItems + ", prixTotal=" + prixTotal + '}';
    }
    
    

    
    
}
