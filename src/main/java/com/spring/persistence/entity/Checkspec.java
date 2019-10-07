/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.persistence.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "checkspec")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Checkspec.findAll", query = "SELECT c FROM Checkspec c")
    , @NamedQuery(name = "Checkspec.findById", query = "SELECT c FROM Checkspec c WHERE c.id = :id")
    , @NamedQuery(name = "Checkspec.findByQuant", query = "SELECT c FROM Checkspec c WHERE c.quant = :quant")
    , @NamedQuery(name = "Checkspec.findByPrice", query = "SELECT c FROM Checkspec c WHERE c.price = :price")
    , @NamedQuery(name = "Checkspec.findByTotal", query = "SELECT c FROM Checkspec c WHERE c.total = :total")
    , @NamedQuery(name = "Checkspec.findByNds", query = "SELECT c FROM Checkspec c WHERE c.nds = :nds")
    , @NamedQuery(name = "Checkspec.findByNdstotal", query = "SELECT c FROM Checkspec c WHERE c.ndstotal = :ndstotal")
    , @NamedQuery(name = "Checkspec.findByCanceled", query = "SELECT c FROM Checkspec c WHERE c.canceled = :canceled")})
public class Checkspec implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "quant")
    private double quant;
    @Basic(optional = false)
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @Column(name = "total")
    private double total;
    @Column(name = "nds")
    private Integer nds;
    @Basic(optional = false)
    @Column(name = "ndstotal")
    private double ndstotal;
    @Basic(optional = false)
    @Column(name = "canceled", columnDefinition = "integer default 0")
    private Integer canceled;
    @JoinColumn(name = "id_check", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Chec check;
    @JoinColumn(name = "id_good", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Goods goods;

    public Checkspec() {
    }

    public Checkspec(Long id) {
        this.id = id;
    }

    public Checkspec(Long id, double quant, double price, double total, double ndstotal, Integer canceled, Goods goods) {
        this.id = id;
        this.quant = quant;
        this.price = price;
        this.total = total;
        this.ndstotal = ndstotal;
        this.canceled = canceled;
        this.goods = goods;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Integer getNds() {
        return nds;
    }

    public void setNds(Integer nds) {
        this.nds = nds;
    }

    public double getNdstotal() {
        return ndstotal;
    }

    public void setNdstotal(double ndstotal) {
        this.ndstotal = ndstotal;
    }

    public Integer getCanceled() {
        return canceled;
    }

    public void setCanceled(Integer canceled) {
        this.canceled = canceled;
    }

    public Chec getCheck() {
        return check;
    }

    public void setCheck(Chec check) {
        this.check = check;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
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
        Checkspec other = (Checkspec) obj;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
