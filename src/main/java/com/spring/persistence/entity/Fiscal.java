package com.spring.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SergeyK
 */
@Entity
@Table(name = "fiscal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fiscal.findAll", query = "SELECT f FROM Fiscal f")
    , @NamedQuery(name = "Fiscal.findById", query = "SELECT f FROM Fiscal f WHERE f.id = :id")
    , @NamedQuery(name = "Fiscal.findByTotal", query = "SELECT f FROM Fiscal f WHERE f.total = :total")})
@NamedNativeQuery(name = "Fiscal.createXReport", query = "SELECT current_timestamp() AS printtime," +
		"	cancel.countcanceled," + 
		"	SUM(COUNT(DISTINCT c.id)) OVER() AS countcheck," + 
		"	s.nds," +
		"	SUM(s.total) AS total," + 
		"	round(SUM(s.ndstotal), 2) AS ndstotal, " +
		"	round(SUM(SUM(s.total)) OVER(), 2) AS sumtotal, " +  
		"	round(SUM(SUM(s.ndstotal)) OVER(), 2) AS sumndstotal " +
		"	FROM checkspec s" + 
		"	INNER JOIN chec c ON c.id = s.id_check" +
		"	LEFT JOIN (SELECT COUNT(c1.canceled) AS countcanceled FROM cashreg.check c1 " + 
		"			 		WHERE c1.canceled = 1 /*AND cast(c1.crtime as date) = current_date()*/) cancel ON true" +
		"	WHERE c.canceled = 0 AND s.canceled = 0 /*AND cast(c.crtime as date) = current_date()*/" + 	//закоментировано для debug-а
		"	GROUP BY s.nds, cancel.countcanceled")
public class Fiscal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;

    public Fiscal() {
    }

    public Fiscal(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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
        Fiscal other = (Fiscal) obj;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
