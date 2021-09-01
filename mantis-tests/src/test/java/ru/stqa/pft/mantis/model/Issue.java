package ru.stqa.pft.mantis.model;

public class Issue {

    private int id;
    private int  issue_id;
    private String summary;
    private String description;
    private Project project;
    private String resolution;
    private String status;


    public String getStatus() {
        return status;
    }

    public Issue withStatus(String status) {
        this.status = status;
        return this;
    }


    public int getIssue_id() {
        return issue_id;
    }

    public Issue withIssue_id(int issue_id) {
        this.issue_id = issue_id;
        return this;
    }

    public Issue withResolution(String resolution) {
        this.resolution = resolution;
        return this;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Project getProject() {
        return project;
    }

    public String getSummary() {
        return summary;
    }

    public String getResolution() {
        return resolution;
    }

    public int getId() {
        return id;
    }

}
