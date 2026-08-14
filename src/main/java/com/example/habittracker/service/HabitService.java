package com.example.habittracker.service;
   import com.example.habittracker.domain.HabitLog;
   import java.time.LocalDate;
   import java.util.Collections;
   import java.util.HashSet;
   import java.util.Set;
   import java.util.UUID;
   public class HabitService {
       private final Set<HabitLog> logs = new HashSet<>();
       public void markDone(UUID habitId) {
           if (habitId == null) {
               throw new IllegalArgumentException("Habit ID cannot be null");
           }
           HabitLog log = new HabitLog(habitId, LocalDate.now());
           logs.add(log);
       }
       public Set<HabitLog> getLogs() {
           return Collections.unmodifiableSet(logs);
       }
   }
