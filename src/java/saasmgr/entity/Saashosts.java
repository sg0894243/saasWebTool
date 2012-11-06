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
@Table(name = "saashosts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Saashosts.findAll", query = "SELECT s FROM Saashosts s"),
    @NamedQuery(name = "Saashosts.findByHostName", query = "SELECT s FROM Saashosts s WHERE s.hostName = :hostName"),
    @NamedQuery(name = "Saashosts.findByType", query = "SELECT s FROM Saashosts s WHERE s.type = :type"),
    @NamedQuery(name = "Saashosts.findByDns", query = "SELECT s FROM Saashosts s WHERE s.dns = :dns"),
    @NamedQuery(name = "Saashosts.findByRootIP", query = "SELECT s FROM Saashosts s WHERE s.rootIP = :rootIP"),
    @NamedQuery(name = "Saashosts.findByTier", query = "SELECT s FROM Saashosts s WHERE s.tier = :tier"),
    @NamedQuery(name = "Saashosts.findByEnv", query = "SELECT s FROM Saashosts s WHERE s.env = :env"),
    @NamedQuery(name = "Saashosts.findByRegion", query = "SELECT s FROM Saashosts s WHERE s.region = :region"),
    @NamedQuery(name = "Saashosts.findByCpu", query = "SELECT s FROM Saashosts s WHERE s.cpu = :cpu"),
    @NamedQuery(name = "Saashosts.findByRam", query = "SELECT s FROM Saashosts s WHERE s.ram = :ram"),
    @NamedQuery(name = "Saashosts.findByOs", query = "SELECT s FROM Saashosts s WHERE s.os = :os"),
    @NamedQuery(name = "Saashosts.findByBuildoutSR", query = "SELECT s FROM Saashosts s WHERE s.buildoutSR = :buildoutSR")})
public class Saashosts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "HostName")
    private String hostName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DNS")
    private String dns;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "RootIP")
    private String rootIP;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Tier")
    private String tier;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Env")
    private String env;
    @Size(max = 45)
    @Column(name = "Region")
    private String region;
    @Size(max = 45)
    @Column(name = "CPU")
    private String cpu;
    @Size(max = 45)
    @Column(name = "RAM")
    private String ram;
    @Size(max = 45)
    @Column(name = "OS")
    private String os;
    @Size(max = 45)
    @Column(name = "BuildoutSR")
    private String buildoutSR;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "host")
    private List<Appservers> appserversList;
    @OneToMany(mappedBy = "primaryHost")
    private List<Qmanagers> qmanagersList;
    @OneToMany(mappedBy = "failoverHost")
    private List<Qmanagers> qmanagersList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "failoverHost")
    private List<Dbs> dbsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "primaryHost")
    private List<Dbs> dbsList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "saashosts")
    private List<Softwareinstalls> softwareinstallsList;

    public Saashosts() {
    }

    public Saashosts(String hostName) {
        this.hostName = hostName;
    }

    public Saashosts(String hostName, String type, String dns, String rootIP, String tier, String env) {
        this.hostName = hostName;
        this.type = type;
        this.dns = dns;
        this.rootIP = rootIP;
        this.tier = tier;
        this.env = env;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public String getRootIP() {
        return rootIP;
    }

    public void setRootIP(String rootIP) {
        this.rootIP = rootIP;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBuildoutSR() {
        return buildoutSR;
    }

    public void setBuildoutSR(String buildoutSR) {
        this.buildoutSR = buildoutSR;
    }

    @XmlTransient
    public List<Appservers> getAppserversList() {
        return appserversList;
    }

    public void setAppserversList(List<Appservers> appserversList) {
        this.appserversList = appserversList;
    }

    @XmlTransient
    public List<Qmanagers> getQmanagersList() {
        return qmanagersList;
    }

    public void setQmanagersList(List<Qmanagers> qmanagersList) {
        this.qmanagersList = qmanagersList;
    }

    @XmlTransient
    public List<Qmanagers> getQmanagersList1() {
        return qmanagersList1;
    }

    public void setQmanagersList1(List<Qmanagers> qmanagersList1) {
        this.qmanagersList1 = qmanagersList1;
    }

    @XmlTransient
    public List<Dbs> getDbsList() {
        return dbsList;
    }

    public void setDbsList(List<Dbs> dbsList) {
        this.dbsList = dbsList;
    }

    @XmlTransient
    public List<Dbs> getDbsList1() {
        return dbsList1;
    }

    public void setDbsList1(List<Dbs> dbsList1) {
        this.dbsList1 = dbsList1;
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
        hash += (hostName != null ? hostName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Saashosts)) {
            return false;
        }
        Saashosts other = (Saashosts) object;
        if ((this.hostName == null && other.hostName != null) || (this.hostName != null && !this.hostName.equals(other.hostName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.Saashosts[ hostName=" + hostName + " ]";
    }
    
}
