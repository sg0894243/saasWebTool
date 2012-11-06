/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package saasmgr.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SG0894243
 */
@Entity
@Table(name = "tasks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tasks.findAll", query = "SELECT t FROM Tasks t"),
    @NamedQuery(name = "Tasks.findByIdTask", query = "SELECT t FROM Tasks t WHERE t.idTask = :idTask"),
    @NamedQuery(name = "Tasks.findByType", query = "SELECT t FROM Tasks t WHERE t.type = :type"),
    @NamedQuery(name = "Tasks.findByReference", query = "SELECT t FROM Tasks t WHERE t.reference = :reference"),
    @NamedQuery(name = "Tasks.findBySubmittedDate", query = "SELECT t FROM Tasks t WHERE t.submittedDate = :submittedDate"),
    @NamedQuery(name = "Tasks.findBySatus", query = "SELECT t FROM Tasks t WHERE t.satus = :satus"),
    @NamedQuery(name = "Tasks.findByComments", query = "SELECT t FROM Tasks t WHERE t.comments = :comments"),
    @NamedQuery(name = "Tasks.findBySubmitter", query = "SELECT t FROM Tasks t WHERE t.submitter = :submitter"),
    @NamedQuery(name = "Tasks.findByTaskscol", query = "SELECT t FROM Tasks t WHERE t.taskscol = :taskscol")})
public class Tasks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTask")
    private Integer idTask;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Type")
    private String type;
    @Size(max = 45)
    @Column(name = "Reference")
    private String reference;
    @Column(name = "SubmittedDate")
    @Temporal(TemporalType.DATE)
    private Date submittedDate;
    @Size(max = 45)
    @Column(name = "Satus")
    private String satus;
    @Size(max = 45)
    @Column(name = "Comments")
    private String comments;
    @Size(max = 45)
    @Column(name = "Submitter")
    private String submitter;
    @Size(max = 45)
    @Column(name = "Taskscol")
    private String taskscol;
    @JoinColumn(name = "Project", referencedColumnName = "projectId")
    @ManyToOne(optional = false)
    private Projects project;

    public Tasks() {
    }

    public Tasks(Integer idTask) {
        this.idTask = idTask;
    }

    public Tasks(Integer idTask, String type) {
        this.idTask = idTask;
        this.type = type;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getSatus() {
        return satus;
    }

    public void setSatus(String satus) {
        this.satus = satus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getTaskscol() {
        return taskscol;
    }

    public void setTaskscol(String taskscol) {
        this.taskscol = taskscol;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTask != null ? idTask.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tasks)) {
            return false;
        }
        Tasks other = (Tasks) object;
        if ((this.idTask == null && other.idTask != null) || (this.idTask != null && !this.idTask.equals(other.idTask))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "saasmgr.entity.Tasks[ idTask=" + idTask + " ]";
    }
    
}
