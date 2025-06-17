package com.todo.todoarqhexagonal.task.infrastructure.mappers;

import com.todo.todoarqhexagonal.task.domain.models.Task;
import com.todo.todoarqhexagonal.task.infrastructure.entities.TaskEntity;
import com.todo.todoarqhexagonal.task.infrastructure.https.request.TaskRequest;
import com.todo.todoarqhexagonal.task.infrastructure.https.response.TaskResponse;
import com.todo.todoarqhexagonal.task.infrastructure.https.response.TaskWithUserResponse;
import com.todo.todoarqhexagonal.user.domain.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskMapper {

    public Task requestToTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.title());
        task.setDescription(taskRequest.description());
        task.setDateLimite(taskRequest.dateLimite());
        task.setDateCreation(new java.util.Date());
        task.setUserId(taskRequest.userId()); // Asignamos el userId
        return task;
    }

    public TaskResponse modelToResponse(Task task) {
        return new TaskResponse(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getDateCreation(),
                task.getDateLimite().toString(),
                task.getUserId() // Incluimos el userId en la respuesta
        );
    }

    public List<TaskResponse> modelListToResponseList(List<Task> tasks) {
        return tasks.stream()
                .map(this::modelToResponse)
                .collect(java.util.stream.Collectors.toList());
    }

    public Task entityToModel(TaskEntity taskEntity) {
        return new Task(
                taskEntity.getTaskId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.getDateLimite(),
                taskEntity.getDateCreation(),
                taskEntity.getUser() != null ? taskEntity.getUser().getUserId() : null // Solo pasamos el ID
        );
    }

    public TaskWithUserResponse modelToResponseWithUser(Task task, User user) {
        return new TaskWithUserResponse(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getDateCreation(),
                task.getDateLimite().toString(),
                task.getUserId(),
                user != null ? user.getFirstName() : null,
                user != null ? user.getLastName() : null
        );
    }

    public TaskEntity modelToEntity(Task task) {
        TaskEntity entity = new TaskEntity(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getDateLimite(),
                task.getDateCreation(),
                null // El UserEntity se establecerá después
        );

        // Nota: El UserEntity deberá ser establecido por el repositorio
        return entity;
    }
}