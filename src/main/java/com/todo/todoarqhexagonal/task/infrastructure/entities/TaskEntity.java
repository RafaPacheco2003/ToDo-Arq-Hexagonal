package com.todo.todoarqhexagonal.task.infrastructure.entities;


import com.todo.todoarqhexagonal.user.infrastructure.entities.UserEntity;
import jakarta.persistence.*;

import java.util.Date;

@Table(name = "tasks")
@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String title;
    private String description;
    private Date dateLimite;
    private Date dateCreation;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public TaskEntity() {
    }

    public TaskEntity(Long taskId, String title, String description, Date dateLimite, Date dateCreation, UserEntity user) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.dateLimite = dateLimite;
        this.dateCreation = dateCreation;
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
