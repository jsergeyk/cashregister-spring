/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.persistence.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SergeyK
 */
@Entity
@Table(name = "chec")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chec.findAll", query = "SELECT c FROM Chec c")
    , @NamedQuery(name = "Chec.findById", query = "SELECT c FROM Chec c WHERE c.id = :id")
    , @NamedQuery(name = "Chec.findByCrtime", query = "SELECT c FROM Chec c WHERE c.crtime = :crtime")
    , @NamedQuery(name = "Chec.findByTotal", query = "SELECT c FROM Chec c WHERE c.total = :total")
    , @NamedQuery(name = "Chec.findByDiscount", query = "SELECT c FROM Chec c WHERE c.discount = :discount")
    , @NamedQuery(name = "Chec.findByCanceled", query = "SELECT c FROM Chec c WHERE c.canceled = :canceled")
    , @NamedQuery(name = "Chec.findByRegistration", query = "SELECT c FROM Chec c WHERE c.registration = :registration")
    , @NamedQuery(name = "Chec.findByCreator", query = "SELECT c FROM Chec c WHERE c.creator = :creator")
    })
public class Chec implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "crtime", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crtime;
    @Basic(optional = false)
    @Column(name = "total")
    private double total;
    @Basic(optional = false)
    @Column(name = "discount")
    private double discount;
    @Basic(optional = false)
    @Column(name = "canceled", columnDefinition = "integer default 0")
    private Integer canceled;
    @Column(name = "registration")
    private Integer registration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "check")
    private Collection<Checkspec> checkspecCollection;
    @JoinColumn(name = "creator", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User creator;

    public Chec() {
    }

    public Chec(Long id) {
        this.id = id;
    }

    public Chec(Long id, Date crtime, double total, double discount, Integer canceled, User creator) {
        this.id = id;
        this.crtime = crtime;
        this.total = total;
        this.discount = discount;
        this.canceled = canceled;
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCrtime() {
        return crtime;
    }

    public void setCrtime(Date crtime) {
        this.crtime = crtime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Integer getCanceled() {
        return canceled;
    }

    public void setCanceled(Integer canceled) {
        this.canceled = canceled;
    }

    public Integer getRegistration() {
        return registration;
    }

    public void setRegistration(Integer registration) {
        this.registration = registration;
    }

    @XmlTransient
    public Collection<Checkspec> getCheckspecCollection() {
        return checkspecCollection;
    }

    public void setCheckspecCollection(Collection<Checkspec> checkspecCollection) {
        this.checkspecCollection = checkspecCollection;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
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
		Chec other = (Chec) obj;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
		return true;
	}
}
