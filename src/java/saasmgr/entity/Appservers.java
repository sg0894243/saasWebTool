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
@Table(name = "appservers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appservers.findAll", query = "SELECT a FROM Appservers a"),
    @NamedQuery(name = "Appservers.findByAppCompId", query = "SELECT a FROM Appservers a WHERE a.appCompId = :appCompId"),
    @NamedQuery(name = "Appservers.findByType", query = "SELECT a FROM Appservers a WHERE a.type = :type"),
    @NamedQuery(name = "Appservers.findByOwner", query = "SELECT a FROM Appservers a WHERE a.owner = :owner"),
    @NamedQuery(name = "Appservers.findByStorage", query = "SELECT a FROM Appservers a WHERE a.storage = :storage"),
    @NamedQuery(name = "Appservers.findByRequiredRAM", query = "SELECT a FROM Appservers a WHERE a.requiredRAM = :requiredRAM")})
public class Appservers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "appCompId")
    private Integer appCompId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Type")
    private String type;
    @Size(max = 45)
    @Column(name = "Owner")
    private String owner;
    @Size(max = 45)
    @Column(name = "Storage")
    private String storage;
    @Size(max = 45)
    @Column(name = "RequiredRAM")
    private String requiredRAM;
    @JoinColumn(name = "Host", referencedColumnName = "HostName")
    @ManyToOne(optional = false)
    private Saashosts host;
    @JoinColumn(name = "appCompId", referencedColumnName = "compId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Components components;

    public Appservers() {
    }

    public Appservers(Integer appCompId) {
        this.appCompId = appCompId;
    }

    public Appservers(Integer appCompId, String type) {
        this.appCompId = appCompId;
        this.type = type;
    }

    public Integer getAppCompId() {
        return appCompId;
    }

    public void setAppCompId(Integer appCompId) {
        this.appCompId = appCompId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getRequiredRAM() {
        return requiredRAM;
    }

    public void setRequiredRAM(String requiredRAM) {
        this.requiredRAM = requiredRAM;
    }

    public Saashosts getHost() {
        return host;
    }

    public void setHost(Saashosts host) {
        this.host = host;
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
        hash += (appCompId != null ? appCompId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appservers)) {
            return false;
        }
        Appservers other = (Appservers) object;
        if ((this.appCompId == null && other.appCompId != null) || (this.appCompId != null && !this.appCompId.equals(other.appCompId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.Appservers[ appCompId=" + appCompId + " ]";
    }
    
}
