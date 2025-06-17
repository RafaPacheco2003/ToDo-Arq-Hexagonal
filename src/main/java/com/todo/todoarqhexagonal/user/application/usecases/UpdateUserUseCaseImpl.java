package com.todo.todoarqhexagonal.user.application.usecases;

import com.todo.todoarqhexagonal.user.domain.models.User;
import com.todo.todoarqhexagonal.user.domain.ports.in.UpdateUserUseCase;
import com.todo.todoarqhexagonal.user.domain.ports.out.UserRepositoryPort;

import java.util.Optional;

public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public UpdateUserUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }
    @Override
    public Optional<User> update(User user) {
        if (user.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null for update");
        }
        return userRepositoryPort.update(user);
    }
}
