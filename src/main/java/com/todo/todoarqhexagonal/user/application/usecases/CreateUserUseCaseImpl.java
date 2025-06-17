package com.todo.todoarqhexagonal.user.application.usecases;

import com.todo.todoarqhexagonal.user.domain.models.User;
import com.todo.todoarqhexagonal.user.domain.ports.in.CreateUserUseCase;
import com.todo.todoarqhexagonal.user.domain.ports.out.UserRepositoryPort;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort  userRepositoryPort;

    public CreateUserUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User create(User user) {
        return userRepositoryPort.create(user);
    }
}
