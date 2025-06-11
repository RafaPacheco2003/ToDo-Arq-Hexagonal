package com.todo.todoarqhexagonal.task.infrastructure.https.response;

import java.util.Date;

public record TaskResponse(
        Long id,
        String title,
        String description,
        Date dateCreation,
        String dateLimite
) {
}
