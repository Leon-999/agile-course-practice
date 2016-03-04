package ru.unn.agile.PomodoroTimer.infrastructure;


import ru.unn.agile.PomodoroTimer.viewmodel.PomodoroTimerViewModelShould;

public class PomodoroTimerViewModelWithLoggerShould extends PomodoroTimerViewModelShould {
    @Override
    public void setUp() {
        LogRecordXmlTag logRecordXmlTag = new LogRecordXmlTag();
        PomodoroTimerXmlLogger pomodoroTimeLogger =
                new PomodoroTimerXmlLogger("./PomodoroViewModelWithLoggerTest.xml",
                        logRecordXmlTag);
        super.prepareViewModel(pomodoroTimeLogger);
    }
}
