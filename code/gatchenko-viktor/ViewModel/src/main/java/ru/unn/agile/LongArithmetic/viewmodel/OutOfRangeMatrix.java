package ru.unn.agile.LongArithmetic.viewmodel;

public class OutOfRangeMatrix extends RuntimeException {

    private static final long serialVersionUID = -8161574123685606228L;

    public OutOfRangeMatrix(final String message) {
        super(message);
    }
}
