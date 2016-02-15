package ru.unn.agile.VolumesComputer.Infrastructure;

import ru.unn.agile.VolumesComputer.ViewModel.ComputerViewModel;
import ru.unn.agile.VolumesComputer.ViewModel.ComputerViewModelLogTest;

public class ViewModelLoggerTest extends ComputerViewModelLogTest {
    @Override
    public void initialize() {
        setViewModel(new ComputerViewModel(new LoggerFile("./ViewModeLoggerTest.log")));
    }
}
