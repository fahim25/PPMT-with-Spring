package com.ppmtool.services;

import com.ppmtool.exceptions.ProjectNotFoundException;
import com.ppmtool.model.Backlog;
import com.ppmtool.model.Project;
import com.ppmtool.model.ProjectTask;
import com.ppmtool.repositories.BacklogRepository;
import com.ppmtool.repositories.ProjectRepository;
import com.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    final BacklogRepository backlogRepository;

    final ProjectTaskRepository projectTaskRepository;

    final ProjectRepository projectRepository;



    public ProjectTaskService(BacklogRepository backlogRepository, ProjectTaskRepository projectTaskRepository, ProjectRepository projectRepository) {
        this.backlogRepository = backlogRepository;
        this.projectTaskRepository = projectTaskRepository;
        this.projectRepository = projectRepository;
    }

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) throws ProjectNotFoundException{

        try {
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

        }catch (Exception e){
            throw new ProjectNotFoundException("Project not found");
        }
    }


    public Iterable<ProjectTask> findBacklogById(String id) {

        Project project = projectRepository.findByProjectIdentifier(id);

        if (project != null ){
            return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
        }else {
            throw new ProjectNotFoundException("Project with id: " + id + " does not exist");
        }
    }


    public  ProjectTask findPTByProjectSequence(String backlog_id, String pt_id){

        /*make sure we are searching the on the right backlog*/
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);

        if (backlog == null){
            throw new ProjectNotFoundException("Project with ID: '" +backlog_id+"' does not exist");
        }

        /* make sure that out task exist */
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);

        if (projectTask == null){
            throw new ProjectNotFoundException("Project with ID: '" +backlog_id+"' does not exist");
        }
        /* mack sure the backlog/project id in the path corresponding to the right project */

        if(!projectTask.getProjectIdentifier().equals(backlog_id)){
            throw new ProjectNotFoundException("Project task '" +pt_id+"' does not exist in project '" +backlog_id + "' " );
        }


        return projectTask;
    }

    //    update project task
    //    find existing project task
    //    replace it updated task
    //    save update
    public ProjectTask updateProjectTaskSequence(ProjectTask updatedTask, String backlog_id, String pt_id){

        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);

        projectTask = updatedTask;

        return projectTaskRepository.save(projectTask);
    }

    public void deletePTByProjectSequence(String backlog_id, String pt_id) {

        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);

        /*Backlog backlog = projectTask.getBacklog();
        List<ProjectTask> projectTaskList = backlog.getProjectTaskList();
        projectTaskList.remove(projectTask);
        backlogRepository.save(backlog);*/


        projectTaskRepository.delete(projectTask);

    }
}
