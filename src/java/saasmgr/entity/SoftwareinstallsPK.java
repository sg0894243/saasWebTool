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
public class SoftwareinstallsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idSoftware")
    private int idSoftware;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Hostname")
    private String hostname;

    public SoftwareinstallsPK() {
    }

    public SoftwareinstallsPK(int idSoftware, String hostname) {
        this.idSoftware = idSoftware;
        this.hostname = hostname;
    }

    public int getIdSoftware() {
        return idSoftware;
    }

    public void setIdSoftware(int idSoftware) {
        this.idSoftware = idSoftware;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSoftware;
        hash += (hostname != null ? hostname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SoftwareinstallsPK)) {
            return false;
        }
        SoftwareinstallsPK other = (SoftwareinstallsPK) object;
        if (this.idSoftware != other.idSoftware) {
            return false;
        }
        if ((this.hostname == null && other.hostname != null) || (this.hostname != null && !this.hostname.equals(other.hostname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.SoftwareinstallsPK[ idSoftware=" + idSoftware + ", hostname=" + hostname + " ]";
    }
    
}
