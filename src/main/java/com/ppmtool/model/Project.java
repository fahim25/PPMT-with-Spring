package com.ppmtool.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message="Project name is required")
    private String projectName;
    @NotBlank(message="Project Identifier is required")
    @Size(min = 4, max = 10, message = "Please use 4 to 10 characters")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;
    @NotBlank(message="Project Desc is required")
    private String projectDesc;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date cratedDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updatedDate;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
    @JsonIgnore
    private Backlog backlog;


    public Project() {
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
