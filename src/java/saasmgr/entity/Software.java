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
@Table(name = "software")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Software.findAll", query = "SELECT s FROM Software s"),
    @NamedQuery(name = "Software.findByIdSoftware", query = "SELECT s FROM Software s WHERE s.idSoftware = :idSoftware"),
    @NamedQuery(name = "Software.findByName", query = "SELECT s FROM Software s WHERE s.name = :name"),
    @NamedQuery(name = "Software.findByDescription", query = "SELECT s FROM Software s WHERE s.description = :description"),
    @NamedQuery(name = "Software.findByVersion", query = "SELECT s FROM Software s WHERE s.version = :version")})
public class Software implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSoftware")
    private Integer idSoftware;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Name")
    private String name;
    @Size(max = 60)
    @Column(name = "Description")
    private String description;
    @Size(max = 45)
    @Column(name = "Version")
    private String version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "software")
    private List<Softwareinstalls> softwareinstallsList;

    public Software() {
    }

    public Software(Integer idSoftware) {
        this.idSoftware = idSoftware;
    }

    public Software(Integer idSoftware, String name) {
        this.idSoftware = idSoftware;
        this.name = name;
    }

    public Integer getIdSoftware() {
        return idSoftware;
    }

    public void setIdSoftware(Integer idSoftware) {
        this.idSoftware = idSoftware;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @XmlTransient
    public List<Softwareinstalls> getSoftwareinstallsList() {
        return softwareinstallsList;
    }

    public void setSoftwareinstallsList(List<Softwareinstalls> softwareinstallsList) {
        this.softwareinstallsList = softwareinstallsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSoftware != null ? idSoftware.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Software)) {
            return false;
        }
        Software other = (Software) object;
        if ((this.idSoftware == null && other.idSoftware != null) || (this.idSoftware != null && !this.idSoftware.equals(other.idSoftware))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.Software[ idSoftware=" + idSoftware + " ]";
    }
    
}
