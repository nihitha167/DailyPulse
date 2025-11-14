package com.nihitha.dailypulse.controller;

import com.nihitha.dailypulse.dto.AnalyticsSummary;
import com.nihitha.dailypulse.service.AnalyticsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    /**
     * GET /api/analytics/summary?from=2025-11-01&to=2025-11-13
     */
    @GetMapping("/summary")
    public ResponseEntity<AnalyticsSummary> getSummary(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to
    ) {
        AnalyticsSummary summary = analyticsService.getSummary(from, to);
        return ResponseEntity.ok(summary);
    }
}
