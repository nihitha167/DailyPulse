package com.nihitha.dailypulse.dto;

import java.time.LocalDate;

public class AnalyticsSummary {
    
    private LocalDate from;
    private LocalDate to;
    private long totalCheckins;
    private double averageMood;
    private long totalCompletedHabits;

    public AnalyticsSummary(LocalDate from, LocalDate to, long totalCheckins, double averageMood,
            long totalCompletedHabits) {
        this.from = from;
        this.to = to;
        this.totalCheckins = totalCheckins;
        this.averageMood = averageMood;
        this.totalCompletedHabits = totalCompletedHabits;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public long getTotalCheckins() {
        return totalCheckins;
    }

    public double getAverageMood() {
        return averageMood;
    }

    public long getTotalCompletedHabits() {
        return totalCompletedHabits;
    }

    
    
}
