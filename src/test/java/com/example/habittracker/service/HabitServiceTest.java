package com.example.habittracker.service;
   import com.example.habittracker.domain.HabitLog;
   import org.junit.jupiter.api.BeforeEach;
   import org.junit.jupiter.api.DisplayName;
   import org.junit.jupiter.api.Test;
   import java.time.Clock;
   import java.time.Instant;
   import java.time.LocalDate;
   import java.time.ZoneId;
   import java.util.UUID;
   import static org.junit.jupiter.api.Assertions.assertEquals;
   import static org.junit.jupiter.api.Assertions.assertTrue;
   class HabitServiceTest {
       private HabitService habitService;
       private LocalDate today;
       @BeforeEach
       void setUp() {
           Clock fixedClock = Clock.fixed(Instant.parse("2026-08-24T10:00:00Z"), ZoneId.of("UTC"));
           today = LocalDate.now(fixedClock);
           habitService = new HabitService(fixedClock);
       }
       @Test
       @DisplayName("Should mark habit done for current day and ignore duplicate attempts")
       void testMarkDoneSingleAndDuplicate() {
           UUID habitId = UUID.randomUUID();
           habitService.markDone(habitId);
           assertEquals(1, habitService.getLogs().size());
           assertTrue(habitService.getLogs().contains(new HabitLog(habitId, today)));
           habitService.markDone(habitId);
           assertEquals(1, habitService.getLogs().size());
       }
   }
