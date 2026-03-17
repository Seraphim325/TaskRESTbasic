package com.example.backend.repository;

import com.example.backend.model.dto.TaskRequestDTO;
import com.example.backend.model.dto.TaskResponseDTO;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    TaskResponseDTO get(int id);

    List<TaskResponseDTO> getAll();

    void create(TaskRequestDTO task);

    void delete(int id);

    void update(int id, TaskRequestDTO task);

}
