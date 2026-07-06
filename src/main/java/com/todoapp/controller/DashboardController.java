package com.todoapp.controller;

import com.todoapp.entity.User;
import com.todoapp.security.CurrentUser;
import com.todoapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

        private final TaskService taskService;
        private final CurrentUser currentUser;

        @GetMapping("/dashboard")
        public String dashboard(Model model) {

                User user = currentUser.getUser();

                model.addAttribute("user", user);

                model.addAttribute("stats",
                                taskService.getDashboardStats(user));

                model.addAttribute("todayTasks",
                                taskService.getTodayTasks(user));
                model.addAttribute("recentTasks",
                                taskService.getRecentTasks(user));

                return "dashboard";
        }
}