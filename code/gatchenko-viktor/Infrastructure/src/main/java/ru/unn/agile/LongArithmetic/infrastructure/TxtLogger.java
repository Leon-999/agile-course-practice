package ru.unn.agile.LongArithmetic.infrastructure;

import ru.unn.agile.LongArithmetic.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;

public class TxtLogger implements ILogger {

    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

    private final BufferedWriter writer;
    private final String filePath;

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);

        return dateFormat.format(cal.getTime());
    }

    public TxtLogger(final String filePath) {
        this.filePath = filePath;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer = logWriter;
    }

    @Override
    public void write(final String message) {
        try {
            writer.write(now() + " => " + message);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Vector<String> read() {
        BufferedReader reader;
        Vector<String> logMessages = new Vector<String>();
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();

            while (line != null) {
                logMessages.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return logMessages;
    }
}
