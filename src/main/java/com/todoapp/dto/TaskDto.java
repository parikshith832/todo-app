package com.todoapp.dto;

import com.todoapp.entity.Priority;
import com.todoapp.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class TaskDto {

    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must be at most 200 characters")
    private String title;

    @Size(max = 2000, message = "Description must be at most 2000 characters")
    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dueDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime dueTime;

    private Priority priority = Priority.MEDIUM;

    private TaskStatus status = TaskStatus.PENDING;

    @Size(max = 100, message = "Category must be at most 100 characters")
    private String category;

    @Size(max = 500, message = "Remarks must be at most 500 characters")
    private String remarks;
}
