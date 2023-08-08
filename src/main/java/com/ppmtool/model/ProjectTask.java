package com.ppmtool.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Getter
@Setter
public class ProjectTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    private String PTSequence;

    @NotBlank(message = "Please include a project summary.")
    private String summary;
    private String acceptanceCriteria;
    private String status;
    private Integer priority;
    private Date dueDate;
    //many to one with backlog
    @Column(updatable = false)
    private String projectIdentifier;

    @Override
    public String toString() {
        return "ProjectTask{" +
                "id=" + id +
                ", PTSequence='" + PTSequence + '\'' +
                ", summary='" + summary + '\'' +
                ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", projectIdentifier='" + projectIdentifier + '\'' +
                ", crate_at=" + crate_at +
                ", update_at=" + update_at +
                '}';
    }

    public ProjectTask() {
    }

    private Date crate_at;
    private Date update_at;



    @PrePersist
    protected void onCreate(){
        this.crate_at = new Date();
    }

    @PreUpdate
    protected  void onUpdate(){
        this.update_at = new Date();
    }



}
