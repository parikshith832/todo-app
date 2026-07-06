package com.todoapp.repository;

import com.todoapp.entity.Task;
import com.todoapp.entity.TaskStatus;
import com.todoapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTop5ByUserOrderByIdDesc(User user);

    List<Task> findByUserOrderByDueDateAscDueTimeAsc(User user);

    List<Task> findByUserAndStatusOrderByDueDateAscDueTimeAsc(User user, TaskStatus status);

    List<Task> findByUserAndDueDateOrderByDueTimeAsc(User user, LocalDate dueDate);

    @Query("SELECT t FROM Task t WHERE t.user = :user AND t.dueDate = :date ORDER BY t.dueTime ASC")
    List<Task> findTodayTasks(@Param("user") User user, @Param("date") LocalDate date);

    @Query("SELECT t FROM Task t WHERE t.user = :user AND t.status = 'PENDING' AND t.dueDate < :date ORDER BY t.dueDate ASC")
    List<Task> findOverdueTasks(@Param("user") User user, @Param("date") LocalDate date);

    Optional<Task> findByIdAndUser(Long id, User user);

    long countByUser(User user);

    long countByUserAndStatus(User user, TaskStatus status);

    long countByUserAndDueDate(User user, LocalDate dueDate);
}
