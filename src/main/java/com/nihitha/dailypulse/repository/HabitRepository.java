package com.nihitha.dailypulse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nihitha.dailypulse.entity.Habit;

/*
A repository is an interface where you define how you talk to the database.
With Spring Data JPA:
- You extend JpaRepository<EntityType, IdType>.
- Spring auto-generates the implementation at runtime.
- You get CRUD methods for free
 */

// this is for habit entity
public interface HabitRepository extends JpaRepository<Habit, Long> {
    
    /*Derived query methods in Spring Data JPA repositories allow developers to define database queries 
    simply by declaring method signatures in their repository interfaces, without needing to write explicit JPQL or SQL. 
    Spring Data JPA analyzes these method names to automatically generate the corresponding query at runtime. */
    // introducer keyword (eg : find, exists, query, count, get etc) + By + entity property + (optionally and/or)
    boolean existsByName(String name);
    /* Spring will generate a query like:
      SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END
      FROM Habit h WHERE h.name = :name
     */
}
