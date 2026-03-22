package com.lattesoft.diary.controller;

import com.lattesoft.diary.model.Day;
import com.lattesoft.diary.service.DayService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/days")
@AllArgsConstructor
public class DayController {
    private final DayService dayService;

    @GetMapping
    public ResponseEntity<List<Day>> getAll() {
        return ResponseEntity.ok(dayService.getAllDays());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Day> getById(@PathVariable Long id) {
        return ResponseEntity.ok(dayService.getDayById(id));
    }

    @PostMapping
    public ResponseEntity<Day> create() {
        return ResponseEntity.ok(dayService.createDay());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dayService.deleteDay(id);
        return ResponseEntity.noContent().build();
    }
}
