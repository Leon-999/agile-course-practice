package ru.unn.agile.VolumesComputer.ViewModel;

import java.util.List;

public interface ILogger {
    void log(final String message);
    List<String> getLines();
    String getDateFormat();
}
