package ru.unn.agile.MarksAccounting.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class TestLogger implements ILogger {
    private ArrayList<String> log = new ArrayList<String>();

    public void log(final String s) {
        log.add(s);
    }

    public List<String> getLog(final WindowType windowType) {
        return log;
    }
}
