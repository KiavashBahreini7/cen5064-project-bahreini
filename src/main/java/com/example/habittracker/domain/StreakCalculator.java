package com.example.habittracker.domain;
   import java.time.Clock;
   import java.time.LocalDate;
   import java.util.Comparator;
   import java.util.List;
   import java.util.Set;
   import java.util.UUID;
   public class StreakCalculator {
       private final Clock clock;
       public StreakCalculator(Clock clock) {
           this.clock = clock;
       }
       public int currentStreak(Set<HabitLog> logs, UUID habitId) {
           List<LocalDate> dates = logs.stream()
                   .filter(l -> l.habitId().equals(habitId))
                   .map(HabitLog::date)
                   .sorted(Comparator.reverseOrder())
                   .toList();
           int streak = 0;
           LocalDate expected = LocalDate.now(clock);
           for (LocalDate d : dates) {
               if (d.equals(expected)) {
                   streak++;
                   expected = expected.minusDays(1);
               } else {
                   break;
               }
           }
           return streak;
       }
   }
