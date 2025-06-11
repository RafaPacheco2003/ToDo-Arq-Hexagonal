package com.todo.todoarqhexagonal.task.domain.ports.in;

import com.todo.todoarqhexagonal.task.domain.models.Task;

import java.util.List;
import java.util.Optional;

public interface RetrieveTaskUseCase {

    Optional<Task> getTaskById(Long id);
    List<Task> getAllTasks();
}
