package com.todo.todoarqhexagonal.user.domain.ports.in;

import com.todo.todoarqhexagonal.user.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface RetrieveUserUseCase {

    Optional<User> findById(Long id);
    List<User> findAll();

}
