package ru.unn.agile.FinanceManagement.viewModel;

import ru.unn.agile.FinanceManagement.Model.Consumption;
import java.util.ArrayList;

public interface ILoggerOfFinanceManagement {
    void addConsumption(final Consumption consumption);
    ArrayList<Consumption> getConsumptions();
}
