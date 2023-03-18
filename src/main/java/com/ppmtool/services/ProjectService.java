package com.ppmtool.services;

import com.ppmtool.exceptions.ProjectIdExceptions;
import com.ppmtool.model.Project;
import com.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /*Save or Update Project*/
    public Project saveOrUpdateProject(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdExceptions("Project ID '" + project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
    }

    /*Find By Project Identifier*/
    public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null){
            throw new ProjectIdExceptions("Project ID '" + projectId +"' doesn't exists");
        }
        return project;
    }

    /*Find All Project*/
    public Iterable<Project> findAllProject(){
        return projectRepository.findAll();
    }

    /*Delete By ProjectIdentifier*/
    public void deleteByProjectIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId);

        if(project == null){
            throw new ProjectIdExceptions("Can't delete project with ID '" + projectId + "' . This project exists.");
        }

        projectRepository.delete(project);
    }

}
