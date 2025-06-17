package com.todo.todoarqhexagonal.user.application.usecases;

import com.todo.todoarqhexagonal.user.domain.ports.in.DeleteUserUseCase;
import com.todo.todoarqhexagonal.user.domain.ports.out.UserRepositoryPort;

public class DeleteUserUseCaseImpl implements DeleteUserUseCase {


    private final UserRepositoryPort userRepositoryPort;

    public DeleteUserUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }




    @Override
    public Boolean delete(Long id) {
        return   userRepositoryPort.delete(id);
    }
}
