package ru.unn.agile.FinanceManagement.Model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Calendar;

public class FinanceManagementTest {

    private FinanceManagement finance;

    private ArrayList<Consumption> addingConsumptionForTest() {
        ArrayList<Consumption> consumptions = new ArrayList<Consumption>();
        Consumption consumption = new Consumption();
        finance = new FinanceManagement();
        String category = "new category";
        String name = "new name";
        Double count = 1.;
        Double price = 100.;
        Calendar time = Calendar.getInstance();

        consumption.addConsumption(category, name, count, price, time);

        finance.addConsumption(consumption);

        consumptions.add(consumption);

        return consumptions;
    }

    @Test
    public void canGetAllCategory() {
        addingConsumptionForTest();
        ArrayList<String> newCategory = new ArrayList<String>();
        newCategory.add("new category");
        assertEquals(newCategory, finance.getAllCategory());
    }

    @Test
    public void canGetAllNameInCategory() {
        addingConsumptionForTest();
        ArrayList<String> newName = new ArrayList<String>();
        newName.add("new name");
        assertEquals(newName, finance.getAllNameInCategory(finance.getAllCategory().get(0)));
    }

    @Test
    public void canGetAllNameInNonCategory() {
        addingConsumptionForTest();
        String nonCategory = "non category";
        ArrayList<String> noName = new ArrayList<String>();
        assertEquals(noName, finance.getAllNameInCategory(nonCategory));
    }
    @Test
    public void canGetBalance() {
        finance = new FinanceManagement();
        double newSum = 100;
        finance.addNewSum(newSum);
        double deltaFromEqualsDoubleSum = 0.;
        assertEquals(newSum, finance.getBalance(), deltaFromEqualsDoubleSum);
    }

    @Test
    public void canGetAllConsumption() {

        ArrayList<Consumption> consumptions = addingConsumptionForTest();
        assertEquals(consumptions, finance.getAllConsumptions());
    }

    @Test
    public void canChangeBalance() {
        addingConsumptionForTest();
        double newSum = 1000;
        finance.addNewSum(newSum);
        double deltaFromEqualsDoubleSum = 0.;
        double resultSum = 900;
        assertEquals(resultSum, finance.getBalance(), deltaFromEqualsDoubleSum);
    }
}
