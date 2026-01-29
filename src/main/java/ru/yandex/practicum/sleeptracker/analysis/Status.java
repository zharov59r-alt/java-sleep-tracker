package ru.yandex.practicum.sleeptracker.analysis;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.*;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Status implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> o) {

        // фильтр для списка интервалов
        // оставляем только те что пересекают интервал ночи 00:00 - 06:00
        Predicate<SleepingSession> sessionFilter = (SleepingSession session) ->
            session.begin.isBefore(
                    LocalDateTime.of(session.end.toLocalDate(), LocalTime.of(6, 0, 0))
            ) && session.end.isAfter(
                    LocalDateTime.of(session.end.toLocalDate(), LocalTime.of(0, 0, 0)));

        /*
            Маппер статусов
            Сова: begin > 23:00 && end > 9:00
            Жаворонок: begin < 22:00 && end < 7:00
        */
        Function<SleepingSession, String> sessionMapper = (SleepingSession session) -> {
            if (session.begin.isAfter(
                    LocalDateTime.of(
                        session.end.toLocalDate().minusDays(1),
                        LocalTime.of(23, 0, 0))
                ) &&  session.end.isAfter(
                    LocalDateTime.of(session.end.toLocalDate(), LocalTime.of(9, 0, 0)))
                ) {
                return "сова";
            } else if (session.begin.isBefore(
                LocalDateTime.of(
                    session.end.toLocalDate().minusDays(1),
                    LocalTime.of(22, 0, 0))
                ) && session.end.isBefore(
                        LocalDateTime.of(session.end.toLocalDate(), LocalTime.of(7, 0, 0)))
                ) {
                return "жаворонок";
            } else {
                return "голубь";
            }
        };


        List<String> status = o.stream()
            .filter(sessionFilter)
            .map(sessionMapper)
             // Группируем данные в мапу вычисляя количество для каждого статуса
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            // преобразуем в новую мапу
            // группируем в обратном порядке: количество - список статусов
            .entrySet()
            .stream()
            .collect(Collectors.groupingBy(
                    Map.Entry::getValue,
                    Collectors.mapping(Map.Entry::getKey, Collectors.toList())
            ))
            // выбираем из мапы список статусов с наибольшим количеством
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByKey())
            .orElseGet(() -> new AbstractMap.SimpleEntry<>(1L, List.of("голубь")))
            .getValue();

        return new SleepAnalysisResult((status.size() > 1) ? "голубь" : status.getFirst(), "Вы");
    }

}
