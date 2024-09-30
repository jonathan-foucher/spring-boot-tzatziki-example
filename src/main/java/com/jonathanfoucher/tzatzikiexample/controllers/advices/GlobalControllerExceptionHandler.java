package com.jonathanfoucher.tzatzikiexample.controllers.advices;

import com.jonathanfoucher.tzatzikiexample.errors.JobNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(JobNotFoundException.class)
    public final ResponseEntity<String> handleNotFoundException(Exception exception) {
        log.warn(exception.getMessage());
        return ResponseEntity.status(NOT_FOUND)
                .body(exception.getMessage());
    }
}
