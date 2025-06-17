package com.todo.todoarqhexagonal.user.infrastructure.mappers;


import com.todo.todoarqhexagonal.user.domain.models.User;
import com.todo.todoarqhexagonal.user.infrastructure.entities.UserEntity;
import com.todo.todoarqhexagonal.user.infrastructure.http.request.UserRequest;
import com.todo.todoarqhexagonal.user.infrastructure.http.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User requestToModel(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        return user;
    }

    public UserResponse modelToResponse(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    public List<UserResponse> modelListToResponseList(List<User> users) {
        return users.stream()
                .map(this::modelToResponse)
                .collect(Collectors.toList());
    }

    public User entityToModel(UserEntity userEntity) {
        return new User(
                userEntity.getUserId(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getFirstName(),
                userEntity.getLastName()
        );
    }

    public UserEntity modelToEntity(User user) {
        return new UserEntity(
                user.getUserId(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                null // Las tareas se manejarán en el repositorio
        );
    }

    // Método opcional para convertir a entidad sin incluir la contraseña (para respuestas)
    public UserEntity modelToEntityWithoutPassword(User user) {
        UserEntity entity = new UserEntity();
        entity.setUserId(user.getUserId());
        entity.setEmail(user.getEmail());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        return entity;
    }
}