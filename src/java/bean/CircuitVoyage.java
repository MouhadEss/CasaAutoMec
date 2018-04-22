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
 * @author IlyassElfouih
 */
@Entity
public class CircuitVoyage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Voyage voyage ;
    @ManyToOne
    private Ville villeDep; 
    @ManyToOne
    private Ville villeArr;
    private int num ;
    private double prix;
    @OneToOne(mappedBy = "circuitVoyage")
    private TypeRoute typeRoute;
    @OneToOne(mappedBy = "circuitVoyage")
    private Place place;

    public CircuitVoyage() {
    }

    public CircuitVoyage(Long id) {
        this.id = id;
    }

    public CircuitVoyage(Long id, Ville villeDep, int num, double prix) {
        this.id = id;
        this.villeDep = villeDep;
        this.num = num;
        this.prix = prix;
    }

    public CircuitVoyage(Voyage voyage, Ville villeDep, Ville villeArr, int num, double prix) {
        this.voyage=voyage;
        this.villeDep = villeDep;
        this.villeArr=villeArr;
        this.num = num;
        this.prix = prix;
    }

    public CircuitVoyage(Ville villeDep, Ville villeArr, double prix) {
        this.villeDep = villeDep;
        this.villeArr = villeArr;
        this.prix = prix;
    }
    
    

    public Ville getVilleArr() {
        return villeArr;
    }

    public void setVilleArr(Ville villeArr) {
        this.villeArr = villeArr;
    }

    
    
    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    public Ville getVilleDep() {
        return villeDep;
    }

    public void setVilleDep(Ville villeDep) {
        this.villeDep = villeDep;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CircuitVoyage)) {
            return false;
        }
        CircuitVoyage other = (CircuitVoyage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CircuitVoyage{" + "id=" + id + ", voyage=" + voyage + ", villeDep=" + villeDep + ", villeArr=" + villeArr + ", num=" + num + ", prix=" + prix + '}';
    }

    
    
}