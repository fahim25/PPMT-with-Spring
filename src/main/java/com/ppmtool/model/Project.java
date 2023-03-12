package com.ppmtool.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private long id;
    @NotBlank(message="Project name is required")
    private String projectName;
    @NotBlank(message="Project Identifier is required")
    @Size(min = 4, max = 6, message = "Please user 4 to 6 characters")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;
    @NotBlank(message="Project Desc is required")
    private String projectDesc;
    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date startDate;
    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date endDate;
    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date cratedDate;
    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date updatedDate;

    public Project() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCratedDate() {
        return cratedDate;
    }

    public void setCratedDate(Date cratedDate) {
        this.cratedDate = cratedDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @PrePersist
    protected void onCreate(){
        this.cratedDate = new Date();
    }

    @PreUpdate
    protected  void onUpdate(){
        this.updatedDate = new Date();
    }


}
