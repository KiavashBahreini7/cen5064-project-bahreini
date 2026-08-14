package com.example.habittracker.service;
   import com.example.habittracker.domain.HabitLog;
   import java.time.Clock;
   import java.time.LocalDate;
   import java.util.Collections;
   import java.util.Comparator;
   import java.util.HashSet;
   import java.util.List;
   import java.util.Set;
   import java.util.UUID;
   public class HabitService {
       private final Set<HabitLog> logs = new HashSet<>();
       private final Clock clock;
       public HabitService(Clock clock) {
           this.clock = clock;
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
