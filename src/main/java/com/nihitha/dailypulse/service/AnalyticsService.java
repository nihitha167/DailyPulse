package com.nihitha.dailypulse.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nihitha.dailypulse.dto.AnalyticsSummary;
import com.nihitha.dailypulse.entity.DailyCheckin;
import com.nihitha.dailypulse.repository.DailyCheckinRepository;

@Service
public class AnalyticsService {
    private final DailyCheckinRepository dailyCheckinRepository;

    public AnalyticsService(DailyCheckinRepository dailyCheckinRepository) {
        this.dailyCheckinRepository = dailyCheckinRepository;
    } 

    public AnalyticsSummary getSummary(LocalDate from, LocalDate to){
        List<DailyCheckin> checkins = dailyCheckinRepository.findByDateBetween(from, to);

        long totalCheckins = checkins.size();

        double averageMood = 0.0;

        if(!checkins.isEmpty()){
            averageMood = checkins.stream()
                            .mapToInt(DailyCheckin::getMood)
                            .average()
                            .orElse(0.0);
        }

        long totalCompletedHabits = checkins.stream()
                                            .mapToLong(c -> c.getCompletedHabitIds() != null ? c.getCompletedHabitIds().size() : 0)
                                            .sum();

        return new AnalyticsSummary(from, to, totalCheckins, averageMood, totalCompletedHabits);
    }
}
