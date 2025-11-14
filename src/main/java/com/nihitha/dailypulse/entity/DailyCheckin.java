package com.nihitha.dailypulse.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

// this table represents user data log
@Entity
public class DailyCheckin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id; 

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int mood;

    private String notes;

    // List of habits completed on that day 
    // @elementcollection tells JPA to create a seperate table to store the list and link them back to entity
    @ElementCollection
    private List<Long> completedHabitIds = new ArrayList<>();

    // constructors
    public DailyCheckin() {
    }

    public DailyCheckin(LocalDate date, int mood, String notes) {
        this.date = date;
        this.mood = mood;
        this.notes = notes;
    }

    // getters and setters

    public Long getId() {
        return Id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Long> getCompletedHabitIds() {
        return completedHabitIds;
    }

    public void setCompletedHabitIds(List<Long> completedHabitIds) {
        this.completedHabitIds = completedHabitIds;
    }

   
    

    



}
