package ru.yandex.practicum.sleeptracker.analysis;

public class SleepAnalysisResult {
    public final Object value;
    public final String description;

    public SleepAnalysisResult(Object value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String toString() {
        return description + ": " + value;
    }
}
