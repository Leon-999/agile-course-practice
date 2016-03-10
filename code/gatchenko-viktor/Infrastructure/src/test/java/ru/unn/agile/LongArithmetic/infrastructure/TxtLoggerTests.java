package ru.unn.agile.LongArithmetic.infrastructure;


import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TxtLoggerTests {
    private static final String PATH = "./TxtLoggerTests.log";
    private TxtLogger txtLogger;

    @Before
    public void setUp() {
        txtLogger = new TxtLogger(PATH);
    }

    @Test
    public void canCreateLoggerWithFileName() {
        assertNotNull(txtLogger);
    }

    @Test
    public void canCreateLogFileOnDisk() {
        try {
            new BufferedReader(new FileReader(PATH));
        } catch (FileNotFoundException e) {
            fail("File " + PATH + " not exist");
        }
    }

    @Test
    public void canWriteLogMessage() {
        String message = "test";
        txtLogger.write(message);
        String logMessage = txtLogger.read().get(0);
        String regex = ".*" + message + "$";

        assertTrue(logMessage.matches(regex));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] messages = {"test 1", "tess 2"};

        txtLogger.write(messages[0]);
        txtLogger.write(messages[1]);

        Vector<String> logMessages = txtLogger.read();
        for (int i = 0; i < logMessages.size(); i++) {
            String logMessage = logMessages.get(i);
            String regex = ".*" + messages[i] + "$";

            assertTrue(logMessage.matches(regex));
        }
    }

    @Test
    public void doesLogContainDateAndTime() {
        String message = "test";
        txtLogger.write(message);
        String logMessage = txtLogger.read().get(0);
        String regex = "^\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2} => .*";

        assertTrue(logMessage.matches(regex));
    }
}
