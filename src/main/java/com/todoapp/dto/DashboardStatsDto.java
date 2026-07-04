package com.todoapp.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DashboardStatsDto {

    private long totalTasks;
    private long pendingTasks;
    private long completedTasks;
    private long todayTasks;
}
