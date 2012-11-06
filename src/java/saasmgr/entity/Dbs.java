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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SG0894243
 */
@Entity
@Table(name = "dbs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dbs.findAll", query = "SELECT d FROM Dbs d"),
    @NamedQuery(name = "Dbs.findByDbCompId", query = "SELECT d FROM Dbs d WHERE d.dbCompId = :dbCompId"),
    @NamedQuery(name = "Dbs.findByDBname", query = "SELECT d FROM Dbs d WHERE d.dBname = :dBname"),
    @NamedQuery(name = "Dbs.findByType", query = "SELECT d FROM Dbs d WHERE d.type = :type"),
    @NamedQuery(name = "Dbs.findBySize", query = "SELECT d FROM Dbs d WHERE d.size = :size"),
    @NamedQuery(name = "Dbs.findByCharset", query = "SELECT d FROM Dbs d WHERE d.charset = :charset"),
    @NamedQuery(name = "Dbs.findByDBscol", query = "SELECT d FROM Dbs d WHERE d.dBscol = :dBscol")})
public class Dbs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "dbCompId")
    private Integer dbCompId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DBname")
    private String dBname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Type")
    private String type;
    @Size(max = 45)
    @Column(name = "Size")
    private String size;
    @Size(max = 45)
    @Column(name = "Charset")
    private String charset;
    @Size(max = 45)
    @Column(name = "DBscol")
    private String dBscol;
    @JoinColumn(name = "FailoverHost", referencedColumnName = "HostName")
    @ManyToOne(optional = false)
    private Saashosts failoverHost;
    @JoinColumn(name = "PrimaryHost", referencedColumnName = "HostName")
    @ManyToOne(optional = false)
    private Saashosts primaryHost;
    @JoinColumn(name = "dbCompId", referencedColumnName = "compId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Components components;

    public Dbs() {
    }

    public Dbs(Integer dbCompId) {
        this.dbCompId = dbCompId;
    }

    public Dbs(Integer dbCompId, String dBname, String type) {
        this.dbCompId = dbCompId;
        this.dBname = dBname;
        this.type = type;
    }

    public Integer getDbCompId() {
        return dbCompId;
    }

    public void setDbCompId(Integer dbCompId) {
        this.dbCompId = dbCompId;
    }

    public String getDBname() {
        return dBname;
    }

    public void setDBname(String dBname) {
        this.dBname = dBname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getDBscol() {
        return dBscol;
    }

    public void setDBscol(String dBscol) {
        this.dBscol = dBscol;
    }

    public Saashosts getFailoverHost() {
        return failoverHost;
    }

    public void setFailoverHost(Saashosts failoverHost) {
        this.failoverHost = failoverHost;
    }

    public Saashosts getPrimaryHost() {
        return primaryHost;
    }

    public void setPrimaryHost(Saashosts primaryHost) {
        this.primaryHost = primaryHost;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dbCompId != null ? dbCompId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dbs)) {
            return false;
        }
        Dbs other = (Dbs) object;
        if ((this.dbCompId == null && other.dbCompId != null) || (this.dbCompId != null && !this.dbCompId.equals(other.dbCompId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.Dbs[ dbCompId=" + dbCompId + " ]";
    }
    
}
