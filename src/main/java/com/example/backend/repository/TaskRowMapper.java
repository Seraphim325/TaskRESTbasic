package com.example.backend.repository;


import com.example.backend.model.dto.TaskResponseDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRowMapper implements RowMapper<List<TaskResponseDTO>> {
    @Override
    public List<TaskResponseDTO> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<TaskResponseDTO> list = new ArrayList<>();
        do {
            list.add(new TaskResponseDTO(rs.getString("title"), rs.getString("description"),
                    rs.getBoolean("completed"), rs.getTimestamp("created_at").toLocalDateTime()));
        } while (rs.next());
        return list;
    }
}
