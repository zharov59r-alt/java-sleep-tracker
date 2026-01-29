package ru.yandex.practicum.sleeptracker.enums;

public enum Chronotype {
    OWL("сова"), LARK("жаворонок"), PIGEON("голубь");

    private String name;

    Chronotype(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
