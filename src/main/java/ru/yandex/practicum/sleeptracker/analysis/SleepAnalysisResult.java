package ru.yandex.practicum.sleeptracker.analysis;

public class SleepAnalysisResult {
    final int value;
    final String description;

    public SleepAnalysisResult(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String toString() {
        return description + ": " + value;
    }
}
