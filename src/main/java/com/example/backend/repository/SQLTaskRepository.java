package com.example.backend.repository;

import com.example.backend.error.exception.TaskNotFoundException;
import com.example.backend.model.dto.TaskRequestDTO;
import com.example.backend.model.dto.TaskResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class SQLTaskRepository implements TaskRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public SQLTaskRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public TaskResponseDTO get(int id) throws EmptyResultDataAccessException {

        List<TaskResponseDTO> task;
        try {
            task = jdbc.queryForObject("SELECT * FROM tasks WHERE id = ?", new TaskRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            throw new TaskNotFoundException("Element not found");
        }
        return Objects.requireNonNull(task).get(0);
    }

    @Override
    public List<TaskResponseDTO> getAll() {
        try {
            return jdbc.queryForObject("SELECT * FROM tasks", new TaskRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void create(TaskRequestDTO task) {
        String statements = "title" +
                            (Objects.nonNull(task.getDescription()) ? ",description" : "")  +
                            (Objects.nonNull(task.isCompleted()) ? ",completed" : "") +
                            (Objects.nonNull(task.getCreatedAt()) ? ",created_at" : "");
        String values = "'" + task.getTitle() + "'" +
                        (Objects.nonNull(task.getDescription()) ? ",'" + task.getDescription() + "'": "")  +
                        (Objects.nonNull(task.isCompleted()) ? "," + task.isCompleted() : "") +
                        (Objects.nonNull(task.getCreatedAt()) ? ",'" + task.getCreatedAt() + "'" : "");
        String sql = "INSERT INTO tasks(" + statements + ")" +
                " VALUES(" + values + ");";

        jdbc.update(sql);
    }

    @Override
    public void delete(int id) {
        get(id);
        jdbc.update("DELETE FROM tasks WHERE id = ?", id);
    }

    @Override
    public void update(int id, TaskRequestDTO task) {
        get(id);

        String info = "title = '" + task.getTitle() + "'" +
                (Objects.nonNull(task.getDescription()) ? ", description = '" + task.getDescription() + "'" : "") +
                (Objects.nonNull(task.isCompleted()) ? ", completed = " + task.isCompleted() : "") +
                (Objects.nonNull(task.getCreatedAt()) ? ", created_at = '" + task.getCreatedAt() + "'" : "");

        jdbc.update("UPDATE tasks SET " + info + " WHERE id = ?", id);

    }

}
