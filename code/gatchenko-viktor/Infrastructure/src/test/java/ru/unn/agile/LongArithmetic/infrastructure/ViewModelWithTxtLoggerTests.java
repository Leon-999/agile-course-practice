package ru.unn.agile.LongArithmetic.infrastructure;

import ru.unn.agile.LongArithmetic.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger txtLogger =
                new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        super.initializeViewModel(txtLogger);
    }
}
