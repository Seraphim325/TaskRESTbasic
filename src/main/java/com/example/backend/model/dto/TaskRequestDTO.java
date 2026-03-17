package com.example.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

public class TaskRequestDTO {
    @NotBlank(message = "This title is required")
    @NotEmpty
    @Size(max = 255)
    private String title;

    @Size(max = 65535)
    private String description;

    private Boolean completed;

    private LocalDateTime createdAt;

    public TaskRequestDTO(String title, String description, Boolean completed, Long curTime) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        if (Objects.nonNull(curTime)) {
            createdAt = LocalDateTime.ofInstant(Instant.ofEpochMilli(curTime), ZoneOffset.systemDefault());
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nDescription: " + description + "\nCompleted: " + completed + "\nCreated at: " + createdAt;
    }
}
