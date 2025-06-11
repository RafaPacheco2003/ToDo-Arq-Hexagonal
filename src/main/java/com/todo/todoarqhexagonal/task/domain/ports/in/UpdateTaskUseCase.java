package com.todo.todoarqhexagonal.task.domain.ports.in;

import com.todo.todoarqhexagonal.task.domain.models.Task;

import java.util.Optional;

public interface UpdateTaskUseCase {

    Optional<Task> update(Long id, Task task);
}
