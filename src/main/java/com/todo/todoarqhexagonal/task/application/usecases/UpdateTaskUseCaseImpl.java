package com.todo.todoarqhexagonal.task.application.usecases;

import com.todo.todoarqhexagonal.task.domain.models.Task;
import com.todo.todoarqhexagonal.task.domain.ports.in.UpdateTaskUseCase;
import com.todo.todoarqhexagonal.task.domain.ports.out.TaskRepositoryPort;
import com.todo.todoarqhexagonal.user.domain.ports.out.UserRepositoryPort;

import java.util.Optional;

public class UpdateTaskUseCaseImpl implements UpdateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;
    private final UserRepositoryPort userRepositoryPort; // Nueva dependencia

    public UpdateTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort,
                                 UserRepositoryPort userRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Optional<Task> update(Long id, Task task) {
        // Validar que el usuario existe si se está actualizando
        if (task.getUserId() != null && !userRepositoryPort.existsById(task.getUserId())) {
            throw new IllegalArgumentException("User with id " + task.getUserId() + " not found");
        }

        task.setTaskId(id);
        return taskRepositoryPort.update(task);
    }
}