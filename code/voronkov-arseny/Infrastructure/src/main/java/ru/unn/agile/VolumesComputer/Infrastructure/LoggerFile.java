package ru.unn.agile.VolumesComputer.Infrastructure;

import ru.unn.agile.VolumesComputer.ViewModel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class LoggerFile implements ILogger {
    private BufferedWriter out;
    private final String fileName;
    private final Calendar calendar;
    private final SimpleDateFormat dateFormat;
    public LoggerFile(final String fileName) {
        this.fileName = fileName;
        out = null;
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss", Locale.ENGLISH);
        try {
            out = new BufferedWriter(new FileWriter(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(final String message) {
        try {
            out.write(dateFormat.format(calendar.getTime()) + " > " + message);
            out.newLine();
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getLines() {
        LinkedList<String> lines = new LinkedList<String>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Override
    public String getDateFormat() {
        return "^\\d{2}.\\d{2}.\\d{2} \\d{2}:\\d{2}:\\d{2}.";
    }
}
