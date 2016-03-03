package ru.unn.agile.PomodoroTimer.infrastructure;


import ru.unn.agile.PomodoroTimer.viewmodel.PomodoroTimerViewModelShould;

public class PomodoroTimerViewModelWithLoggerShould extends PomodoroTimerViewModelShould {
    @Override
    public void setUp() {
        PomodoroTimerXmlLogger pomodoroTimeLogger =
                new PomodoroTimerXmlLogger("./PomodoroViewModelWithLoggerTests.xml");
        super.prepareViewModel(pomodoroTimeLogger);
    }
}
