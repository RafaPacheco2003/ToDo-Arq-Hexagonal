package com.todo.todoarqhexagonal.task.application.usecases;

import com.todo.todoarqhexagonal.task.domain.models.Task;
import com.todo.todoarqhexagonal.task.domain.ports.in.UpdateTaskUseCase;
import com.todo.todoarqhexagonal.task.domain.ports.out.TaskRepositoryPort;

import java.util.Optional;

public class UpdateTaskUseCaseImpl implements UpdateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    public UpdateTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public Optional<Task> update(Long id, Task task) {
        return taskRepositoryPort.update(task);
    }
}
