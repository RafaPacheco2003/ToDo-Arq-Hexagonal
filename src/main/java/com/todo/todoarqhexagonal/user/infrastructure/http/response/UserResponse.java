package com.todo.todoarqhexagonal.user.infrastructure.http.response;

public record UserResponse( Long userId,
                            String email,
                            String firstName,
                            String lastName) {

}
