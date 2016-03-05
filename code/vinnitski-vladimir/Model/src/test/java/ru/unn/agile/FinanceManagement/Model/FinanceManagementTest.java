package ru.unn.agile.FinanceManagement.Model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Calendar;

public class FinanceManagementTest {
    private FinanceManagement finance;

    @Before
    public void addingConsumptionForTest() {
        finance = new FinanceManagement();
        String category = "new category";
        String name = "new name";
        Double count = 1.;
        Double price = 100.;
        Calendar time = Calendar.getInstance();
        Consumption consumption = new Consumption(category, name, count, price, time);
        finance.addConsumption(consumption);
    }

    @Test
    public void canGetAllCategory() {
        ArrayList<String> newCategory = new ArrayList<String>();
        newCategory.add("new category");

        assertEquals(newCategory, finance.getAllCategory());
    }

    @Test
    public void canGetAllNameInCategory() {
        ArrayList<String> newName = new ArrayList<String>();
        newName.add("new name");

        assertEquals(newName, finance.getAllNameInCategory(finance.getAllCategory().get(0)));
    }

    @Test
    public void canGetAllNameInNonCategory() {
        String nonCategory = "non category";
        ArrayList<String> noName = new ArrayList<String>();

        assertEquals(noName, finance.getAllNameInCategory(nonCategory));
    }

    @Test
    public void canGetBalance() {
        double deltaFromEqualsDoubleSum = 0.;
        finance = new FinanceManagement();
        double newSum = 100;

        finance.addNewSum(newSum);

        assertEquals(newSum, finance.getBalance(), deltaFromEqualsDoubleSum);
    }

    @Test
    public void canGetAllConsumption() {
        ArrayList<Consumption> consumptions = new ArrayList<Consumption>();
        String category = "new category";
        String name = "new name";
        Double count = 1.;
        Double price = 100.;
        Calendar time = Calendar.getInstance();
        Consumption consumption = new Consumption(category, name, count, price, time);
        consumptions.add(consumption);

        assertEquals(consumptions, finance.getAllConsumptions());
    }

    @Test
    public void canChangeBalance() {
        double newSum = 1000;
        double deltaFromEqualsDoubleSum = 0.;
        double resultSum = 900;

        finance.addNewSum(newSum);

        assertEquals(resultSum, finance.getBalance(), deltaFromEqualsDoubleSum);
    }
}
