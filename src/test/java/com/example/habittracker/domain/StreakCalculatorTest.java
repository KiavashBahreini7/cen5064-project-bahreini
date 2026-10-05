package com.example.habittracker.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StreakCalculatorTest {

    private static final ZoneId ZONE = ZoneId.of("UTC");
    private static final LocalDate TODAY = LocalDate.of(2026, 9, 28);

    private Clock fixedClock;
    private StreakCalculator streakCalculator;
    private UUID habitId;

    @BeforeEach
    void setUp() {
        fixedClock = Clock.fixed(TODAY.atStartOfDay(ZONE).toInstant(), ZONE);
        streakCalculator = new StreakCalculator(fixedClock);
        habitId = UUID.randomUUID();
    }

    @Test
    void returnsZeroWhenLogsAreEmpty() {
        int streak = streakCalculator.currentStreak(Collections.emptySet(), habitId);
        assertEquals(0, streak);
    }

    @Test
    void returnsZeroWhenNoLogsForToday() {
        Set<HabitLog> logs = Set.of(new HabitLog(habitId, TODAY.minusDays(1)));
        assertEquals(0, streakCalculator.currentStreak(logs, habitId));
    }

    @Test
    void countsConsecutiveDaysBackFromToday() {
        Set<HabitLog> logs = Set.of(
            new HabitLog(habitId, TODAY),
            new HabitLog(habitId, TODAY.minusDays(1)),
            new HabitLog(habitId, TODAY.minusDays(2)));
        assertEquals(3, streakCalculator.currentStreak(logs, habitId));
    }

    @Test
    void stopsCountingWhenStreakIsBroken() {
        Set<HabitLog> logs = Set.of(
            new HabitLog(habitId, TODAY),
            new HabitLog(habitId, TODAY.minusDays(1)),
            new HabitLog(habitId, TODAY.minusDays(3)));
        assertEquals(2, streakCalculator.currentStreak(logs, habitId));
    }

    @Test
    void ignoresLogsForOtherHabits() {
        UUID otherHabitId = UUID.randomUUID();
        Set<HabitLog> logs = Set.of(
            new HabitLog(habitId, TODAY),
            new HabitLog(otherHabitId, TODAY.minusDays(1)));
        assertEquals(1, streakCalculator.currentStreak(logs, habitId));
    }
}
