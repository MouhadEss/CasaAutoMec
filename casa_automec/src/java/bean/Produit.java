/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Entity
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String reference;
    private float prix;
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAjout;
    private Long nbrStock;
    private float remise;
    @OneToMany(mappedBy = "produit")
    private List<ImageProduit> imageProduits;

    @ManyToOne
    private TypeProduit typeProduit;

    public Produit() {
    }

    public Produit(String reference) {
        this.reference = reference;
    }

    public Long getNbrStock() {
        return nbrStock;
    }

    public void setNbrStock(Long nbrStock) {
        this.nbrStock = nbrStock;
    }

    public float getRemise() {
        return remise;
    }

    public void setRemise(float remise) {
        this.remise = remise;
    }
    
    

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public List<ImageProduit> getImageProduits() {
        return imageProduits;
    }

    public void setImageProduits(List<ImageProduit> imageProduits) {
        this.imageProduits = imageProduits;
    }

    public TypeProduit getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(TypeProduit typeProduit) {
        this.typeProduit = typeProduit;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (!Objects.equals(this.reference, other.reference)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit{" + "reference=" + reference + ", prix=" + prix + ", description=" + description + ", dateAjout=" + dateAjout + ", imageProduits=" + imageProduits + ", typeProduit=" + typeProduit + '}';
    }

    
    

   

   
}
