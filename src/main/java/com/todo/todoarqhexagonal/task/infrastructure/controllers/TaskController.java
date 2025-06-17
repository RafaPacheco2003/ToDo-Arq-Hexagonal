package com.todo.todoarqhexagonal.task.infrastructure.controllers;


import com.todo.todoarqhexagonal.task.application.services.TaskService;
import com.todo.todoarqhexagonal.task.domain.models.Task;
import com.todo.todoarqhexagonal.task.infrastructure.https.request.TaskRequest;
import com.todo.todoarqhexagonal.task.infrastructure.https.response.TaskResponse;
import com.todo.todoarqhexagonal.task.infrastructure.https.response.TaskWithUserResponse;
import com.todo.todoarqhexagonal.task.infrastructure.mappers.TaskMapper;
import com.todo.todoarqhexagonal.user.application.services.UserService;
import com.todo.todoarqhexagonal.user.domain.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, UserService userService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.userService = userService;
        this.taskMapper = taskMapper;
    }



    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest) {
        Task task = taskMapper.requestToTask(taskRequest);
        Task createdTask = taskService.create(task, taskRequest.userId()); // Pasar el userId
        return ResponseEntity.ok(taskMapper.modelToResponse(createdTask));
    }

    @GetMapping
    public ResponseEntity<List<TaskWithUserResponse>> getAllTasksWithUsers() {
        List<Task> tasks = taskService.getAllTasks();

        // Mapear cada tarea a TaskWithUserResponse
        List<TaskWithUserResponse> responses = tasks.stream()
                .map(task -> {
                    Optional<User> userOpt = task.getUserId() != null ?
                            userService.findById(task.getUserId()) :
                            Optional.empty();

                    return taskMapper.modelToResponseWithUser(
                            task,
                            userOpt.orElse(null)
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TaskWithUserResponse> getTaskWithUser(@PathVariable Long id) {
        Optional<Task> taskOpt = taskService.getTaskById(id);
        if (taskOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Task task = taskOpt.get();
        Optional<User> userOpt = userService.findById(task.getUserId());

        TaskWithUserResponse response = taskMapper.modelToResponseWithUser(
                task,
                userOpt.orElse(null)
        );

        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(
            @PathVariable Long id,
            @RequestBody TaskRequest taskRequest) {

        Task taskToUpdate = taskMapper.requestToTask(taskRequest);
        taskToUpdate.setTaskId(id);


            Optional<Task> updatedTask = taskService.update(id, taskToUpdate);
            return updatedTask.map(task -> ResponseEntity.ok(taskMapper.modelToResponse(task)))
                    .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        if (taskService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
