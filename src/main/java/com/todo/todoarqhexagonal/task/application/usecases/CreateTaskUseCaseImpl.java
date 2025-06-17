package com.todo.todoarqhexagonal.task.application.usecases;

import com.todo.todoarqhexagonal.task.domain.models.Task;
import com.todo.todoarqhexagonal.task.domain.ports.in.CreateTaskUseCase;
import com.todo.todoarqhexagonal.task.domain.ports.out.TaskRepositoryPort;
import com.todo.todoarqhexagonal.user.domain.ports.out.UserRepositoryPort;

public class CreateTaskUseCaseImpl implements CreateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;
    private final UserRepositoryPort userRepositoryPort; // Añadir dependencia

    public CreateTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort,
                                 UserRepositoryPort userRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Task create(Task task, Long userId) {

        if (!userRepositoryPort.existsById(userId)) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }

        task.setUserId(userId); // Asignar el userId a la tarea
        return taskRepositoryPort.create(task);
    }


}
