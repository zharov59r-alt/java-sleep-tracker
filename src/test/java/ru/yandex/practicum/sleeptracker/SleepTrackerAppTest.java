package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analysis.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepTrackerAppTest {

    @Test
    void checkAvg() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 6, 0),
                "GOOD"));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 8, 0),
                "GOOD"));

        Avg func = new Avg();
        assertEquals(420L, func.apply(sessions).value);
    }

    @Test
    void checkCount() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 9, 0),
                "GOOD"));

        Count func = new Count();
        assertEquals(1, func.apply(sessions).value);
    }

    @Test
    void checkMax() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 6, 0),
                "GOOD"));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 8, 0),
                "GOOD"));

        Max func = new Max();
        assertEquals(480, func.apply(sessions).value);
    }

    @Test
    void checkMin() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 6, 0),
                "GOOD"));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 8, 0),
                "GOOD"));

        Min func = new Min();
        assertEquals(360, func.apply(sessions).value);
    }

    @Test
    void checkCountBAD() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 6, 0),
                "GOOD"));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 8, 0),
                "BAD"));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 8, 0),
                "BAD"));

        CountBAD func = new CountBAD();
        assertEquals(2L, func.apply(sessions).value);
    }

    @Test
    void checkCountSleeplessBeginBeforeNightStartEndBeforeNightEnd() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 2, 23, 0),
                LocalDateTime.of(2026, 1, 3, 5, 0),
                "GOOD"));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 23, 0),
                LocalDateTime.of(2026, 1, 4, 5, 0),
                "GOOD"));

        CountSleepless func = new CountSleepless();
        assertEquals(0L, func.apply(sessions).value);
    }

    @Test
    void checkCountSleeplessBeginBeforeNightStartEndAfterNightEnd() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 2, 23, 0),
                LocalDateTime.of(2026, 1, 3, 8, 0),
                "GOOD"));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 23, 0),
                LocalDateTime.of(2026, 1, 4, 8, 0),
                "GOOD"));

        CountSleepless func = new CountSleepless();
        assertEquals(0L, func.apply(sessions).value);
    }



    @Test
    void checkCountSleeplessBeginAfterNightStartEndBeforeNightEnd() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 1, 0),
                LocalDateTime.of(2026, 1, 3, 5, 0),
                "GOOD"));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 4, 1, 0),
                LocalDateTime.of(2026, 1, 4, 5, 0),
                "GOOD"));

        CountSleepless func = new CountSleepless();
        assertEquals(0L, func.apply(sessions).value);
    }

    @Test
    void checkCountSleeplessBeginAfterNightStartEndAfterNightEnd() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 1, 0),
                LocalDateTime.of(2026, 1, 3, 8, 0),
                "GOOD"));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 1, 0),
                LocalDateTime.of(2026, 1, 4, 8, 0),
                "GOOD"));

        CountSleepless func = new CountSleepless();
        assertEquals(0L, func.apply(sessions).value);
    }

    @Test
    void checkCountSleeplessBeginBeforeNightStartEndBeforeNightStartAfterNoon() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 15, 0),
                LocalDateTime.of(2026, 1, 3, 18, 0),
                "GOOD"));

        CountSleepless func = new CountSleepless();
        assertEquals(1L, func.apply(sessions).value);
    }

    @Test
    void checkCountSleeplessBeginBeforeNightStartEndBeforeNightStartBeforeNoon() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 7, 0),
                LocalDateTime.of(2026, 1, 3, 11, 0),
                "GOOD"));

        CountSleepless func = new CountSleepless();
        assertEquals(1L, func.apply(sessions).value);
    }

    @Test
    void checkCountSleeplessWithSkippingDay() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 1, 0),
                LocalDateTime.of(2026, 1, 3, 8, 0),
                "GOOD"));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 5, 1, 0),
                LocalDateTime.of(2026, 1, 5, 8, 0),
                "GOOD"));

        CountSleepless func = new CountSleepless();
        assertEquals(1L, func.apply(sessions).value);
    }

    @Test
    void checkStatusOwl() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 0, 0),
                LocalDateTime.of(2026, 1, 3, 10, 0),
                "GOOD"));

        Status func = new Status();
        assertEquals("сова", func.apply(sessions).value);
    }

    @Test
    void checkStatusLark() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 2, 21, 0),
                LocalDateTime.of(2026, 1, 3, 6, 0),
                "GOOD"));

        Status func = new Status();
        assertEquals("жаворонок", func.apply(sessions).value);
    }

    @Test
    void checkStatusDove() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 2, 21, 0),
                LocalDateTime.of(2026, 1, 3, 10, 0),
                "GOOD"));

        Status func = new Status();
        assertEquals("голубь", func.apply(sessions).value);
    }

    @Test
    void checkStatusOwlPlusLark() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 0, 0),
                LocalDateTime.of(2026, 1, 3, 10, 0),
                "GOOD"));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 21, 0),
                LocalDateTime.of(2026, 1, 4, 6, 0),
                "GOOD"));

        Status func = new Status();
        assertEquals("голубь", func.apply(sessions).value);
    }

    @Test
    void checkStatusTwoOwlPlusLark() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 0, 0),
                LocalDateTime.of(2026, 1, 3, 10, 0),
                "GOOD"));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 4, 0, 0),
                LocalDateTime.of(2026, 1, 4, 10, 0),
                "GOOD"));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 4, 21, 0),
                LocalDateTime.of(2026, 1, 5, 6, 0),
                "GOOD"));

        Status func = new Status();
        assertEquals("сова", func.apply(sessions).value);
    }

    @Test
    void checkStatusWithoutSessions() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 7, 0),
                LocalDateTime.of(2026, 1, 3, 11, 0),
                "GOOD"));

        Status func = new Status();
        assertEquals("голубь", func.apply(sessions).value);
    }

}