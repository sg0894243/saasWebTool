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
@Table(name = "softwareinstalls")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Softwareinstalls.findAll", query = "SELECT s FROM Softwareinstalls s"),
    @NamedQuery(name = "Softwareinstalls.findByIdSoftware", query = "SELECT s FROM Softwareinstalls s WHERE s.softwareinstallsPK.idSoftware = :idSoftware"),
    @NamedQuery(name = "Softwareinstalls.findByHostname", query = "SELECT s FROM Softwareinstalls s WHERE s.softwareinstallsPK.hostname = :hostname"),
    @NamedQuery(name = "Softwareinstalls.findByInstallSR", query = "SELECT s FROM Softwareinstalls s WHERE s.installSR = :installSR"),
    @NamedQuery(name = "Softwareinstalls.findBySoftwareInstallscol", query = "SELECT s FROM Softwareinstalls s WHERE s.softwareInstallscol = :softwareInstallscol")})
public class Softwareinstalls implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SoftwareinstallsPK softwareinstallsPK;
    @Size(max = 45)
    @Column(name = "InstallSR")
    private String installSR;
    @Size(max = 45)
    @Column(name = "SoftwareInstallscol")
    private String softwareInstallscol;
    @JoinColumn(name = "idSoftware", referencedColumnName = "idSoftware", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Software software;
    @JoinColumn(name = "Hostname", referencedColumnName = "HostName", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Saashosts saashosts;

    public Softwareinstalls() {
    }

    public Softwareinstalls(SoftwareinstallsPK softwareinstallsPK) {
        this.softwareinstallsPK = softwareinstallsPK;
    }

    public Softwareinstalls(int idSoftware, String hostname) {
        this.softwareinstallsPK = new SoftwareinstallsPK(idSoftware, hostname);
    }

    public SoftwareinstallsPK getSoftwareinstallsPK() {
        return softwareinstallsPK;
    }

    public void setSoftwareinstallsPK(SoftwareinstallsPK softwareinstallsPK) {
        this.softwareinstallsPK = softwareinstallsPK;
    }

    public String getInstallSR() {
        return installSR;
    }

    public void setInstallSR(String installSR) {
        this.installSR = installSR;
    }

    public String getSoftwareInstallscol() {
        return softwareInstallscol;
    }

    public void setSoftwareInstallscol(String softwareInstallscol) {
        this.softwareInstallscol = softwareInstallscol;
    }

    public Software getSoftware() {
        return software;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }

    public Saashosts getSaashosts() {
        return saashosts;
    }

    public void setSaashosts(Saashosts saashosts) {
        this.saashosts = saashosts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (softwareinstallsPK != null ? softwareinstallsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Softwareinstalls)) {
            return false;
        }
        Softwareinstalls other = (Softwareinstalls) object;
        if ((this.softwareinstallsPK == null && other.softwareinstallsPK != null) || (this.softwareinstallsPK != null && !this.softwareinstallsPK.equals(other.softwareinstallsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.Softwareinstalls[ softwareinstallsPK=" + softwareinstallsPK + " ]";
    }
    
}
