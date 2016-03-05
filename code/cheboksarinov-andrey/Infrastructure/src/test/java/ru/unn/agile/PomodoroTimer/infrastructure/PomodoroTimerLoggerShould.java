package ru.unn.agile.PomodoroTimer.infrastructure;


import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PomodoroTimerLoggerShould {
    private static final String FILE_LOG_NAME = "./PomodoroTimerLog.xml";
    private PomodoroTimerXmlLogger pomodoroTimerLogger;
    private LogRecordXmlTag logRecordXmlTag;
    @Before
    public void setUp() {
        logRecordXmlTag = new LogRecordXmlTag();
        pomodoroTimerLogger = new PomodoroTimerXmlLogger(FILE_LOG_NAME, logRecordXmlTag);
    }
    @Test
    public void beCreatedCorrectly() {
        assertNotNull(pomodoroTimerLogger);
    }
    @Test
    public void createLogFile() {
        try {
            new BufferedReader(new FileReader(FILE_LOG_NAME));
        } catch (FileNotFoundException e) {
            fail("File " + FILE_LOG_NAME + " not found");
        }
    }
    @Test
    public void addSingleLineToLog() {
        String logMessageForTest = "Log message 1";

        pomodoroTimerLogger.addRecord(logMessageForTest);
        String firstLogMessage = pomodoroTimerLogger.getLog().get(0);

        assertTrue(firstLogMessage.contains(logMessageForTest));
    }
    @Test
    public void addLinesToLog() {
        String[] messages = {"Log message 1", "Log message 2"};

        pomodoroTimerLogger.addRecord(messages[0]);
        pomodoroTimerLogger.addRecord(messages[1]);

        List<String> fullLogMessage = pomodoroTimerLogger.getLog();

        assertTrue(allMessagesInLog(messages, fullLogMessage));

    }
    @Test
    public void writeDateAndTimeInLogMessage() {
        String testMessage = "Log message 1";

        pomodoroTimerLogger.addRecord(testMessage);

        String logLine = pomodoroTimerLogger.getLog().get(0);

        assertTrue(logLine.matches("^\\[\\d{2}.\\d{2}.\\d{4} \\d{2}:\\d{2}:\\d{2}\\] .*"));
    }

    private boolean allMessagesInLog(final String[] messages, final List<String> fullLogMessage) {
        return fullLogMessage.get(0).contains(messages[0])
                && fullLogMessage.get(1).contains(messages[1]);
    }
}
