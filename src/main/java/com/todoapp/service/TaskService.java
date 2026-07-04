package com.todoapp.service;

import com.todoapp.dto.DashboardStatsDto;
import com.todoapp.dto.TaskDto;
import com.todoapp.entity.Task;
import com.todoapp.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    DashboardStatsDto getDashboardStats(User user);

    List<Task> getAllTasks(User user);

    List<Task> getTasksByFilter(User user, String filter);

    List<Task> getTasksByDate(User user, LocalDate date);

    Task getTaskById(Long id, User user);

    Task createTask(TaskDto taskDto, User user);

    Task updateTask(Long id, TaskDto taskDto, User user);

    void deleteTask(Long id, User user);

    Task markComplete(Long id, User user);

    Task markPending(Long id, User user);

    TaskDto toDto(Task task);

    void applyDto(Task task, TaskDto taskDto);
}
