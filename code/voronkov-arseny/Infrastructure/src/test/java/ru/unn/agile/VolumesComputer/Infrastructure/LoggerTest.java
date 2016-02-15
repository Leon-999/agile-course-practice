package ru.unn.agile.VolumesComputer.Infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LoggerTest {
    private static final String FILE_NAME = "./LoggerTest.log";
    private LoggerFile logger;

    @Before
    public void initialize() {
        logger = new LoggerFile(FILE_NAME);
    }

    @Test
    public void createFile() {
        assertTrue(new File(FILE_NAME).isFile());
    }

    @Test
    public void writeOneMessage() {
        final String msg = "xexexe";
        logger.log(msg);
        assertThat(logger.getLines().get(0), containsString(msg));
    }

    @Test
    public void writeThoMessages() {
        final String msg1 = "xexexe";
        final String msg2 = "xaxaxa";
        logger.log(msg1);
        logger.log(msg2);
        List<String> lines = logger.getLines();
        assertThat(lines.get(0), containsString(msg1));
        assertThat(lines.get(1), containsString(msg2));
    }

    @Test
    public void writeDate() {
        logger.log("xexexe");
        assertTrue(Pattern.matches(
                logger.getDateFormat() + "*",
                logger.getLines().get(0)));
    }
}
