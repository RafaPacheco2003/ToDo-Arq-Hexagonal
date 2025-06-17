package com.todo.todoarqhexagonal.user.domain.ports.in;

import com.todo.todoarqhexagonal.user.domain.models.User;

import java.util.Optional;

public interface UpdateUserUseCase {
    Optional<User> update(User user);
}
