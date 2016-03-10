package ru.unn.agile.FinanceManagement.Infrastructure;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.FinanceManagement.Model.Consumption;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Random;
import static org.junit.Assert.*;

public class FinanceManagementTxtLoggerTests {
    private String logPath;
    private TxtLoggerOfFinanceManagement logger;

    @Before
    public void setUp() {
        final int seedRand = 1000;
        final Random rand = new Random(seedRand);
        logPath = "FinanceManagementLogger" + rand.nextInt() + ".log";
        logger = new TxtLoggerOfFinanceManagement(logPath);
    }

    @Test
    public void canInitializedTxtLogger() {
        assertNotNull(logger);
    }

    @Test
    public void isLogFileCreatedAfterLoggerHadBeenInstantiated() {
        try {
            new BufferedReader(new FileReader(logPath));
        } catch (FileNotFoundException e) {
            fail("File " + logPath + " is not created");
        }
    }

    @Test
    public void canWriteMessageIntoLog() {
        String category = "new category";
        String name = "new name";
        Double count = 1.;
        Double price = 100.;
        Calendar time = Calendar.getInstance();
        Consumption consumption = new Consumption(category, name, count, price, time);
        logger.addConsumption(consumption);

        Consumption membersConsumption = logger.getConsumptions().get(0);

        assertTrue(membersConsumption.equals(consumption));
    }
}

