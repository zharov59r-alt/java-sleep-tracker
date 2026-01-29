package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.analysis.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleepTrackerApp {

    public static void main(String[] args) throws IOException {

        List<Function<List<SleepingSession>, SleepAnalysisResult>> analysisProcessors = new ArrayList<>();
        analysisProcessors.add(new Count());
        analysisProcessors.add(new Min());
        analysisProcessors.add(new Max());
        analysisProcessors.add(new Avg());
        analysisProcessors.add(new CountBAD());
        analysisProcessors.add(new CountSleepless());



        /*
        if (args.length == 0) {
            throw new RuntimeException("Не указан файл сессий сна");
        }

        final List<SleepingSession> sleepingSessions = SleepingSessionLoader.load(args[0]);
*/
        final List<SleepingSession> sleepingSessions = SleepingSessionLoader.load("sleep_log.txt");

        List<SleepAnalysisResult> results = analysisProcessors.stream()
                .map(func -> func.apply(sleepingSessions))
                .peek(System.out::println)
                .collect(Collectors.toList());

    }
}