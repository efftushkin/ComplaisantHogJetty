package com.efftushkin.ComplaisantHogJetty.controller;

import com.efftushkin.ComplaisantHogJetty.model.Worker;
import com.efftushkin.ComplaisantHogJetty.repository.WorkerRepository;
import com.efftushkin.ComplaisantHogJetty.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/workers")
public class WorkerController {
    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping
    public void addWorker(@RequestParam("first_name") String firstName, @RequestParam("last_name") String lastName) {
        workerService.createWorker(firstName, lastName);
    }

    @GetMapping
    public List<Worker> findAll() {
        return workerService.findAll();
    }
}
