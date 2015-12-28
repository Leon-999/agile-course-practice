package ru.unn.agile.LongArithmetic.viewmodel;

public class BadFormatToCellException extends RuntimeException {

    private static final long serialVersionUID = -8161574175685606228L;

    public BadFormatToCellException(final String message) {
        super(message);
    }
}