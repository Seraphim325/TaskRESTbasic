package com.example.backend.error;

import com.example.backend.error.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ResponseErrorEntity> handleTaskNotFound(TaskNotFoundException e, WebRequest request) {
        return new ResponseEntity<>(new ResponseErrorEntity(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                e.getMessage(),
                request.getDescription(false)
        ), HttpStatus.NOT_FOUND);
    }
}
