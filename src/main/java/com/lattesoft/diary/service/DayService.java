package com.lattesoft.diary.service;

import com.lattesoft.diary.model.Day;
import com.lattesoft.diary.repository.DayRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class DayService {
    private final DayRepository dayRepository;

    public List<Day> getAllDays() {
        return dayRepository.findAll();
    }

    public Day getDayById(Long id) {
        return dayRepository.findById(id)
                .orElse(null);
    }

    @Transactional
    public Day createDay() {
        LocalDate today = LocalDate.now();

        if (dayRepository.findByDate(today).isPresent()) {
            throw new RuntimeException("Day already exists");
        }

        Day createdDay = new Day();
        createdDay.setDate(today);
        return dayRepository.save(createdDay);
    }

    @Transactional
    public void deleteDay(Long id) {
        if (!dayRepository.existsById(id)) {
            throw new RuntimeException("Day not found.");
        }

        dayRepository.deleteById(id);
    }
}
