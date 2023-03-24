package com.ppmtool.controller;

import com.ppmtool.model.Project;
import com.ppmtool.services.MapValidationService;
import com.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
@CrossOrigin(origins = "http://localhost:3000/")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationService validationService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){

        ResponseEntity<?> errorMap = validationService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }


    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable("projectId") String projectId){
        Project project = projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    /*find all project*/
    @GetMapping("/allProject")
    public Iterable<Project> getAllProjects(){
        return projectService.findAllProject();
    }

    /*Delete By ProjectIdentifier*/
    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable("projectId") String projectId){
        projectService.deleteByProjectIdentifier(projectId.toUpperCase());
        return new ResponseEntity<String>("Project '" + projectId + "' has been deleted successfully", HttpStatus.OK);
    }

}
