package com.todo.todoarqhexagonal.user.application.services;

import com.todo.todoarqhexagonal.user.domain.models.User;
import com.todo.todoarqhexagonal.user.domain.ports.in.CreateUserUseCase;
import com.todo.todoarqhexagonal.user.domain.ports.in.DeleteUserUseCase;
import com.todo.todoarqhexagonal.user.domain.ports.in.RetrieveUserUseCase;
import com.todo.todoarqhexagonal.user.domain.ports.in.UpdateUserUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService  implements CreateUserUseCase, RetrieveUserUseCase, UpdateUserUseCase, DeleteUserUseCase {


    private final CreateUserUseCase createUserUseCase;
    private final RetrieveUserUseCase retrieveUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final  UpdateUserUseCase updateUserUseCase;

    public UserService(CreateUserUseCase createUserUseCase, RetrieveUserUseCase retrieveUserUseCase, DeleteUserUseCase deleteUserUseCase, UpdateUserUseCase updateUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.retrieveUserUseCase = retrieveUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
    }


    @Override
    public User create(User user) {
        return createUserUseCase.create(user);
    }

    @Override
    public Boolean delete(Long id) {
        return deleteUserUseCase.delete(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        return retrieveUserUseCase.findById(id);
    }

    @Override
    public List<User> findAll() {
        return retrieveUserUseCase.findAll();
    }

    @Override
    public Optional<User> update(User user) {
        return updateUserUseCase.update(user);
    }
}
