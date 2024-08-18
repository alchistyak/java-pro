package tech.inno.task8.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.inno.task8.dto.ReportResponse;
import tech.inno.task8.service.ReportService;

// Отчет по пользователю о лимитах и совершенных платежах

@RestController
@RequestMapping("/api/v1")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/report/{id}")
    public ReportResponse report(@PathVariable(name = "id") Long userId) {
        return reportService.reportByUserId(userId);
    }
}
