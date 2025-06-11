package com.todo.todoarqhexagonal.task.infrastructure.https.request;

import java.util.Date;

public record TaskRequest(
        String title,
        String description,
        Date dateLimite
) {}