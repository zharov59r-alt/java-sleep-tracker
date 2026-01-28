package ru.yandex.practicum.sleeptracker.analysis;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class CountBAD implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> o) {
        return new SleepAnalysisResult(
                (int) o.stream()
                        .filter(session -> session.status.equals("BAD"))
                        .count()
                , "Количество сессий с плохим качество сна");
    }

}
