package ru.unn.agile.PomodoroTimer.viewmodel;

import java.util.List;

public interface ILogger {
    void addRecord(final String messageString);

    List<String> getLog();
}
