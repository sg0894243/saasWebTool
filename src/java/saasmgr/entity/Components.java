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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "components")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Components.findAll", query = "SELECT c FROM Components c"),
    @NamedQuery(name = "Components.findByCompId", query = "SELECT c FROM Components c WHERE c.compId = :compId"),
    @NamedQuery(name = "Components.findByDns", query = "SELECT c FROM Components c WHERE c.dns = :dns"),
    @NamedQuery(name = "Components.findByListeningIP", query = "SELECT c FROM Components c WHERE c.listeningIP = :listeningIP"),
    @NamedQuery(name = "Components.findByPort", query = "SELECT c FROM Components c WHERE c.port = :port"),
    @NamedQuery(name = "Components.findByStatus", query = "SELECT c FROM Components c WHERE c.status = :status"),
    @NamedQuery(name = "Components.findByComponentscol", query = "SELECT c FROM Components c WHERE c.componentscol = :componentscol")})
public class Components implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "compId")
    private Integer compId;
    @Size(max = 45)
    @Column(name = "DNS")
    private String dns;
    @Size(max = 45)
    @Column(name = "ListeningIP")
    private String listeningIP;
    @Size(max = 45)
    @Column(name = "Port")
    private String port;
    @Size(max = 45)
    @Column(name = "Status")
    private String status;
    @Size(max = 45)
    @Column(name = "Componentscol")
    private String componentscol;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "components")
    private Externalhosts externalhosts;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "components")
    private Appservers appservers;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "components")
    private Qmanagers qmanagers;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "components")
    private Dbs dbs;
    @JoinColumn(name = "compGroup", referencedColumnName = "compGroupId")
    @ManyToOne(optional = false)
    private Componentgroups compGroup;
    @JoinColumn(name = "Project", referencedColumnName = "projectId")
    @ManyToOne(optional = false)
    private Projects project;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "components")
    private List<Vips> vipsList;

    public Components() {
    }

    public Components(Integer compId) {
        this.compId = compId;
    }

    public Integer getCompId() {
        return compId;
    }

    public void setCompId(Integer compId) {
        this.compId = compId;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public String getListeningIP() {
        return listeningIP;
    }

    public void setListeningIP(String listeningIP) {
        this.listeningIP = listeningIP;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComponentscol() {
        return componentscol;
    }

    public void setComponentscol(String componentscol) {
        this.componentscol = componentscol;
    }

    public Externalhosts getExternalhosts() {
        return externalhosts;
    }

    public void setExternalhosts(Externalhosts externalhosts) {
        this.externalhosts = externalhosts;
    }

    public Appservers getAppservers() {
        return appservers;
    }

    public void setAppservers(Appservers appservers) {
        this.appservers = appservers;
    }

    public Qmanagers getQmanagers() {
        return qmanagers;
    }

    public void setQmanagers(Qmanagers qmanagers) {
        this.qmanagers = qmanagers;
    }

    public Dbs getDbs() {
        return dbs;
    }

    public void setDbs(Dbs dbs) {
        this.dbs = dbs;
    }

    public Componentgroups getCompGroup() {
        return compGroup;
    }

    public void setCompGroup(Componentgroups compGroup) {
        this.compGroup = compGroup;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    @XmlTransient
    public List<Vips> getVipsList() {
        return vipsList;
    }

    public void setVipsList(List<Vips> vipsList) {
        this.vipsList = vipsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compId != null ? compId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Components)) {
            return false;
        }
        Components other = (Components) object;
        if ((this.compId == null && other.compId != null) || (this.compId != null && !this.compId.equals(other.compId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.Components[ compId=" + compId + " ]";
    }
    
}
