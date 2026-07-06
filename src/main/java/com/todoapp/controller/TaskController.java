package com.todoapp.controller;

import com.todoapp.dto.TaskDto;
import com.todoapp.entity.Priority;
import com.todoapp.entity.TaskStatus;
import com.todoapp.entity.User;
import com.todoapp.security.CurrentUser;
import com.todoapp.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final CurrentUser currentUser;

    @GetMapping
    public String listTasks(@RequestParam(required = false) String filter, Model model) {
        User user = currentUser.getUser();
        model.addAttribute("tasks", taskService.getTasksByFilter(user, filter));
        model.addAttribute("filter", filter != null ? filter : "all");
        model.addAttribute("user", user);
        return "tasks/list";
    }

    @GetMapping("/new")
    public String newTaskForm(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
            Model model) {
        TaskDto taskDto = new TaskDto();
        if (dueDate != null) {
            taskDto.setDueDate(dueDate);
        }
        populateFormModel(model, taskDto, false);
        return "tasks/form";
    }

    @PostMapping
    public String createTask(@Valid @ModelAttribute("taskDto") TaskDto taskDto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            populateFormModel(model, taskDto, false);
            return "tasks/form";
        }
        User user = currentUser.getUser();
        taskService.createTask(taskDto, user);
        redirectAttributes.addFlashAttribute("toast", "created");
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String viewTask(@PathVariable Long id, Model model) {
        User user = currentUser.getUser();
        model.addAttribute("task", taskService.getTaskById(id, user));
        model.addAttribute("user", user);
        return "tasks/detail";
    }

    @GetMapping("/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model) {
        User user = currentUser.getUser();
        TaskDto taskDto = taskService.toDto(taskService.getTaskById(id, user));
        populateFormModel(model, taskDto, true);
        return "tasks/form";
    }

    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id,
            @Valid @ModelAttribute("taskDto") TaskDto taskDto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            populateFormModel(model, taskDto, true);
            return "tasks/form";
        }
        User user = currentUser.getUser();
        taskService.updateTask(id, taskDto, user);
        redirectAttributes.addFlashAttribute("toast", "updated");
        return "redirect:/tasks/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        User user = currentUser.getUser();
        taskService.deleteTask(id, user);
        redirectAttributes.addFlashAttribute("toast", "deleted");
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/complete")
    public String markComplete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        User user = currentUser.getUser();
        taskService.markComplete(id, user);
        redirectAttributes.addFlashAttribute("toast", "completed");
        return "redirect:/tasks/" + id;
    }

    @PostMapping("/{id}/pending")
    public String markPending(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        User user = currentUser.getUser();
        taskService.markPending(id, user);
        redirectAttributes.addFlashAttribute("toast", "pending");
        return "redirect:/tasks/" + id;
    }

    private void populateFormModel(Model model, TaskDto taskDto, boolean isEdit) {
        model.addAttribute("taskDto", taskDto);
        model.addAttribute("isEdit", isEdit);
        model.addAttribute("priorities", Arrays.asList(Priority.values()));
        model.addAttribute("statuses", Arrays.asList(TaskStatus.values()));
        model.addAttribute("categories", getCategories());
        model.addAttribute("user", currentUser.getUser());
    }

    private List<String> getCategories() {
        return List.of("Personal", "Work", "Shopping", "Health", "Finance", "Other");
    }
}
