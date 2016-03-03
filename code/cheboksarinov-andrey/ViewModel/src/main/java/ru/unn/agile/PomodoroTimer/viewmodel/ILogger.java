package ru.unn.agile.PomodoroTimer.viewmodel;

import java.util.List;

public interface ILogger {
    void addRecord(final String stringForAdd);

    List<String> getLog();
}
