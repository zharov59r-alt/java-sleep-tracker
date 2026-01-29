package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SleepingSession {

    public final LocalDateTime begin;
    public final LocalDateTime end;
    public final String status;

    public SleepingSession(LocalDateTime begin, LocalDateTime end, String status) {
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
