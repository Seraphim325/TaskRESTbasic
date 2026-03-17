package com.example.backend.model.dto;

import java.time.LocalDateTime;

public class TaskResponseDTO {


    private String title;

    private String description;

    private boolean completed;

    private LocalDateTime createdAt;

    public TaskResponseDTO(String title, String description, Boolean completed, LocalDateTime createdAt) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.createdAt = createdAt;

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
        return "Task{" +
                " title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                ", createdAt=" + createdAt +
                '}';
    }

}
