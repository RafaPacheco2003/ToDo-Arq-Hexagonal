package com.todo.todoarqhexagonal.user.infrastructure.config;

import com.todo.todoarqhexagonal.task.application.usecases.CreateTaskUseCaseImpl;
import com.todo.todoarqhexagonal.task.application.usecases.DeteleTaskUseCaseImpl;
import com.todo.todoarqhexagonal.task.application.usecases.RetrieveTaskUseCaseImpl;
import com.todo.todoarqhexagonal.task.application.usecases.UpdateTaskUseCaseImpl;
import com.todo.todoarqhexagonal.user.application.services.UserService;
import com.todo.todoarqhexagonal.user.application.usecases.CreateUserUseCaseImpl;
import com.todo.todoarqhexagonal.user.application.usecases.DeleteUserUseCaseImpl;
import com.todo.todoarqhexagonal.user.application.usecases.RetrieveUserUseCaseImpl;
import com.todo.todoarqhexagonal.user.application.usecases.UpdateUserUseCaseImpl;
import com.todo.todoarqhexagonal.user.domain.ports.out.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("applicationConfigUser")
public class ApplicationConfig {

    @Bean
    public UserService userService(UserRepositoryPort userRepositoryPort) {

       return new UserService(
               new CreateUserUseCaseImpl(userRepositoryPort),
               new RetrieveUserUseCaseImpl(userRepositoryPort),
               new DeleteUserUseCaseImpl(userRepositoryPort),
               new UpdateUserUseCaseImpl(userRepositoryPort)

       );
    }
}
