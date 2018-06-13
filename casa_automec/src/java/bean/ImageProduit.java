/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Entity
public class ImageProduit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameimg;
    private String typeimg;
    @ManyToOne
    private Produit produit;

    public ImageProduit() {
    }

    public ImageProduit(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameimg() {
        return nameimg;
    }

    public void setNameimg(String nameimg) {
        this.nameimg = nameimg;
    }

    public String getTypeimg() {
        return typeimg;
    }

    public void setTypeimg(String typeimg) {
        this.typeimg = typeimg;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final ImageProduit other = (ImageProduit) obj;
        if (!Objects.equals(this.nameimg, other.nameimg)) {
            return false;
        }
        if (!Objects.equals(this.typeimg, other.typeimg)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ImageProduit{" + "id=" + id + ", nameimg=" + nameimg + ", typeimg=" + typeimg + ", produit=" + produit + '}';
    }

  
    
    
    
}
