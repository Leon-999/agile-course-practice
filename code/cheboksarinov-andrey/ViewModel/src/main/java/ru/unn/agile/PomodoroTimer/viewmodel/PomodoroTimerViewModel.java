package ru.unn.agile.PomodoroTimer.viewmodel;

import ru.unn.agile.pomodoro.EventGenerator;
import ru.unn.agile.pomodoro.SessionManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroTimerViewModel extends EventGenerator {
    private String seconds;
    private String minutes;
    private String currentStatus;
    private Boolean canStartTimer;
    private String pomodoroCount;
    private String logsString;
    private final SessionManager sessionManager;
    private final ActionEvent viewModelChangedActionEvent;
    private ILogger logger;

    public PomodoroTimerViewModel(final SessionManager sessionManager, final ILogger logger) {
        seconds = "00";
        minutes = "00";
        currentStatus = Status.WAITING.toString();
        pomodoroCount = "0";
        canStartTimer = true;
        viewModelChangedActionEvent = new ActionEvent(this, 0, "View model is changed");
        this.sessionManager = sessionManager;
        this.logger = logger;
        this.sessionManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (isCurrentStatusChanged(sessionManager.getStatus())) {
                    if (currentStatus.equals(Status.WAITING.toString())) {
                        logStartPomodoroSessionEvent();
                    }
                    logChangeStatusEvent(sessionManager.getStatus());
                }
                currentStatus = sessionManager.getStatus();
                pomodoroCount = String.valueOf(sessionManager.getPomodoroCount());
                seconds = String.valueOf(sessionManager.getPomodoroTime().getSecondCount());
                minutes = String.valueOf(sessionManager.getPomodoroTime().getMinuteCount());
                canStartTimer = true;
                if (canStartTimer != isCurrentStatusWaiting()) {
                    canStartTimer = false;
                }
                fireActionPerformed(viewModelChangedActionEvent);
            }
        });
    }

    public Boolean getCanStartTimer() {
        return canStartTimer;
    }
    public String getPomodoroCount() {
        return pomodoroCount;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public String getSeconds() {
        return formatTimeString(seconds);
    }

    public String getMinutes() {
        return formatTimeString(minutes);
    }

    public String getLogs() {
        constructLogsStringForView();
        return logsString;
    }

    private void constructLogsStringForView() {
        StringBuilder newLogsString = new StringBuilder();
        for (String logRecord:logger.getLog()) {
            newLogsString.append(logRecord + '\n');
        }
        logsString = newLogsString.toString();
    }

    private boolean isStringContainOneCharNumber(final String stringWithNumber) {
        return stringWithNumber.length() < 2;
    }

    public void startSession() {
        sessionManager.startNewPomodoro();
    }

    private boolean isCurrentStatusWaiting() {
        return currentStatus.equals(Status.WAITING.toString());
    }

    private String formatTimeString(final String timeValue) {
        if (isStringContainOneCharNumber(timeValue)) {
            return String.format("0%1$d", Integer.parseInt(timeValue));
        }
        return timeValue;
    }

    private Boolean isCurrentStatusChanged(final String newStatus) {
        if (!newStatus.equals(currentStatus)) {
            return true;
        }
        return false;
    }

    private void logChangeStatusEvent(final String newStatus) {
        StringBuilder logString = new StringBuilder(LogMessages.STATUS_WAS_CHANGED.toString());
        logString.append(newStatus);
        logger.addRecord(logString.toString());
    }

    private void logStartPomodoroSessionEvent() {
        StringBuilder logString = new StringBuilder(LogMessages.SESSION_STARTED.toString());
        logger.addRecord(logString.toString());
    }

    public enum Status {
        WAITING("Waiting"),
        POMODORO("Pomodoro"),
        BREAK("Break"),
        BIG_BREAK("Big break");

        private final String name;

        Status(final String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return name;
        }
    }

    public enum LogMessages {
        SESSION_STARTED("Start pomodoro session"),
        STATUS_WAS_CHANGED("Session status changed to ");

        private final String name;
        private LogMessages(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
