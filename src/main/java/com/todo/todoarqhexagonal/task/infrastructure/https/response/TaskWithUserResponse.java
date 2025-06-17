package com.todo.todoarqhexagonal.task.infrastructure.https.response;

import java.util.Date;

public record TaskWithUserResponse(
        Long id,
        String title,
        String description,
        Date dateCreation,
        String dateLimite,
        Long userId,
        String userFirstName,
        String userLastName
) {}
