package ru.yandex.practicum.sleeptracker.analysis;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class Avg implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> o) {
        return new SleepAnalysisResult(
                (int) Math.round(
                    o.stream()
                        .map(session -> (int) Duration.between(session.begin, session.end).toMinutes())
                        .mapToInt(i -> i)
                        .average().orElse(0.0)
                    )
                , "Средняя продолжительность сессии (в минутах)");
    }

}
