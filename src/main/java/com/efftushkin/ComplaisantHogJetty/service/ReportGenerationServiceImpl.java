package com.efftushkin.ComplaisantHogJetty.service;

import com.efftushkin.ComplaisantHogJetty.model.Report;
import com.efftushkin.ComplaisantHogJetty.model.Task;
import com.efftushkin.ComplaisantHogJetty.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

@Service
public class ReportGenerationServiceImpl implements ReportGenerationService {
    private WorkerService workerService;
    private TaskService taskService;

    @Autowired
    public ReportGenerationServiceImpl(WorkerService workerService, TaskService taskService) {
        this.workerService = workerService;
        this.taskService = taskService;
    }

    @Override
    public Report generateReport() {
        Report report = new Report();
        for (Worker worker : workerService.findAll()) {
            List<Task> workersTasks = taskService.findTasksAssignedToWorker(worker);
            totalDuration(workersTasks);
            Report.ReportEntry entry = new Report.ReportEntry(worker,
                    totalDuration(workersTasks),
                    totalSalary(workersTasks));
            report.addEntry(entry);
        }
        return report;
    }

    private Duration totalDuration(List<Task> workersTasks) {
        return workersTasks
                .stream()
                .map(task -> Duration.between(task.getFrom(), task.getTill()))
                .reduce(Duration::plus)
                .orElse(Duration.ZERO);
    }

    private BigDecimal totalSalary(List<Task> workersTasks) {
        return workersTasks
                .stream()
                .map(Task::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
