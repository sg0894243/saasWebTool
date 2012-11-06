/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package saasmgr.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author SG0894243
 */
@Embeddable
public class VipsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "vipCompId")
    private int vipCompId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Host")
    private String host;

    public VipsPK() {
    }

    public VipsPK(int vipCompId, String host) {
        this.vipCompId = vipCompId;
        this.host = host;
    }

    public int getVipCompId() {
        return vipCompId;
    }

    public void setVipCompId(int vipCompId) {
        this.vipCompId = vipCompId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) vipCompId;
        hash += (host != null ? host.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VipsPK)) {
            return false;
        }
        VipsPK other = (VipsPK) object;
        if (this.vipCompId != other.vipCompId) {
            return false;
        }
        if ((this.host == null && other.host != null) || (this.host != null && !this.host.equals(other.host))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.VipsPK[ vipCompId=" + vipCompId + ", host=" + host + " ]";
    }
    
}
