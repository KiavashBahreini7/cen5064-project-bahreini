package com.example.habittracker.service;
   import com.example.habittracker.domain.HabitLog;
   import com.example.habittracker.domain.StreakCalculator;
   import java.time.Clock;
   import java.time.LocalDate;
   import java.util.Collections;
   import java.util.HashSet;
   import java.util.Set;
   import java.util.UUID;
   public class HabitService {
       private final Set<HabitLog> logs = new HashSet<>();
       private final Clock clock;
       private final StreakCalculator streakCalculator;
       public HabitService(Clock clock, StreakCalculator streakCalculator) {
           this.clock = clock;
           this.streakCalculator = streakCalculator;
       }
       public void markDone(UUID habitId) {
           if (habitId == null) {
               throw new IllegalArgumentException("Habit ID cannot be null");
           }
           logs.add(new HabitLog(habitId, LocalDate.now(clock)));
       }
       public Set<HabitLog> getLogs() {
           return Collections.unmodifiableSet(logs);
       }
       public int getStreak(UUID habitId) {
           return streakCalculator.currentStreak(logs, habitId);
       }
   }
