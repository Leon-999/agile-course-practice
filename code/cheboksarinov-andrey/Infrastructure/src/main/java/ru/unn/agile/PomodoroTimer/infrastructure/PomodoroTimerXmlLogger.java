package ru.unn.agile.PomodoroTimer.infrastructure;

import ru.unn.agile.PomodoroTimer.viewmodel.ILogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PomodoroTimerXmlLogger implements ILogger {
    private static final String DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";
    private static final String LOG_FILE_HANDLE = "<?xml version=\"1.0\" "
            + "encoding=\"utf-8\"?>\n<logger>\n";
    private final BufferedWriter writer;
    private final String logFileName;
    private LogRecordXmlTag logRecordXmlTag;

    private static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.GERMAN);
        return simpleDateFormat.format(calendar.getTime());
    }

    public PomodoroTimerXmlLogger(final String logFileName,
                                  final LogRecordXmlTag logRecordXmlTag) {
        this.logFileName = logFileName;
        this.logRecordXmlTag = logRecordXmlTag;
        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(logFileName));
            logWriter.write(LOG_FILE_HANDLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer = logWriter;
    }
    @Override
    public void addRecord(final String messageString) {
        try {
            logRecordXmlTag.setTimeAttributeValue(getCurrentTime());
            logRecordXmlTag.setMessageAttributeValue(messageString);
            writer.write(logRecordXmlTag.toString());
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public List<String> getLog() {
        BufferedReader logReader;
        ArrayList<String> logMessage = new ArrayList<>();
        Pattern logMessagePattern = Pattern.compile(constructXmlRecordPatternString());
        try {
            logReader = new BufferedReader(new FileReader(logFileName));
            String logLine = logReader.readLine();

            while (logLine != null) {
                Matcher logMatcher = logMessagePattern.matcher(logLine);
                if (logMatcher.matches()) {
                    logMessage.add(logMatcher.group("date") + " " + logMatcher.group("message"));
                }
                logLine = logReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return logMessage;
    }

    private String constructXmlRecordPatternString() {
        return "^  <" + this.logRecordXmlTag.getName() + " "
                + this.logRecordXmlTag.getTimeAttributeName() + "=\"(?<date>.*)" + "\" "
                + this.logRecordXmlTag.getMessageAttributeName() + "=\"(?<message>.*)\" />$";
    }

    public void setLogRecordXmlTag(final LogRecordXmlTag logRecordXmlTag) {
        this.logRecordXmlTag = logRecordXmlTag;
    }

    public LogRecordXmlTag getLogRecordXmlTag() {
        return logRecordXmlTag;
    }
}
