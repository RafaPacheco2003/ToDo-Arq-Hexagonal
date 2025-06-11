package com.todo.todoarqhexagonal.task.domain.ports.in;

import com.todo.todoarqhexagonal.task.domain.models.Task;

public interface DeleteTaskUseCase {
    Boolean delete(Task task);
}
