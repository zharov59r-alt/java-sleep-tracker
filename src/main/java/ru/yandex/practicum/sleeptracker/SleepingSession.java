package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SleepingSession {

    public final LocalDateTime begin;
    public final LocalDateTime end;
    public final SleepQuality status;

    public SleepingSession(LocalDateTime begin, LocalDateTime end, SleepQuality status) {
        this.begin = begin;
        this.end = end;
        this.status = status;
    }

    @Override
    public String toString() {
        return "SleepingSession{" +
                "begin=" + begin.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +
                ", end=" + end.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +
                ", status='" + status + '\'' +
                '}';
    }
}
