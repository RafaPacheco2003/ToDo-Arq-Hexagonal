package com.todo.todoarqhexagonal.task.domain.ports.out;

import com.todo.todoarqhexagonal.task.domain.models.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface TaskRepositoryPort {

    Task create(Task task);
    Optional<Task> findById(Long id);
    Optional<Task> update(Task task);
    List<Task> findAll();
    Boolean delete(Long id);
}
