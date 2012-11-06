/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package saasmgr.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SG0894243
 */
@Entity
@Table(name = "qmanagers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qmanagers.findAll", query = "SELECT q FROM Qmanagers q"),
    @NamedQuery(name = "Qmanagers.findByIdComponent", query = "SELECT q FROM Qmanagers q WHERE q.idComponent = :idComponent"),
    @NamedQuery(name = "Qmanagers.findByName", query = "SELECT q FROM Qmanagers q WHERE q.name = :name"),
    @NamedQuery(name = "Qmanagers.findByPurpose", query = "SELECT q FROM Qmanagers q WHERE q.purpose = :purpose")})
public class Qmanagers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idComponent")
    private Integer idComponent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Name")
    private String name;
    @Size(max = 45)
    @Column(name = "Purpose")
    private String purpose;
    @JoinColumn(name = "idComponent", referencedColumnName = "compId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Components components;
    @JoinColumn(name = "PrimaryHost", referencedColumnName = "HostName")
    @ManyToOne
    private Saashosts primaryHost;
    @JoinColumn(name = "FailoverHost", referencedColumnName = "HostName")
    @ManyToOne
    private Saashosts failoverHost;

    public Qmanagers() {
    }

    public Qmanagers(Integer idComponent) {
        this.idComponent = idComponent;
    }

    public Qmanagers(Integer idComponent, String name) {
        this.idComponent = idComponent;
        this.name = name;
    }

    public Integer getIdComponent() {
        return idComponent;
    }

    public void setIdComponent(Integer idComponent) {
        this.idComponent = idComponent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public Saashosts getPrimaryHost() {
        return primaryHost;
    }

    public void setPrimaryHost(Saashosts primaryHost) {
        this.primaryHost = primaryHost;
    }

    public Saashosts getFailoverHost() {
        return failoverHost;
    }

    public void setFailoverHost(Saashosts failoverHost) {
        this.failoverHost = failoverHost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComponent != null ? idComponent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Qmanagers)) {
            return false;
        }
        Qmanagers other = (Qmanagers) object;
        if ((this.idComponent == null && other.idComponent != null) || (this.idComponent != null && !this.idComponent.equals(other.idComponent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.Qmanagers[ idComponent=" + idComponent + " ]";
    }
    
}
