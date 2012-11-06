/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package saasmgr.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SG0894243
 */
@Entity
@Table(name = "vips")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vips.findAll", query = "SELECT v FROM Vips v"),
    @NamedQuery(name = "Vips.findByVipCompId", query = "SELECT v FROM Vips v WHERE v.vipsPK.vipCompId = :vipCompId"),
    @NamedQuery(name = "Vips.findByHost", query = "SELECT v FROM Vips v WHERE v.vipsPK.host = :host"),
    @NamedQuery(name = "Vips.findByPersistence", query = "SELECT v FROM Vips v WHERE v.persistence = :persistence"),
    @NamedQuery(name = "Vips.findByIRule", query = "SELECT v FROM Vips v WHERE v.iRule = :iRule"),
    @NamedQuery(name = "Vips.findByDescription", query = "SELECT v FROM Vips v WHERE v.description = :description"),
    @NamedQuery(name = "Vips.findByVIPScol", query = "SELECT v FROM Vips v WHERE v.vIPScol = :vIPScol")})
public class Vips implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VipsPK vipsPK;
    @Size(max = 45)
    @Column(name = "Persistence")
    private String persistence;
    @Size(max = 45)
    @Column(name = "iRule")
    private String iRule;
    @Size(max = 60)
    @Column(name = "Description")
    private String description;
    @Size(max = 45)
    @Column(name = "VIPScol")
    private String vIPScol;
    @JoinColumn(name = "vipCompId", referencedColumnName = "compId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Components components;
    @JoinColumn(name = "Host", referencedColumnName = "BigIPHostname", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Bigiphosts bigiphosts;

    public Vips() {
    }

    public Vips(VipsPK vipsPK) {
        this.vipsPK = vipsPK;
    }

    public Vips(int vipCompId, String host) {
        this.vipsPK = new VipsPK(vipCompId, host);
    }

    public VipsPK getVipsPK() {
        return vipsPK;
    }

    public void setVipsPK(VipsPK vipsPK) {
        this.vipsPK = vipsPK;
    }

    public String getPersistence() {
        return persistence;
    }

    public void setPersistence(String persistence) {
        this.persistence = persistence;
    }

    public String getIRule() {
        return iRule;
    }

    public void setIRule(String iRule) {
        this.iRule = iRule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVIPScol() {
        return vIPScol;
    }

    public void setVIPScol(String vIPScol) {
        this.vIPScol = vIPScol;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public Bigiphosts getBigiphosts() {
        return bigiphosts;
    }

    public void setBigiphosts(Bigiphosts bigiphosts) {
        this.bigiphosts = bigiphosts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vipsPK != null ? vipsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vips)) {
            return false;
        }
        Vips other = (Vips) object;
        if ((this.vipsPK == null && other.vipsPK != null) || (this.vipsPK != null && !this.vipsPK.equals(other.vipsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.Vips[ vipsPK=" + vipsPK + " ]";
    }
    
}
