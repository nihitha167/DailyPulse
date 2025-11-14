package com.nihitha.dailypulse.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.nihitha.dailypulse.entity.DailyCheckin;
import com.nihitha.dailypulse.exception.ResourceNotFoundException;
import com.nihitha.dailypulse.repository.DailyCheckinRepository;
import java.util.List;

// logging a daily check-in
@Service
public class DailyCheckinService {
    private final DailyCheckinRepository dailyCheckinRepository;

    public DailyCheckinService(DailyCheckinRepository dailyCheckinRepository) {
        this.dailyCheckinRepository = dailyCheckinRepository;
    } 

    // log afterday checkin
    public DailyCheckin logCheckin(DailyCheckin checkin){
        if(checkin.getDate() == null){
            throw new IllegalArgumentException("Date must not be null");
        }

        int mood = checkin.getMood();
        if (mood < 1 || mood > 5){
            throw new IllegalArgumentException("mood must be between 1 and 5");
        }

        return dailyCheckinRepository.save(checkin);
    }

    //getcheckin range between these dates
    public List<DailyCheckin> getCheckinsInRange(LocalDate from, LocalDate to){
        if (from == null || to == null){
            throw new IllegalArgumentException("both from and to should be provided");
        }
        if(from.isAfter(to)){
            throw new IllegalArgumentException("from date must be before or equal to date");
        }
        return dailyCheckinRepository.findByDateBetween(from, to);
    }

    public DailyCheckin getCheckinById(Long id){
        return dailyCheckinRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("checkin not found"));
    }

    public void deleteCheckin(Long id){
        DailyCheckin existing = getCheckinById(id);
        dailyCheckinRepository.delete(existing);
    }


    
}
