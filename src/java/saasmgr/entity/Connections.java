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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SG0894243
 */
@Entity
@Table(name = "connections")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Connections.findAll", query = "SELECT c FROM Connections c"),
    @NamedQuery(name = "Connections.findByIdConnection", query = "SELECT c FROM Connections c WHERE c.idConnection = :idConnection"),
    @NamedQuery(name = "Connections.findByType", query = "SELECT c FROM Connections c WHERE c.type = :type")})
public class Connections implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idConnection")
    private Integer idConnection;
    @Size(max = 45)
    @Column(name = "Type")
    private String type;
    @JoinColumn(name = "Source", referencedColumnName = "compGroupId")
    @ManyToOne(optional = false)
    private Componentgroups source;
    @JoinColumn(name = "idProject", referencedColumnName = "projectId")
    @ManyToOne
    private Projects idProject;
    @JoinColumn(name = "Destination", referencedColumnName = "compGroupId")
    @ManyToOne(optional = false)
    private Componentgroups destination;

    public Connections() {
    }

    public Connections(Integer idConnection) {
        this.idConnection = idConnection;
    }

    public Integer getIdConnection() {
        return idConnection;
    }

    public void setIdConnection(Integer idConnection) {
        this.idConnection = idConnection;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Componentgroups getSource() {
        return source;
    }

    public void setSource(Componentgroups source) {
        this.source = source;
    }

    public Projects getIdProject() {
        return idProject;
    }

    public void setIdProject(Projects idProject) {
        this.idProject = idProject;
    }

    public Componentgroups getDestination() {
        return destination;
    }

    public void setDestination(Componentgroups destination) {
        this.destination = destination;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConnection != null ? idConnection.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Connections)) {
            return false;
        }
        Connections other = (Connections) object;
        if ((this.idConnection == null && other.idConnection != null) || (this.idConnection != null && !this.idConnection.equals(other.idConnection))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.Connections[ idConnection=" + idConnection + " ]";
    }
    
}
