package com.todo.todoarqhexagonal.task.application.services;

import com.todo.todoarqhexagonal.task.domain.models.Task;
import com.todo.todoarqhexagonal.task.domain.ports.in.CreateTaskUseCase;
import com.todo.todoarqhexagonal.task.domain.ports.in.DeleteTaskUseCase;
import com.todo.todoarqhexagonal.task.domain.ports.in.RetrieveTaskUseCase;
import com.todo.todoarqhexagonal.task.domain.ports.in.UpdateTaskUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements CreateTaskUseCase, RetrieveTaskUseCase, UpdateTaskUseCase, DeleteTaskUseCase {

    private final CreateTaskUseCase createTaskUseCase;
    private final RetrieveTaskUseCase retrieveTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private  final DeleteTaskUseCase deleteTaskUseCase;

    public TaskService(CreateTaskUseCase createTaskUseCase, RetrieveTaskUseCase retrieveTaskUseCase, UpdateTaskUseCase updateTaskUseCase, DeleteTaskUseCase deleteTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.retrieveTaskUseCase = retrieveTaskUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
    }


    @Override
    public Task create(Task task) {
        return createTaskUseCase.create(task);
    }

    @Override
    public Boolean delete(Long taskId) {
        return deleteTaskUseCase.delete(taskId);
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return retrieveTaskUseCase.getTaskById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return retrieveTaskUseCase.getAllTasks();
    }

    @Override
    public Optional<Task> update(Long id, Task task) {
        return updateTaskUseCase.update(id, task);
    }
}
