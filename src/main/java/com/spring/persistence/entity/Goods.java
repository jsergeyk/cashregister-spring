/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.persistence.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "goods")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Goods.findAll", query = "SELECT g FROM Goods g")
    , @NamedQuery(name = "Goods.findById", query = "SELECT g FROM Goods g WHERE g.id = :id")
    , @NamedQuery(name = "Goods.findByCode", query = "SELECT g FROM Goods g WHERE g.code = :code")
    , @NamedQuery(name = "Goods.findByName", query = "SELECT g FROM Goods g WHERE g.name = :name")
    , @NamedQuery(name = "Goods.findByQuant", query = "SELECT g FROM Goods g WHERE g.quant = :quant")
    , @NamedQuery(name = "Goods.findByMeasure", query = "SELECT g FROM Goods g WHERE g.measure = :measure")
    , @NamedQuery(name = "Goods.findByComments", query = "SELECT g FROM Goods g WHERE g.comments = :comments")})
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "code")
    private int code;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "quant")
    private double quant;
    @Basic(optional = false)
    private double price;
    @Basic(optional = false)
    @Column(name = "measure")
    private String measure;
    @Column(name = "comments")
    private String comments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "goods")
    private Collection<Checkspec> checkspecCollection;

    public Goods() {
    }

    public Goods(Long id) {
        this.id = id;
    }

    public Goods(Long id, int code, String name, double quant, String measure) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.quant = quant;
        this.measure = measure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuant() {
        return quant;
    }

    public void setQuant(double quant) {
        this.quant = quant;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @XmlTransient
    public Collection<Checkspec> getCheckspecCollection() {
        return checkspecCollection;
    }

    public void setCheckspecCollection(Collection<Checkspec> checkspecCollection) {
        this.checkspecCollection = checkspecCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
        Goods other = (Goods) obj;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
