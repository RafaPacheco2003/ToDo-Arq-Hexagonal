package com.todo.todoarqhexagonal.user.domain.ports.in;

public interface DeleteUserUseCase {
    Boolean delete(Long id);
}
