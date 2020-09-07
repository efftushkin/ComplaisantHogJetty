package com.efftushkin.ComplaisantHogJetty.controller;

import com.efftushkin.ComplaisantHogJetty.model.Report;
import com.efftushkin.ComplaisantHogJetty.service.ReportGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("report")
@Controller
public class ReportController {
    private final ReportGenerationService reportGenerationService;

    @Autowired
    public ReportController(ReportGenerationService reportGenerationService) {
        this.reportGenerationService = reportGenerationService;
    }

    @GetMapping
    public Report generateReport() {
        return reportGenerationService.generateReport();
    }
}
