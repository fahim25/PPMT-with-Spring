package com.ppmtool.controller;

import com.ppmtool.model.ProjectTask;
import com.ppmtool.services.MapValidationService;
import com.ppmtool.services.ProjectService;
import com.ppmtool.services.ProjectTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin(origins = "http://localhost:3000/")
public class BacklogController {

    final ProjectTaskService projectTaskService;

    final MapValidationService mapValidationService;

    public BacklogController(ProjectTaskService projectTaskService, MapValidationService mapValidationService) {
        this.projectTaskService = projectTaskService;
        this.mapValidationService = mapValidationService;
    }

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTask projectTask, @PathVariable String backlog_id , BindingResult result){

        ResponseEntity<?> errorMap = mapValidationService.MapValidationService(result);

        if(errorMap != null) return errorMap;

        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);


        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }
    
    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id){
        return (projectTaskService.findBacklogById (backlog_id));
    }


    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id){
        ProjectTask projectTask = projectTaskService.findPTByProjectSequence(backlog_id, pt_id);

        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);

    }

    @PatchMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
                                               @PathVariable String backlog_id, @PathVariable String pt_id){

        ResponseEntity<?> errorMap = mapValidationService.MapValidationService(result);

        if(errorMap != null) return errorMap;

        ProjectTask updatedTask = projectTaskService.updateProjectTaskSequence(projectTask, backlog_id, pt_id);

        return new ResponseEntity<ProjectTask>(updatedTask, HttpStatus.OK);

    }





}
