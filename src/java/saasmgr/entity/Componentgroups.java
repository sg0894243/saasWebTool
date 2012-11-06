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
@Table(name = "componentgroups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Componentgroups.findAll", query = "SELECT c FROM Componentgroups c"),
    @NamedQuery(name = "Componentgroups.findByCompGroupId", query = "SELECT c FROM Componentgroups c WHERE c.compGroupId = :compGroupId"),
    @NamedQuery(name = "Componentgroups.findByName", query = "SELECT c FROM Componentgroups c WHERE c.name = :name"),
    @NamedQuery(name = "Componentgroups.findByDescription", query = "SELECT c FROM Componentgroups c WHERE c.description = :description"),
    @NamedQuery(name = "Componentgroups.findByProtocol", query = "SELECT c FROM Componentgroups c WHERE c.protocol = :protocol")})
public class Componentgroups implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "compGroupId")
    private Integer compGroupId;
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @Size(max = 45)
    @Column(name = "Description")
    private String description;
    @Size(max = 45)
    @Column(name = "Protocol")
    private String protocol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "source")
    private List<Connections> connectionsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destination")
    private List<Connections> connectionsList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compGroup")
    private List<Components> componentsList;

    public Componentgroups() {
    }

    public Componentgroups(Integer compGroupId) {
        this.compGroupId = compGroupId;
    }

    public Integer getCompGroupId() {
        return compGroupId;
    }

    public void setCompGroupId(Integer compGroupId) {
        this.compGroupId = compGroupId;
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

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @XmlTransient
    public List<Connections> getConnectionsList() {
        return connectionsList;
    }

    public void setConnectionsList(List<Connections> connectionsList) {
        this.connectionsList = connectionsList;
    }

    @XmlTransient
    public List<Connections> getConnectionsList1() {
        return connectionsList1;
    }

    public void setConnectionsList1(List<Connections> connectionsList1) {
        this.connectionsList1 = connectionsList1;
    }

    @XmlTransient
    public List<Components> getComponentsList() {
        return componentsList;
    }

    public void setComponentsList(List<Components> componentsList) {
        this.componentsList = componentsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compGroupId != null ? compGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Componentgroups)) {
            return false;
        }
        Componentgroups other = (Componentgroups) object;
        if ((this.compGroupId == null && other.compGroupId != null) || (this.compGroupId != null && !this.compGroupId.equals(other.compGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.Componentgroups[ compGroupId=" + compGroupId + " ]";
    }
    
}
