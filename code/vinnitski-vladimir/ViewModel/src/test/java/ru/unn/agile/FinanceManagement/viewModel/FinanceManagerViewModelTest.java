package ru.unn.agile.FinanceManagement.viewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;

public class FinanceManagerViewModelTest {
    private FinanceManagerViewModel viewModel;
    private ILoggerOfFinanceManagement logger;

    private String[][] addedConsumptionsFromTests() {
        String[][] consumptions = new String[1][7];
        consumptions[0][0] = "Hobby";
        consumptions[0][1] = "Copter";
        consumptions[0][2] = "1.0";
        consumptions[0][3] = "10000.0";
        consumptions[0][4] = "2012";
        consumptions[0][5] = "11";
        consumptions[0][6] = "12";
        viewModel.setCategory(consumptions[0][0]);
        viewModel.setName(consumptions[0][1]);
        viewModel.setCount(consumptions[0][2]);
        viewModel.setPrice(consumptions[0][3]);
        viewModel.setYear(consumptions[0][4]);
        viewModel.setMonth(consumptions[0][5]);
        viewModel.setDay(consumptions[0][6]);
        return consumptions;
    }

    public void setViewModelAndLogger(final FinanceManagerViewModel viewModel,
                                      final ILoggerOfFinanceManagement logger) {
        this.viewModel = viewModel;
        this.logger = logger;
    }

    @Before
    public void setUp() {
        logger = new FakeLoggerOfFinanceManagement();
        viewModel = new FinanceManagerViewModel(logger);
    }

    @After
    public void tearDown() {
        viewModel = null;
        logger = null;
    }

    @Test
    public void whenDefaultAddConsumptionButtonIsDisabled() {
        assertFalse(viewModel.isEnabledAddConsumptionButton());
    }

    @Test
    public void canGetConsumptions() {
        String[][] consumptions = addedConsumptionsFromTests();

        viewModel.addConsumption();

        assertTrue(Arrays.deepEquals(consumptions, viewModel.getArrayConsumptions()));
    }

    @Test
    public void canGetEmptyArrayCategory() {
        String[] emptyArrayCategory = new String[0];

        assertTrue(Arrays.deepEquals(emptyArrayCategory, viewModel.getArrayCategory()));
    }

    @Test
    public void canGetNotEmptyArrayCategory() {
        String[] category = new String[1];
        category[0] = "Hobby";
        addedConsumptionsFromTests();

        viewModel.addConsumption();

        assertTrue(Arrays.deepEquals(category, viewModel.getArrayCategory()));
    }

    @Test
    public void canGetEmptyArrayNameWhenEmptyCategory() {
        String[] emptyArrayName = new String[0];

        viewModel.setCategory("");

        assertTrue(Arrays.deepEquals(emptyArrayName, viewModel.getArrayName()));
    }

    @Test
    public void canGetEmptyArrayNameWhenNewCategory() {
        String[] emptyArrayName = new String[0];

        viewModel.setCategory("Hobby");

        assertTrue(Arrays.deepEquals(emptyArrayName, viewModel.getArrayName()));
    }

    @Test
    public void canGetArrayNameWhenKnownCategory() {
        addedConsumptionsFromTests();
        viewModel.addConsumption();
        ArrayList<String> arrayName = new ArrayList<String>();
        arrayName.add("Copter");

        viewModel.setCategory("Hobby");

        assertTrue(Arrays.deepEquals(arrayName.toArray(), viewModel.getArrayName()));
    }

    @Test
    public void wenNotNumberInCountShowCountErrorMessage() {
        String errorMessage = "Please enter only numbers in the 'Count'";

        viewModel.setCount("a");

        assertEquals(errorMessage, viewModel.getCountErrorMessage());
    }

    @Test
    public void wenFixNotNumberInCountNotShowCountErrorMessage() {
        String errorMessage = "";

        viewModel.setCount("a");
        viewModel.setCount("1");

        assertEquals(errorMessage, viewModel.getCountErrorMessage());
    }

    @Test
    public void wenClearNotNumberInCountNotShoCountErrorMessage() {
        String errorMessage = "";

        viewModel.setCount("a");
        viewModel.setCount("");

        assertEquals(errorMessage, viewModel.getCountErrorMessage());
    }

    @Test
    public void canAddConsumptionWenCountEmpty() {
        String[][] consumptions = addedConsumptionsFromTests();
        viewModel.setCount("");
        consumptions[0][2] = "0.0";

        viewModel.addConsumption();

        assertTrue(Arrays.deepEquals(consumptions, viewModel.getArrayConsumptions()));
    }

    @Test
    public void wenNotNumberInPriceShowPriceErrorMessage() {
        String errorMessage = "Please enter only numbers in the 'Price'";

        viewModel.setPrice("a");

        assertEquals(errorMessage, viewModel.getPriceErrorMessage());
    }

    @Test
    public void wenFixNotNumberInPriceNotShowPriceErrorMessage() {
        String errorMessage = "";

        viewModel.setPrice("a");
        viewModel.setPrice("1");

        assertEquals(errorMessage, viewModel.getPriceErrorMessage());
    }

    @Test
    public void wenClearNotNumberInPriceNotShowPriceErrorMessage() {
        String errorMessage = "";

        viewModel.setPrice("a");
        viewModel.setPrice("");

        assertEquals(errorMessage, viewModel.getPriceErrorMessage());
    }

    @Test
    public void canAddConsumptionWenPriceEmpty() {
        String[][] consumptions = addedConsumptionsFromTests();
        viewModel.setPrice("");
        consumptions[0][3] = "0.0";

        viewModel.addConsumption();

        assertTrue(Arrays.deepEquals(consumptions, viewModel.getArrayConsumptions()));
    }

    @Test
    public void wenShowsCountErrorMessageAddConsumptionButtonIsDisabled() {
        viewModel.setCount("a");

        assertFalse(viewModel.isEnabledAddConsumptionButton());
    }

    @Test
    public void wenShowsPriceErrorMessageAddConsumptionButtonIsDisabled() {
        viewModel.setPrice("a");

        assertFalse(viewModel.isEnabledAddConsumptionButton());
    }

    @Test
    public void wenFixCountErrorMessageAddConsumptionButtonIsEnabled() {
        addedConsumptionsFromTests();
        viewModel.setCount("a");
        viewModel.setCount("");

        assertTrue(viewModel.isEnabledAddConsumptionButton());
    }

    @Test
    public void wenFixPriceErrorMessageAddConsumptionButtonIsEnabled() {
        addedConsumptionsFromTests();
        viewModel.setPrice("a");
        viewModel.setPrice("");

        assertTrue(viewModel.isEnabledAddConsumptionButton());
    }

    @Test
    public void wenShowsPriceErrorMessageButFixCountErrorMessageAddConsumptionButtonIsDisabled() {
        viewModel.setPrice("a");
        viewModel.setCount("a");
        viewModel.setCount("");

        assertFalse(viewModel.isEnabledAddConsumptionButton());
    }

    @Test
    public void wenShowsCountErrorMessageButFixPriceErrorMessageAddConsumptionButtonIsDisabled() {
        viewModel.setCount("a");
        viewModel.setPrice("a");
        viewModel.setPrice("");

        assertFalse(viewModel.isEnabledAddConsumptionButton());
    }

    @Test
    public void wenCategoryEmptyNameTextboxIsDisabled() {
        viewModel.setCategory("");

        assertFalse(viewModel.isEnabledNameTextbox());
    }

    @Test
    public void wenCategoryNotEmptyNameTextboxIsEnabled() {
        viewModel.setCategory("Hobby");

        assertTrue(viewModel.isEnabledNameTextbox());
    }

    @Test
    public void wenNameTextboxIsEmptyCountAndPriceTextboxIsDisabled() {
        viewModel.setCategory("Hobby");
        viewModel.setName("");

        assertFalse(viewModel.isEnabledCountAndPriceTextbox());
    }

    @Test
    public void wenNameTextboxIsNotEmptyCountAndPriceTextboxIsEnabled() {
        viewModel.setCategory("Hobby");
        viewModel.setName("Copter");

        assertTrue(viewModel.isEnabledCountAndPriceTextbox());
    }

    @Test
    public void wenCountAndPriceTextboxIsDisabledAddConsumptionButtonIsDisabled() {
        viewModel.setCategory("Hobby");
        viewModel.setName("");

        assertFalse(viewModel.isEnabledAddConsumptionButton());
    }

    @Test
    public void canGetYears() {
        Integer[] years = {2013, 2014, 2015, 2016, 2017};
        assertTrue(Arrays.deepEquals(years, viewModel.getYears()));
    }

    @Test
    public void canGetMonths() {
        int countMonth = 12;
        Integer[] months = new Integer[countMonth];
        for (int i = 0; i < countMonth; i++) {
            months[i] = i + 1;
        }

        assertTrue(Arrays.deepEquals(months, viewModel.getMonths()));
    }

    @Test
    public void canGetDays() {
        viewModel.setYear("2103");
        viewModel.setMonth("11");
        int countDay = 30;
        Integer[] days = new Integer[countDay];
        for (int i = 0; i < countDay; i++) {
            days[i] = i + 1;
        }
        assertTrue(Arrays.deepEquals(days, viewModel.getDays()));
    }

    @Test
    public void canGetSelectedDay() {
        Integer selectedDay = 1;

        viewModel.setSelectedDay(selectedDay);

        assertEquals(selectedDay, viewModel.getSelectedDay());
    }

    @Test
    public void canGetSelectedMonth() {
        Integer selectedMonth = 1;

        viewModel.setSelectedMonth(selectedMonth);

        assertEquals(selectedMonth, viewModel.getSelectedMonth());
    }

    @Test
    public void canGetSelectedYear() {
        Integer selectedYear = 1;

        viewModel.setSelectedYear(selectedYear);

        assertEquals(selectedYear, viewModel.getSelectedYear());
    }

    @Test
    public void canGetBalance() {
        String addedBalance = "100.0";

        viewModel.addedBalance(addedBalance);

        assertEquals(addedBalance, viewModel.getBalance().toString());
    }

    @Test
    public void canCreateFinanceManagerViewModelWithLogger() {
        FakeLoggerOfFinanceManagement logger = new FakeLoggerOfFinanceManagement();

        FinanceManagerViewModel viewModelLogged = new FinanceManagerViewModel(logger);

        assertNotNull(viewModelLogged);
    }

    @Test
    public void financeManagerViewModelConstructorThrowsExceptionWithNullLogger() {
        try {
            new FinanceManagerViewModel(null);
            fail("Exception wasn't thrown");
        } catch (IllegalArgumentException ex) {
            assertEquals("Logger parameter can't be null", ex.getMessage());
        } catch (Exception ex) {
            fail("Invalid exception type");
        }
    }

    @Test
    public void canGetEmptyArrayConsumptionsInLogger() {
        addedConsumptionsFromTests();
        String[][] consumptions = new String[0][0];
        viewModel = null;

        viewModel = new FinanceManagerViewModel(logger);

        assertTrue(Arrays.deepEquals(consumptions, viewModel.getArrayConsumptions()));
    }

    @Test
    public void canGetNotEmptyArrayConsumptionsInLogger() {
        String[][] consumptions = addedConsumptionsFromTests();
        viewModel.addConsumption();
        viewModel = null;

        viewModel = new FinanceManagerViewModel(logger);

        assertTrue(Arrays.deepEquals(consumptions, viewModel.getArrayConsumptions()));
    }
}
