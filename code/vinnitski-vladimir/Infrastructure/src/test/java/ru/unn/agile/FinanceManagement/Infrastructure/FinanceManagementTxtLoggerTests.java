package ru.unn.agile.FinanceManagement.Infrastructure;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.FinanceManagement.Model.Consumption;
import java.util.Calendar;

import static org.junit.Assert.*;

public class FinanceManagementTxtLoggerTests {
    private static final String LOG_PATH = "FinanceManagementLogger.log";
    private TxtLoggerOfFinanceManagement logger;

    @Before
    public void setUp() {
        logger = new TxtLoggerOfFinanceManagement(LOG_PATH);
    }

    @Test
    public void canInstantiateTxtLogger() {
        assertNotNull(logger);
    }


    @Test
    public void canWriteMessageIntoLog() {
        Consumption consumption = new Consumption();
        String category = "new category";
        String name = "new name";
        Double count = 1.;
        Double price = 100.;
        Calendar time = Calendar.getInstance();

        consumption.addConsumption(category, name, count, price, time);
        logger.addConsumption(consumption);
        Consumption membersConsumption = logger.getConsumptions().get(0);
        assertTrue(membersConsumption.equals(consumption));
    }
}
