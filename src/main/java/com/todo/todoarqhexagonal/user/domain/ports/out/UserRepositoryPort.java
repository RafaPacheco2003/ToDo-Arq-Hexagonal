package com.todo.todoarqhexagonal.user.domain.ports.out;

import com.todo.todoarqhexagonal.user.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    User create(User user);
    Optional<User> findById(Long id);
    Optional<User> update(User user);
    List<User> findAll();
    Boolean delete(Long id);
    Boolean existsById(Long userId);
}
