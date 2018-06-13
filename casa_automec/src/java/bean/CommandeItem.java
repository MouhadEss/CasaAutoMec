/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Entity
public class CommandeItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Commande commande;
    @OneToOne
    private Produit produit;
    private int nbrProduit;
    private float prixItems;

    public CommandeItem() {
    }

    public CommandeItem(Long id) {
        this.id = id;
    }

    public float getPrixItems() {
        return prixItems;
    }

    public void setPrixItems(float prixItems) {
        this.prixItems = prixItems;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getNbrProduit() {
        return nbrProduit;
    }

    public void setNbrProduit(int nbrProduit) {
        this.nbrProduit = nbrProduit;
    }

    @Override
    public String toString() {
        return "CommandeItem{" + "id=" + id + ", commande=" + commande + ", produit=" + produit + ", nbrProduit=" + nbrProduit + '}';
    }
    
    
}
