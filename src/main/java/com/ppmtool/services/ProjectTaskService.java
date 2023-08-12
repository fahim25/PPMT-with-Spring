package com.ppmtool.services;

import com.ppmtool.model.Backlog;
import com.ppmtool.model.ProjectTask;
import com.ppmtool.repositories.BacklogRepository;
import com.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    final BacklogRepository backlogRepository;

    final ProjectTaskRepository projectTaskRepository;


    public ProjectTaskService(BacklogRepository backlogRepository, ProjectTaskRepository projectTaskRepository) {
        this.backlogRepository = backlogRepository;
        this.projectTaskRepository = projectTaskRepository;
    }

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){

        //PTs to be added to specific project, project != null
        Backlog backlog =backlogRepository.findByProjectIdentifier(projectIdentifier);

        //set the bl to pt
        projectTask.setBacklog(backlog);

        //project sequence should be IDPRO-1 IDPRO_2...
        Integer backlogSequence = backlog.getPTSequence();

        //Update the BL SEQUENCE
        backlogSequence++;

        backlog.setPTSequence(backlogSequence);

        //add sequence to project task
        projectTask.setProjectSequence(backlog.getProjectIdentifier()+ "-"+backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        //INITIAL priority is null

        /*if(projectTask.getPriority()==0 || projectTask.getPriority()==null){
            projectTask.setPriority(3);
        }*/
        if(projectTask.getPriority()==null){
            projectTask.setPriority(3);
        }

        //INITIAL status is null
        if (projectTask.getStatus().equals("") || projectTask.getStatus() == null){
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);
    }


    public Iterable<ProjectTask> findBacklogById(String id) {
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }
}