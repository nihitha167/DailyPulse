package com.nihitha.dailypulse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.nihitha.dailypulse.entity.Habit;
import com.nihitha.dailypulse.service.HabitService;

// controller - accessible to outside world, it maps url /api/habits to methods.
// restcontroller - listen to http requests , calls service layer and return JSON responses.
@RestController
@RequestMapping("/api/habits") //base url for all methods
public class HabitController {
    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public ResponseEntity<Habit> createHabit(@RequestBody Habit habit){
        Habit created = habitService.createHabit(habit);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Habit>> getAllHabits(){
        List<Habit> habits = habitService.getAllHabits();

        return ResponseEntity.ok(habits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habit> getHabitById(@PathVariable Long id){
        Habit habit = habitService.getHabitById(id);

        return ResponseEntity.ok(habit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable Long id, @RequestBody Habit updatedHabit){
        Habit updated = habitService.updateHabit(id,updatedHabit);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id){
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }

    
}
