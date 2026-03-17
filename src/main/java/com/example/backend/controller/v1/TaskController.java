package com.example.backend.controller.v1;

import com.example.backend.model.dto.TaskRequestDTO;
import com.example.backend.model.dto.TaskResponseDTO;
import com.example.backend.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> postEntity(@RequestBody @Valid TaskRequestDTO task) {
        service.create(task);
        return new ResponseEntity<>("Created user: \n" + task, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getElementById(@PathVariable int id) {
        TaskResponseDTO task = service.get(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAll() {
        List<TaskResponseDTO> list = service.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity<>("Successfully deleted element with id " + id, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putEntity(@PathVariable int id, @RequestBody @Valid TaskRequestDTO task) {
        service.update(id, task);
        return new ResponseEntity<>("Successfully updated element with id " + id, HttpStatus.NO_CONTENT);
    }
}
