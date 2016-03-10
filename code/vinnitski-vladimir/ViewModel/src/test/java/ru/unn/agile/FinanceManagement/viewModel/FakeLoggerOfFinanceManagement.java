package ru.unn.agile.FinanceManagement.viewModel;

import ru.unn.agile.FinanceManagement.Model.Consumption;
import java.util.ArrayList;


public class FakeLoggerOfFinanceManagement implements ILoggerOfFinanceManagement {
    private ArrayList<Consumption> logConsumptions = new ArrayList<>();

    @Override
    public void addConsumption(final Consumption message) {
        logConsumptions.add(message);
    }

    @Override
    public ArrayList<Consumption> getConsumptions() {
        return logConsumptions;
    }
}
