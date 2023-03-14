package com.ppmtool.exceptions;

public class ProjectIdExceptionsResponse {
    private String projectIdentifier;

    public ProjectIdExceptionsResponse(String message) {
        super();
    }

    public void ProjectIdExceptionsResponse(String projectIdentifier){
        this.projectIdentifier = projectIdentifier;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
