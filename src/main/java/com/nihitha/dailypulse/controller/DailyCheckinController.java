package com.nihitha.dailypulse.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.nihitha.dailypulse.entity.DailyCheckin;
import com.nihitha.dailypulse.service.DailyCheckinService;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/checkins")
public class DailyCheckinController {
    private final DailyCheckinService dailyCheckinService;

    public DailyCheckinController(DailyCheckinService dailyCheckinService) {
        this.dailyCheckinService = dailyCheckinService;
    } 

    @PostMapping
    public ResponseEntity<DailyCheckin> logCheckin(@RequestBody DailyCheckin checkin){
        DailyCheckin created = dailyCheckinService.logCheckin(checkin);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<DailyCheckin>> getCheckinsInRange(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate from,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate to
    ){
        List<DailyCheckin> checkins = dailyCheckinService.getCheckinsInRange(from, to);
        return ResponseEntity.ok(checkins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DailyCheckin> getCheckinById(@PathVariable Long id) {
        DailyCheckin checkin = dailyCheckinService.getCheckinById(id);
        return ResponseEntity.ok(checkin);
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckin(@PathVariable Long id) {
        dailyCheckinService.deleteCheckin(id);
        return ResponseEntity.noContent().build();
    }

    
}
