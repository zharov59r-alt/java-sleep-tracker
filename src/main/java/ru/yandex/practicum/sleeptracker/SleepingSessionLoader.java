package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.enums.SleepQuality;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SleepingSessionLoader {

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public static List<SleepingSession> load(String fileName) throws IOException {

        List<SleepingSession> sleepingSessions;

        Path path = Paths.get(fileName);

        if (!Files.exists(path)) {
            throw new RuntimeException("Файл сессий сна отсутствует");
        }

        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            sleepingSessions = stream
                                    .map(SleepingSessionLoader::parseLine)
                                    .collect(Collectors.toList());;
        }

        if (sleepingSessions.isEmpty()) {
            throw new RuntimeException("Список сессий сна пуст");
        }

        return sleepingSessions;
    }

    static SleepingSession parseLine(String str) {

        String[] args = str.split(";");

        return new SleepingSession(
                LocalDateTime.parse(args[0], FORMATTER),
                LocalDateTime.parse(args[1], FORMATTER),
                SleepQuality.valueOf(args[2])
        );

    }

}
