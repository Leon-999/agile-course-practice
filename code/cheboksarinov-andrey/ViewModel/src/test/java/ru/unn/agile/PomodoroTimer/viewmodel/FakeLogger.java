package ru.unn.agile.PomodoroTimer.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLogger implements ILogger {
    private final ArrayList<String> log = new ArrayList<>();

    @Override
    public void addRecord(final String messageString) {
        log.add(messageString);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
