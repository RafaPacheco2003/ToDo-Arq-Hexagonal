package com.todo.todoarqhexagonal.task.infrastructure.configs;

import com.todo.todoarqhexagonal.task.application.services.TaskService;
import com.todo.todoarqhexagonal.task.application.usecases.CreateTaskUseCaseImpl;
import com.todo.todoarqhexagonal.task.application.usecases.DeteleTaskUseCaseImpl;
import com.todo.todoarqhexagonal.task.application.usecases.RetrieveTaskUseCaseImpl;
import com.todo.todoarqhexagonal.task.application.usecases.UpdateTaskUseCaseImpl;
import com.todo.todoarqhexagonal.task.domain.ports.out.TaskRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("applicationConfigTask")
public class ApplicationConfig {

    @Bean
    public TaskService taskService(TaskRepositoryPort taskRepository) {
        return new TaskService(
                new CreateTaskUseCaseImpl(taskRepository),
                new RetrieveTaskUseCaseImpl(taskRepository),
                new UpdateTaskUseCaseImpl(taskRepository),
                new DeteleTaskUseCaseImpl(taskRepository)
        );

    }
}
