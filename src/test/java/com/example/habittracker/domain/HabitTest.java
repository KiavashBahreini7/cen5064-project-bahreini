package com.example.habittracker.domain;
   import org.junit.jupiter.api.Test;
   import static org.junit.jupiter.api.Assertions.assertEquals;
   class HabitTest {
       @Test
       void minimalHabitNeedsOnlyName() {
           Habit h = new Habit.Builder("Read").build();
           assertEquals("Read", h.name());
           assertEquals("", h.reminderTime());
       }
       @Test
       void fullHabitWithAllOptionalFields() {
           Habit h = new Habit.Builder("Exercise")
                   .reminderTime("07:00")
                   .colorTag("green")
                   .notes("30 min minimum")
                   .build();
           assertEquals("07:00", h.reminderTime());
           assertEquals("green", h.colorTag());
       }
   }
