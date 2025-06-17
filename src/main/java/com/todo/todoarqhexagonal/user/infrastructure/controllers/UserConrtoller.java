package com.todo.todoarqhexagonal.user.infrastructure.controllers;

import com.todo.todoarqhexagonal.task.domain.models.Task;
import com.todo.todoarqhexagonal.task.infrastructure.https.request.TaskRequest;
import com.todo.todoarqhexagonal.user.application.services.UserService;
import com.todo.todoarqhexagonal.user.domain.models.User;
import com.todo.todoarqhexagonal.user.infrastructure.http.request.UserRequest;
import com.todo.todoarqhexagonal.user.infrastructure.http.response.UserResponse;
import com.todo.todoarqhexagonal.user.infrastructure.mappers.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserConrtoller {

    private final UserService  userService;
    private final UserMapper userMapper;

    public UserConrtoller(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody UserRequest userRequest) {
        User user = userMapper.requestToModel(userRequest);
        User createdUser = userService.create(user);
        return ResponseEntity.ok(userMapper.modelToResponse(user)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTask(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);

        return ResponseEntity.ok(user.map(userMapper::modelToResponse));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserResponse> responses = users.stream()
                .map(userMapper::modelToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequest userRequest) {

        // Verificar que el ID del path coincida con el cuerpo si lo incluyeras
        User user = userMapper.requestToModel(userRequest);
        user.setUserId(id); // Asegurar que el ID coincida

        try {
            Optional<User> updatedUser = userService.update(user);
            return updatedUser.map(value -> ResponseEntity.ok(userMapper.modelToResponse(value)))
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
