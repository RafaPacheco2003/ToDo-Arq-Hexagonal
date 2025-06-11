package com.todo.todoarqhexagonal.task.infrastructure.repositories;

import com.todo.todoarqhexagonal.task.infrastructure.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTaskRepository extends JpaRepository<TaskEntity, Long> {

}
