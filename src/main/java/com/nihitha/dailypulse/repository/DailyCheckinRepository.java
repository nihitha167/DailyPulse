package com.nihitha.dailypulse.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nihitha.dailypulse.entity.DailyCheckin;
import java.util.List;

// repository for dailycheckin entity
public interface DailyCheckinRepository extends JpaRepository<DailyCheckin, Long>{
    
    List<DailyCheckin> findByDateBetween(LocalDate from, LocalDate to);
    /*
     * Find all check-ins between two dates (inclusive).
       Spring will generate a query similar to:
       SELECT d FROM DailyCheckin d WHERE d.date BETWEEN :from AND :to
     */
}
