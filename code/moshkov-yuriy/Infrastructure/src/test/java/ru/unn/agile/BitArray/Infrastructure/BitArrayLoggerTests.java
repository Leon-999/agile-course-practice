package ru.unn.agile.BitArray.Infrastructure;

import org.junit.Before;
import org.junit.Test;

import ru.unn.agile.BitArray.Infrastructure.BitArrayLogger;

import java.io.File;

import static org.junit.Assert.*;


public class BitArrayLoggerTests {
    private static final String FILENAME = "./BitArray_Tests.log";
    private BitArrayLogger logger;

    @Before
    public void initializeBitArrayLogger() {
        logger = new BitArrayLogger(FILENAME);
    }

    @Test
    public void canCreateBitArrayLoggerWithFileName() {
        assertNotNull(logger);
    }

    @Test
    public void didLoggerCreateTxtFileOnDisk() {
        assertTrue(new File(FILENAME).isFile());
    }

    @Test
    public void canLoggerAddMessageToLog() {
        String message = "TEST";
        logger.log(message);
        assertFalse(logger.getLog().isEmpty());
    }
}
