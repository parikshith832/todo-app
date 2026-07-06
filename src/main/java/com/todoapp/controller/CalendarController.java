package com.todoapp.controller;

import com.todoapp.entity.Task;
import com.todoapp.entity.User;
import com.todoapp.security.CurrentUser;
import com.todoapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final TaskService taskService;
    private final CurrentUser currentUser;

    @GetMapping
    public String calendar(@RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate selectedDate,
            Model model) {
        User user = currentUser.getUser();
        YearMonth yearMonth = resolveYearMonth(year, month);
        LocalDate date = selectedDate != null ? selectedDate : LocalDate.now();
        List<Task> allTasks = taskService.getAllTasks(user);

        model.addAttribute("user", user);
        model.addAttribute("yearMonth", yearMonth);
        model.addAttribute("selectedDate", date);
        model.addAttribute("tasksOnDate", taskService.getTasksByDate(user, date));
        model.addAttribute("tasksByDate", buildTasksByDate(allTasks));
        return "calendar";
    }

    private Map<String, List<Map<String, Object>>> buildTasksByDate(List<Task> tasks) {
        Map<String, List<Map<String, Object>>> map = new HashMap<>();
        for (Task task : tasks) {
            if (task.getDueDate() == null) {
                continue;
            }
            String key = task.getDueDate().toString();
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(Map.of(
                    "id", task.getId(),
                    "title", task.getTitle(),
                    "status", task.getStatus().name(),
                    "dueTime", task.getDueTime() != null ? task.getDueTime().toString() : ""));
        }
        return map;
    }

    private YearMonth resolveYearMonth(Integer year, Integer month) {
        if (year != null && month != null) {
            return YearMonth.of(year, month);
        }
        return YearMonth.now();
    }
}
