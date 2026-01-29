package ru.yandex.practicum.sleeptracker.analysis;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class CountSleepless implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> o) {

        LocalDateTime firstSessionBegin = o.getFirst().begin;
        LocalDateTime lastSessionEnd = o.getLast().end;

        LocalDate startPeriod = firstSessionBegin.toLocalTime().isAfter(LocalTime.of(12, 0, 0)) ?
                firstSessionBegin.toLocalDate().plusDays(1L) : firstSessionBegin.toLocalDate();

        Period daysBetween = Period.between(
                startPeriod,
                startPeriod.isAfter(lastSessionEnd.toLocalDate()) ? startPeriod : lastSessionEnd.toLocalDate()
                );

        return new SleepAnalysisResult(
            IntStream.rangeClosed(0, daysBetween.getDays())
                .filter(num ->
                    o.stream()
                        .noneMatch(
                            session ->
                                session.begin.isBefore(startPeriod.plusDays(num).atTime(7, 0)) &&
                                session.end.isAfter(startPeriod.plusDays(num).atTime(0, 0))
                        )
                )
                .count(),
            "Количество бессонных ночей");
    }

}
