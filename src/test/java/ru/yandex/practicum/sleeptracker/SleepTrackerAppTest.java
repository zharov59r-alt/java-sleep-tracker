package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analysis.*;
import ru.yandex.practicum.sleeptracker.enums.Chronotype;
import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

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
                SleepQuality.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 8, 0),
                SleepQuality.GOOD));

        Avg func = new Avg();
        assertEquals(420L, func.apply(sessions).value);
    }

    @Test
    void checkCount() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 9, 0),
                SleepQuality.GOOD));

        Count func = new Count();
        assertEquals(1, func.apply(sessions).value);
    }

    @Test
    void checkMax() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 6, 0),
                SleepQuality.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 8, 0),
                SleepQuality.GOOD));

        Max func = new Max();
        assertEquals(480, func.apply(sessions).value);
    }

    @Test
    void checkMin() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 6, 0),
                SleepQuality.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 8, 0),
                SleepQuality.GOOD));

        Min func = new Min();
        assertEquals(360, func.apply(sessions).value);
    }

    @Test
    void checkCountBAD() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 6, 0),
                SleepQuality.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 8, 0),
                SleepQuality.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 1, 0, 0),
                LocalDateTime.of(2026, 1, 1, 8, 0),
                SleepQuality.BAD));

        CountBAD func = new CountBAD();
        assertEquals(1L, func.apply(sessions).value);
    }

    @Test
    void checkCountSleeplessBeginBeforeNightStartEndBeforeNightEnd() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 2, 23, 0),
                LocalDateTime.of(2026, 1, 3, 5, 0),
                SleepQuality.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 23, 0),
                LocalDateTime.of(2026, 1, 4, 5, 0),
                SleepQuality.GOOD));

        CountSleepless func = new CountSleepless();
        assertEquals(0L, func.apply(sessions).value);
    }

    @Test
    void checkCountSleeplessBeginBeforeNightStartEndAfterNightEnd() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 2, 23, 0),
                LocalDateTime.of(2026, 1, 3, 8, 0),
                SleepQuality.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 23, 0),
                LocalDateTime.of(2026, 1, 4, 8, 0),
                SleepQuality.GOOD));

        CountSleepless func = new CountSleepless();
        assertEquals(0L, func.apply(sessions).value);
    }



    @Test
    void checkCountSleeplessBeginAfterNightStartEndBeforeNightEnd() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 1, 0),
                LocalDateTime.of(2026, 1, 3, 5, 0),
                SleepQuality.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 4, 1, 0),
                LocalDateTime.of(2026, 1, 4, 5, 0),
                SleepQuality.GOOD));

        CountSleepless func = new CountSleepless();
        assertEquals(0L, func.apply(sessions).value);
    }

    @Test
    void checkCountSleeplessBeginAfterNightStartEndAfterNightEnd() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 1, 0),
                LocalDateTime.of(2026, 1, 3, 8, 0),
                SleepQuality.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 1, 0),
                LocalDateTime.of(2026, 1, 4, 8, 0),
                SleepQuality.GOOD));

        CountSleepless func = new CountSleepless();
        assertEquals(0L, func.apply(sessions).value);
    }

    @Test
    void checkCountSleeplessBeginBeforeNightStartEndBeforeNightStartAfterNoon() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 15, 0),
                LocalDateTime.of(2026, 1, 3, 18, 0),
                SleepQuality.GOOD));

        CountSleepless func = new CountSleepless();
        assertEquals(1L, func.apply(sessions).value);
    }

    @Test
    void checkCountSleeplessBeginBeforeNightStartEndBeforeNightStartBeforeNoon() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 7, 0),
                LocalDateTime.of(2026, 1, 3, 11, 0),
                SleepQuality.GOOD));

        CountSleepless func = new CountSleepless();
        assertEquals(1L, func.apply(sessions).value);
    }

    @Test
    void checkCountSleeplessWithSkippingDay() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 1, 0),
                LocalDateTime.of(2026, 1, 3, 8, 0),
                SleepQuality.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 5, 1, 0),
                LocalDateTime.of(2026, 1, 5, 8, 0),
                SleepQuality.GOOD));

        CountSleepless func = new CountSleepless();
        assertEquals(1L, func.apply(sessions).value);
    }

    @Test
    void checkStatusOwl() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 0, 0),
                LocalDateTime.of(2026, 1, 3, 10, 0),
                SleepQuality.GOOD));

        Status func = new Status();
        assertEquals(Chronotype.OWL, func.apply(sessions).value);
    }

    @Test
    void checkStatusLark() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 2, 21, 0),
                LocalDateTime.of(2026, 1, 3, 6, 0),
                SleepQuality.GOOD));

        Status func = new Status();
        assertEquals(Chronotype.LARK, func.apply(sessions).value);
    }

    @Test
    void checkStatusDove() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 2, 21, 0),
                LocalDateTime.of(2026, 1, 3, 10, 0),
                SleepQuality.GOOD));

        Status func = new Status();
        assertEquals(Chronotype.PIGEON, func.apply(sessions).value);
    }

    @Test
    void checkStatusOwlPlusLark() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 0, 0),
                LocalDateTime.of(2026, 1, 3, 10, 0),
                SleepQuality.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 21, 0),
                LocalDateTime.of(2026, 1, 4, 6, 0),
                SleepQuality.GOOD));

        Status func = new Status();
        assertEquals(Chronotype.PIGEON, func.apply(sessions).value);
    }

    @Test
    void checkStatusTwoOwlPlusLark() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 0, 0),
                LocalDateTime.of(2026, 1, 3, 10, 0),
                SleepQuality.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 4, 0, 0),
                LocalDateTime.of(2026, 1, 4, 10, 0),
                SleepQuality.GOOD));
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 4, 21, 0),
                LocalDateTime.of(2026, 1, 5, 6, 0),
                SleepQuality.GOOD));

        Status func = new Status();
        assertEquals(Chronotype.OWL, func.apply(sessions).value);
    }

    @Test
    void checkStatusWithoutSessions() {
        List<SleepingSession> sessions = new ArrayList<>();
        sessions.add(new SleepingSession(
                LocalDateTime.of(2026, 1, 3, 7, 0),
                LocalDateTime.of(2026, 1, 3, 11, 0),
                SleepQuality.GOOD));

        Status func = new Status();
        assertEquals(Chronotype.PIGEON, func.apply(sessions).value);
    }

}