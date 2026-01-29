package ru.yandex.practicum.sleeptracker.analysis;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class Max implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> o) {
        return new SleepAnalysisResult(
                o.stream()
                        .map(session -> (int) Duration.between(session.begin, session.end).toMinutes())
                        .max(Long::compare)
                        .get(),
                "Максимальная продолжительность сессии (в минутах)");
    }

}
