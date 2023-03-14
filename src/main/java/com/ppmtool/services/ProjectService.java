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

    public Project saveOrUpdateProject(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdExceptions("Project ID '" + project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
    }

}
