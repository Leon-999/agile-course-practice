package ru.unn.agile.LongArithmetic.viewmodel;

import java.util.Vector;

public interface ILogger {

    void write(final String message);

    Vector<String> read();
}
