/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package saasmgr.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SG0894243
 */
@Entity
@Table(name = "customers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customers.findAll", query = "SELECT c FROM Customers c"),
    @NamedQuery(name = "Customers.findByCustomerCode", query = "SELECT c FROM Customers c WHERE c.customerCode = :customerCode"),
    @NamedQuery(name = "Customers.findByCustomerFullName", query = "SELECT c FROM Customers c WHERE c.customerFullName = :customerFullName"),
    @NamedQuery(name = "Customers.findByRegion", query = "SELECT c FROM Customers c WHERE c.region = :region")})
public class Customers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CustomerCode")
    private String customerCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CustomerFullName")
    private String customerFullName;
    @Size(max = 45)
    @Column(name = "Region")
    private String region;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Projects> projectsList;

    public Customers() {
    }

    public Customers(String customerCode) {
        this.customerCode = customerCode;
    }

    public Customers(String customerCode, String customerFullName) {
        this.customerCode = customerCode;
        this.customerFullName = customerFullName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @XmlTransient
    public List<Projects> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(List<Projects> projectsList) {
        this.projectsList = projectsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerCode != null ? customerCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customers)) {
            return false;
        }
        Customers other = (Customers) object;
        if ((this.customerCode == null && other.customerCode != null) || (this.customerCode != null && !this.customerCode.equals(other.customerCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.Customers[ customerCode=" + customerCode + " ]";
    }
    
}
