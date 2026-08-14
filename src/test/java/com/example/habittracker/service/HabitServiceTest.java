package com.example.habittracker.service;
   import com.example.habittracker.domain.HabitLog;
   import org.junit.jupiter.api.BeforeEach;
   import org.junit.jupiter.api.DisplayName;
   import org.junit.jupiter.api.Test;
   import java.time.LocalDate;
   import java.util.UUID;
   import static org.junit.jupiter.api.Assertions.assertEquals;
   import static org.junit.jupiter.api.Assertions.assertTrue;
   class HabitServiceTest {
       private HabitService habitService;
       @BeforeEach
       void setUp() {
           habitService = new HabitService();
       }
       @Test
       @DisplayName("Should mark habit done for current day and ignore duplicate attempts")
       void testMarkDoneSingleAndDuplicate() {
           UUID habitId = UUID.randomUUID();
           LocalDate today = LocalDate.now();
           habitService.markDone(habitId);
           assertEquals(1, habitService.getLogs().size());
           assertTrue(habitService.getLogs().contains(new HabitLog(habitId, today)));
           habitService.markDone(habitId);
           assertEquals(1, habitService.getLogs().size());
       }
   }
