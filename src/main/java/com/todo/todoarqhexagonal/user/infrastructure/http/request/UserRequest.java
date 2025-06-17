package com.todo.todoarqhexagonal.user.infrastructure.http.request;

public record UserRequest(
        String email,
        String password,
        String firstName,
        String lastName


) {
}
