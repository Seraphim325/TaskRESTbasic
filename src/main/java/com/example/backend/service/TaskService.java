package com.example.backend.service;


import com.example.backend.model.dto.TaskRequestDTO;
import com.example.backend.model.dto.TaskResponseDTO;
import com.example.backend.repository.SQLTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final SQLTaskRepository repository;

    @Autowired
    public TaskService(SQLTaskRepository repository) {
        this.repository = repository;
    }

    public void create(TaskRequestDTO task) {
        repository.create(task);
    }

    public TaskResponseDTO get(int id) {
        return repository.get(id);
    }

    public List<TaskResponseDTO> getAll() {
        return repository.getAll();
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public void update(int id, TaskRequestDTO task) {
        repository.update(id, task);
    }
}
