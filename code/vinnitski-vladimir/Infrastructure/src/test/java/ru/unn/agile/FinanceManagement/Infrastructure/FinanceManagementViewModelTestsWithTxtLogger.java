package ru.unn.agile.FinanceManagement.Infrastructure;

import ru.unn.agile.FinanceManagement.viewModel.FinanceManagerViewModel;
import ru.unn.agile.FinanceManagement.viewModel.FinanceManagerViewModelTest;
import java.util.Random;

public class FinanceManagementViewModelTestsWithTxtLogger
        extends FinanceManagerViewModelTest {
    @Override
    public void setUp() {
        final int seedRand = 1000;
        final Random rand = new Random(seedRand);
        final String logPath = "FinanceManagementLogger" + rand.nextInt() + ".log";
        TxtLoggerOfFinanceManagement logger =
                new TxtLoggerOfFinanceManagement(logPath);
        FinanceManagerViewModel viewModel = new FinanceManagerViewModel(logger);
        super.setViewModelAndLogger(viewModel, logger);
    }
}

