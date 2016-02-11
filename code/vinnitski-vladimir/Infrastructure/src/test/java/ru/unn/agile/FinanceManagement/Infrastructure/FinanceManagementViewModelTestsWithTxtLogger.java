package ru.unn.agile.FinanceManagement.Infrastructure;


import ru.unn.agile.FinanceManagement.viewModel.FinanceManagerViewModel;
import ru.unn.agile.FinanceManagement.viewModel.FinanceManagerViewModelTest;

public class FinanceManagementViewModelTestsWithTxtLogger
        extends FinanceManagerViewModelTest {
    @Override
    public void setUp() {
        TxtLoggerOfFinanceManagement logger =
                new TxtLoggerOfFinanceManagement("FinanceManagementLogger.log");
        FinanceManagerViewModel viewModel = new FinanceManagerViewModel(logger);
        super.setViewModelAndLogger(viewModel, logger);
    }
}

