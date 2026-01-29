package ru.yandex.practicum.sleeptracker.analysis;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.*;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Status implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> o) {

        Map<String, Long> counts = o.stream()
            .filter(session ->
                session.begin.isBefore(
                    LocalDateTime.of(
                        session.end.toLocalDate(),
                        LocalTime.of(6, 0, 0)
                        )
                    ) &&
                session.end.isAfter(
                    LocalDateTime.of(
                        session.end.toLocalDate(),
                        LocalTime.of(0, 0, 0)
                    )
                )
            )
            .peek(System.out::println)
            .map(session -> {
                    if (session.begin.isAfter(
                            LocalDateTime.of(
                                session.end.toLocalDate().minusDays(1),
                                LocalTime.of(23, 0, 0)
                                )
                            ) &&
                        session.end.isAfter(
                            LocalDateTime.of(
                                session.end.toLocalDate(),
                                LocalTime.of(9, 0, 0)
                            )
                        )) {
                        return "сова";
                    } else if (session.begin.isBefore(
                                LocalDateTime.of(
                                    session.end.toLocalDate().minusDays(1),
                                    LocalTime.of(22, 0, 0)
                                    )
                                ) &&
                            session.end.isBefore(
                                    LocalDateTime.of(
                                        session.end.toLocalDate(),
                                        LocalTime.of(7, 0, 0)
                                    )
                                )) {
                        return "жаворонок";
                    } else {
                        return "голубь";
                    }
                }
            )
            .peek(System.out::println)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())
            );

        System.out.println(counts);

        return new SleepAnalysisResult("qwe", "qwe");
    }

}
