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
@Table(name = "bigiphosts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bigiphosts.findAll", query = "SELECT b FROM Bigiphosts b"),
    @NamedQuery(name = "Bigiphosts.findByBigIPHostname", query = "SELECT b FROM Bigiphosts b WHERE b.bigIPHostname = :bigIPHostname"),
    @NamedQuery(name = "Bigiphosts.findByEnv", query = "SELECT b FROM Bigiphosts b WHERE b.env = :env"),
    @NamedQuery(name = "Bigiphosts.findByTier", query = "SELECT b FROM Bigiphosts b WHERE b.tier = :tier"),
    @NamedQuery(name = "Bigiphosts.findByPrimaryIP", query = "SELECT b FROM Bigiphosts b WHERE b.primaryIP = :primaryIP"),
    @NamedQuery(name = "Bigiphosts.findByStandbyIP", query = "SELECT b FROM Bigiphosts b WHERE b.standbyIP = :standbyIP"),
    @NamedQuery(name = "Bigiphosts.findByPrimaryDNS", query = "SELECT b FROM Bigiphosts b WHERE b.primaryDNS = :primaryDNS"),
    @NamedQuery(name = "Bigiphosts.findByStandbyDNS", query = "SELECT b FROM Bigiphosts b WHERE b.standbyDNS = :standbyDNS"),
    @NamedQuery(name = "Bigiphosts.findByBigIPHostscol", query = "SELECT b FROM Bigiphosts b WHERE b.bigIPHostscol = :bigIPHostscol")})
public class Bigiphosts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "BigIPHostname")
    private String bigIPHostname;
    @Size(max = 45)
    @Column(name = "Env")
    private String env;
    @Size(max = 45)
    @Column(name = "Tier")
    private String tier;
    @Size(max = 45)
    @Column(name = "PrimaryIP")
    private String primaryIP;
    @Size(max = 45)
    @Column(name = "StandbyIP")
    private String standbyIP;
    @Size(max = 45)
    @Column(name = "PrimaryDNS")
    private String primaryDNS;
    @Size(max = 45)
    @Column(name = "StandbyDNS")
    private String standbyDNS;
    @Size(max = 45)
    @Column(name = "BigIPHostscol")
    private String bigIPHostscol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bigiphosts")
    private List<Vips> vipsList;

    public Bigiphosts() {
    }

    public Bigiphosts(String bigIPHostname) {
        this.bigIPHostname = bigIPHostname;
    }

    public String getBigIPHostname() {
        return bigIPHostname;
    }

    public void setBigIPHostname(String bigIPHostname) {
        this.bigIPHostname = bigIPHostname;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getPrimaryIP() {
        return primaryIP;
    }

    public void setPrimaryIP(String primaryIP) {
        this.primaryIP = primaryIP;
    }

    public String getStandbyIP() {
        return standbyIP;
    }

    public void setStandbyIP(String standbyIP) {
        this.standbyIP = standbyIP;
    }

    public String getPrimaryDNS() {
        return primaryDNS;
    }

    public void setPrimaryDNS(String primaryDNS) {
        this.primaryDNS = primaryDNS;
    }

    public String getStandbyDNS() {
        return standbyDNS;
    }

    public void setStandbyDNS(String standbyDNS) {
        this.standbyDNS = standbyDNS;
    }

    public String getBigIPHostscol() {
        return bigIPHostscol;
    }

    public void setBigIPHostscol(String bigIPHostscol) {
        this.bigIPHostscol = bigIPHostscol;
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
        hash += (bigIPHostname != null ? bigIPHostname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bigiphosts)) {
            return false;
        }
        Bigiphosts other = (Bigiphosts) object;
        if ((this.bigIPHostname == null && other.bigIPHostname != null) || (this.bigIPHostname != null && !this.bigIPHostname.equals(other.bigIPHostname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.Bigiphosts[ bigIPHostname=" + bigIPHostname + " ]";
    }
    
}
