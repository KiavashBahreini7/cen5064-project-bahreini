package com.example.habittracker.domain;
   import java.time.LocalDate;
   import java.util.Objects;
   import java.util.UUID;
   public record HabitLog(UUID habitId, LocalDate date) {
       public HabitLog {
           Objects.requireNonNull(habitId, "habitId cannot be null");
           Objects.requireNonNull(date, "date cannot be null");
       }
   }
