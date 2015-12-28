package ru.unn.agile.LongArithmetic.viewmodel;

public class BadFormatToCellExeption extends RuntimeException {

    private static final long serialVersionUID = -8161574175685606228L;

    public BadFormatToCellExeption(final String message) {
        super(message);
    }
}