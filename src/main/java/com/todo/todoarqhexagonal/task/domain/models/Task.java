package com.todo.todoarqhexagonal.task.domain.models;

import java.util.Date;

public class Task {

    private Long taskId;
    private String title;
    private String description;
    private Date dateLimite;
    private Date dateCreation;
    private Long userId;



    public Task() {
    }

    public Task(Long taskId, String title, String description, Date dateLimite, Date dateCreation, Long userId) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.dateLimite = dateLimite;
        this.dateCreation = dateCreation;
        this.userId = userId;
    }

    // Getters y setters...
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
