package com.nihitha.dailypulse.entity;
/*An entity is a normal Java class that is mapped to a database table. Each field is a column and one column would be a primary key.
Spring Data JPA does the SQL for us.
This makes code cleaner, safer, and easier to maintain.
Entity - how data looks like*/

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// @Entity - class represents a table
@Entity
public class Habit {

    @Id // primary key column
    @GeneratedValue(strategy = GenerationType.IDENTITY) //let database auto-generate values
    private Long id; 

    @Column(nullable = false)
    private String name; 

    private String description; 

    private LocalDateTime createdAt;

    //constructors
    //JPA requires no arg constructor
     public Habit() {
    }

    public Habit(String name, String description) {
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }



    

   
    

    


    
}
