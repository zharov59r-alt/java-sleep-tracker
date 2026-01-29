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

        LocalDate startPeriod = firstSessionBegin.toLocalTime().isAfter(LocalTime.of(12, 0, 0)) ?
                firstSessionBegin.toLocalDate().plusDays(1L) : firstSessionBegin.toLocalDate();

        System.out.println(o.getLast().end.toLocalDate().plusDays(1L));

        Period daysBetween = Period.between(
                startPeriod,
                o.getLast().end.toLocalDate().plusDays(1L));

        System.out.println(daysBetween.getDays());

        return new SleepAnalysisResult(
                (int) IntStream.rangeClosed(0, daysBetween.getDays())
                        .filter(num -> {

                            return num > 0;
                        })
                        .peek(System.out::println)
                        .count(),
                "Количество бессонных ночей");
    }

}
