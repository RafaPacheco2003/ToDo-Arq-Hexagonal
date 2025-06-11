package com.todo.todoarqhexagonal.task.application.usecases;

import com.todo.todoarqhexagonal.task.domain.models.Task;
import com.todo.todoarqhexagonal.task.domain.ports.in.DeleteTaskUseCase;
import com.todo.todoarqhexagonal.task.domain.ports.out.TaskRepositoryPort;

public class DeteleTaskUseCaseImpl implements DeleteTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    public DeteleTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public Boolean delete(Long taskId) {
        return taskRepositoryPort.delete(taskId);
    }
}
