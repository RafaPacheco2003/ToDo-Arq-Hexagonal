package com.todo.todoarqhexagonal.user.application.usecases;

import com.todo.todoarqhexagonal.user.domain.models.User;
import com.todo.todoarqhexagonal.user.domain.ports.in.RetrieveUserUseCase;
import com.todo.todoarqhexagonal.user.domain.ports.out.UserRepositoryPort;

import java.util.List;
import java.util.Optional;

public class RetrieveUserUseCaseImpl implements RetrieveUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public RetrieveUserUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }


    @Override
    public Optional<User> findById(Long id) {
        return userRepositoryPort.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepositoryPort.findAll();
    }
}
