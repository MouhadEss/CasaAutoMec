/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Mouhad ESSABBANE
 */
@Entity
public class TypeVehicule implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String vehicule;
    @OneToMany(mappedBy = "typeVehicule")
    private List<TypeProduit> typeProduits;

    public TypeVehicule() {
    }

    public TypeVehicule(Long id) {
        this.id = id;
    }


    
    

    public List<TypeProduit> getTypeProduits() {
        return typeProduits;
    }

    public void setTypeProduits(List<TypeProduit> typeProduits) {
        this.typeProduits = typeProduits;
    }

    public String getVehicule() {
        return vehicule;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final TypeVehicule other = (TypeVehicule) obj;
        if (!Objects.equals(this.vehicule, other.vehicule)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TypeVehicule{" + "id=" + id + ", vehicule=" + vehicule + '}';
    }

    
    
   
    
}
