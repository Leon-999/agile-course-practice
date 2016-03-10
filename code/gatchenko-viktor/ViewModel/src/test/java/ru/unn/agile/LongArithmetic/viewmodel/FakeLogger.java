package ru.unn.agile.LongArithmetic.viewmodel;

import java.util.Vector;

public class FakeLogger implements ILogger {
    private Vector<String> log = new Vector<String>();

    @Override
    public void write(final String message) {
        log.add(message);
    }

    @Override
    public Vector<String> read() {
        return log;
    }
}
