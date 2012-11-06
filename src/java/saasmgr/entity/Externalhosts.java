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
@Table(name = "externalhosts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Externalhosts.findAll", query = "SELECT e FROM Externalhosts e"),
    @NamedQuery(name = "Externalhosts.findByExtCompId", query = "SELECT e FROM Externalhosts e WHERE e.extCompId = :extCompId"),
    @NamedQuery(name = "Externalhosts.findByLocation", query = "SELECT e FROM Externalhosts e WHERE e.location = :location"),
    @NamedQuery(name = "Externalhosts.findByEnv", query = "SELECT e FROM Externalhosts e WHERE e.env = :env")})
public class Externalhosts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "extCompId")
    private Integer extCompId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Location")
    private String location;
    @Size(max = 45)
    @Column(name = "Env")
    private String env;
    @JoinColumn(name = "extCompId", referencedColumnName = "compId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Components components;

    public Externalhosts() {
    }

    public Externalhosts(Integer extCompId) {
        this.extCompId = extCompId;
    }

    public Externalhosts(Integer extCompId, String location) {
        this.extCompId = extCompId;
        this.location = location;
    }

    public Integer getExtCompId() {
        return extCompId;
    }

    public void setExtCompId(Integer extCompId) {
        this.extCompId = extCompId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (extCompId != null ? extCompId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Externalhosts)) {
            return false;
        }
        Externalhosts other = (Externalhosts) object;
        if ((this.extCompId == null && other.extCompId != null) || (this.extCompId != null && !this.extCompId.equals(other.extCompId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.Externalhosts[ extCompId=" + extCompId + " ]";
    }
    
}
