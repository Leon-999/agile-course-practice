package ru.unn.agile.VolumesComputer.ViewModel;

import java.util.LinkedList;
import java.util.List;

public class LoggerFake implements ILogger {
    private List<String> lines;

    public LoggerFake() {
        lines = new LinkedList<String>();
    }

    @Override
    public void log(final String message) {
        lines.add(message);
    }

    @Override
    public List<String> getLines() {
        return lines;
    }

    @Override
    public String getDateFormat() {
        return "";
    }
}
