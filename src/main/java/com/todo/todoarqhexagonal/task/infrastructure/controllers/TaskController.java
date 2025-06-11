package com.todo.todoarqhexagonal.task.infrastructure.controllers;


import com.todo.todoarqhexagonal.task.application.services.TaskService;
import com.todo.todoarqhexagonal.task.domain.models.Task;
import com.todo.todoarqhexagonal.task.infrastructure.https.request.TaskRequest;
import com.todo.todoarqhexagonal.task.infrastructure.https.response.TaskResponse;
import com.todo.todoarqhexagonal.task.infrastructure.mappers.TaskMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest) {
        Task task = taskMapper.requestToTask(taskRequest);
        Task createdTask = taskService.create(task);
        return ResponseEntity.ok(taskMapper.modelToResponse(createdTask)
        );
    }

    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok(taskMapper.modelListToResponseList(taskService.getAllTasks()));
    }



}
