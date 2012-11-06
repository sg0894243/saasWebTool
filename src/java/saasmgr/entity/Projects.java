/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package saasmgr.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SG0894243
 */
@Entity
@Table(name = "projects")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projects.findAll", query = "SELECT p FROM Projects p"),
    @NamedQuery(name = "Projects.findByProjectId", query = "SELECT p FROM Projects p WHERE p.projectId = :projectId"),
    @NamedQuery(name = "Projects.findByType", query = "SELECT p FROM Projects p WHERE p.type = :type"),
    @NamedQuery(name = "Projects.findByEnv", query = "SELECT p FROM Projects p WHERE p.env = :env"),
    @NamedQuery(name = "Projects.findByParentSR", query = "SELECT p FROM Projects p WHERE p.parentSR = :parentSR"),
    @NamedQuery(name = "Projects.findByEnvReadyDate", query = "SELECT p FROM Projects p WHERE p.envReadyDate = :envReadyDate"),
    @NamedQuery(name = "Projects.findByCutoverDate", query = "SELECT p FROM Projects p WHERE p.cutoverDate = :cutoverDate"),
    @NamedQuery(name = "Projects.findByStatus", query = "SELECT p FROM Projects p WHERE p.status = :status"),
    @NamedQuery(name = "Projects.findByProjectscol", query = "SELECT p FROM Projects p WHERE p.projectscol = :projectscol")})
public class Projects implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "projectId")
    private Integer projectId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Env")
    private String env;
    @Size(max = 45)
    @Column(name = "ParentSR")
    private String parentSR;
    @Column(name = "EnvReadyDate")
    @Temporal(TemporalType.DATE)
    private Date envReadyDate;
    @Column(name = "CutoverDate")
    @Temporal(TemporalType.DATE)
    private Date cutoverDate;
    @Size(max = 45)
    @Column(name = "Status")
    private String status;
    @Size(max = 45)
    @Column(name = "Projectscol")
    private String projectscol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<Tasks> tasksList;
    @JoinColumn(name = "PrimaryArchitect", referencedColumnName = "idEmployee")
    @ManyToOne
    private People primaryArchitect;
    @JoinColumn(name = "PrimaryPM", referencedColumnName = "idEmployee")
    @ManyToOne
    private People primaryPM;
    @JoinColumn(name = "Customer", referencedColumnName = "CustomerCode")
    @ManyToOne(optional = false)
    private Customers customer;
    @JoinColumn(name = "Product", referencedColumnName = "idProduct")
    @ManyToOne(optional = false)
    private Products product;
    @OneToMany(mappedBy = "idProject")
    private List<Connections> connectionsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<Components> componentsList;

    public Projects() {
    }

    public Projects(Integer projectId) {
        this.projectId = projectId;
    }

    public Projects(Integer projectId, String type, String env) {
        this.projectId = projectId;
        this.type = type;
        this.env = env;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getParentSR() {
        return parentSR;
    }

    public void setParentSR(String parentSR) {
        this.parentSR = parentSR;
    }

    public Date getEnvReadyDate() {
        return envReadyDate;
    }

    public void setEnvReadyDate(Date envReadyDate) {
        this.envReadyDate = envReadyDate;
    }

    public Date getCutoverDate() {
        return cutoverDate;
    }

    public void setCutoverDate(Date cutoverDate) {
        this.cutoverDate = cutoverDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectscol() {
        return projectscol;
    }

    public void setProjectscol(String projectscol) {
        this.projectscol = projectscol;
    }

    @XmlTransient
    public List<Tasks> getTasksList() {
        return tasksList;
    }

    public void setTasksList(List<Tasks> tasksList) {
        this.tasksList = tasksList;
    }

    public People getPrimaryArchitect() {
        return primaryArchitect;
    }

    public void setPrimaryArchitect(People primaryArchitect) {
        this.primaryArchitect = primaryArchitect;
    }

    public People getPrimaryPM() {
        return primaryPM;
    }

    public void setPrimaryPM(People primaryPM) {
        this.primaryPM = primaryPM;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    @XmlTransient
    public List<Connections> getConnectionsList() {
        return connectionsList;
    }

    public void setConnectionsList(List<Connections> connectionsList) {
        this.connectionsList = connectionsList;
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
        hash += (projectId != null ? projectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projects)) {
            return false;
        }
        Projects other = (Projects) object;
        if ((this.projectId == null && other.projectId != null) || (this.projectId != null && !this.projectId.equals(other.projectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.Projects[ projectId=" + projectId + " ]";
    }
    
}
