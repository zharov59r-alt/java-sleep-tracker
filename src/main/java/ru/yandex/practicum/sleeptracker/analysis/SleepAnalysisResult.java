package ru.yandex.practicum.sleeptracker.analysis;

public class SleepAnalysisResult {
    final Object value;
    final String description;

    public SleepAnalysisResult(Object value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String toString() {
        return description + ": " + value;
    }
}
