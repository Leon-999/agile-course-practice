package ru.unn.agile.PomodoroTimer.viewmodel;

import java.util.List;

public interface ILogger {
    void addLogLine(final String s);

    List<String> getLog();
}
