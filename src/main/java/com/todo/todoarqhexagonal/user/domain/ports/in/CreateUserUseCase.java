package com.todo.todoarqhexagonal.user.domain.ports.in;

import com.todo.todoarqhexagonal.user.domain.models.User;

public interface CreateUserUseCase {
    User create(User user);
}
