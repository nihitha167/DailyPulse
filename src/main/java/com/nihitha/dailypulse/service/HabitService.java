package com.nihitha.dailypulse.service;
// service - business logic and rules that are specific for our application eg dont create duplicate names or ensure mood is logged in 
// HabitService - for creating, updating and deleting habits

import org.springframework.stereotype.Service;
import com.nihitha.dailypulse.exception.ResourceNotFoundException;
import com.nihitha.dailypulse.entity.Habit;
import com.nihitha.dailypulse.repository.HabitRepository;
import java.util.*;

@Service
public class HabitService {
    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public Habit createHabit(Habit habit){
        // avoid duplicate values
        if(habitRepository.existsByName(habit.getName())){
            throw new IllegalArgumentException("Habit with this name already exists");
        }

         if (habit.getCreatedAt() == null) {
            habit.setCreatedAt(java.time.LocalDateTime.now());
        }
        
        return habitRepository.save(habit);
    }

     public List<Habit> getAllHabits(){
        
        return habitRepository.findAll();
    }

     public Habit getHabitById(Long id){
        
        return habitRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Habit not found" + id));
    }

     public Habit updateHabit(Long id, Habit updatedHabit){
        Habit existing = getHabitById(id); 

        existing.setName(updatedHabit.getName());
        existing.setDescription(updatedHabit.getDescription());

        
        return habitRepository.save(existing);
    }

    public void deleteHabit(Long id){
        Habit existing = getHabitById(id);
        habitRepository.delete(existing);
    }
    

    
}
