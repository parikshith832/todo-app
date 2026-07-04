package com.todoapp.service.impl;

import com.todoapp.dto.DashboardStatsDto;
import com.todoapp.dto.TaskDto;
import com.todoapp.entity.Task;
import com.todoapp.entity.TaskStatus;
import com.todoapp.entity.User;
import com.todoapp.repository.TaskRepository;
import com.todoapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public DashboardStatsDto getDashboardStats(User user) {
        LocalDate today = LocalDate.now();
        return DashboardStatsDto.builder()
                .totalTasks(taskRepository.countByUser(user))
                .pendingTasks(taskRepository.countByUserAndStatus(user, TaskStatus.PENDING))
                .completedTasks(taskRepository.countByUserAndStatus(user, TaskStatus.COMPLETED))
                .todayTasks(taskRepository.countByUserAndDueDate(user, today))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllTasks(User user) {
        return taskRepository.findByUserOrderByDueDateAscDueTimeAsc(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByFilter(User user, String filter) {
        if (filter == null || filter.isBlank()) {
            return getAllTasks(user);
        }
        return switch (filter.toLowerCase()) {
            case "pending" -> taskRepository.findByUserAndStatusOrderByDueDateAscDueTimeAsc(user, TaskStatus.PENDING);
            case "completed" -> taskRepository.findByUserAndStatusOrderByDueDateAscDueTimeAsc(user, TaskStatus.COMPLETED);
            case "today" -> taskRepository.findTodayTasks(user, LocalDate.now());
            case "overdue" -> taskRepository.findOverdueTasks(user, LocalDate.now());
            default -> getAllTasks(user);
        };
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByDate(User user, LocalDate date) {
        return taskRepository.findByUserAndDueDateOrderByDueTimeAsc(user, date);
    }

    @Override
    @Transactional(readOnly = true)
    public Task getTaskById(Long id, User user) {
        return taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    @Override
    @Transactional
    public Task createTask(TaskDto taskDto, User user) {
        Task task = Task.builder()
                .user(user)
                .build();
        applyDto(task, taskDto);
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task updateTask(Long id, TaskDto taskDto, User user) {
        Task task = getTaskById(id, user);
        applyDto(task, taskDto);
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public void deleteTask(Long id, User user) {
        Task task = getTaskById(id, user);
        taskRepository.delete(task);
    }

    @Override
    @Transactional
    public Task markComplete(Long id, User user) {
        Task task = getTaskById(id, user);
        task.setStatus(TaskStatus.COMPLETED);
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task markPending(Long id, User user) {
        Task task = getTaskById(id, user);
        task.setStatus(TaskStatus.PENDING);
        return taskRepository.save(task);
    }

    @Override
    public TaskDto toDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setDueDate(task.getDueDate());
        dto.setDueTime(task.getDueTime());
        dto.setPriority(task.getPriority());
        dto.setStatus(task.getStatus());
        dto.setCategory(task.getCategory());
        dto.setRemarks(task.getRemarks());
        return dto;
    }

    @Override
    public void applyDto(Task task, TaskDto taskDto) {
        task.setTitle(taskDto.getTitle().trim());
        task.setDescription(taskDto.getDescription() != null ? taskDto.getDescription().trim() : null);
        task.setDueDate(taskDto.getDueDate());
        task.setDueTime(taskDto.getDueTime());
        task.setPriority(taskDto.getPriority() != null ? taskDto.getPriority() : com.todoapp.entity.Priority.MEDIUM);
        task.setStatus(taskDto.getStatus() != null ? taskDto.getStatus() : TaskStatus.PENDING);
        task.setCategory(taskDto.getCategory() != null ? taskDto.getCategory().trim() : null);
        task.setRemarks(taskDto.getRemarks() != null ? taskDto.getRemarks().trim() : null);
    }
}
