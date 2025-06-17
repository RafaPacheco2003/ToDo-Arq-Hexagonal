package com.todo.todoarqhexagonal.user.infrastructure.repositories;

import com.todo.todoarqhexagonal.user.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity,Long> {
}
