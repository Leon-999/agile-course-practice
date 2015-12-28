package ru.unn.agile.LongArithmetic.viewmodel;

import ru.unn.agile.LongArithmetic.model.LongNumber;
import ru.unn.agile.LongArithmetic.model.Matrix;

public class ViewModel {

    private static final int INITIAL_SIZE_MATRIX = 1;

    private String rowsFirstMatrix;
    private String colsFirstMatrix;
    private String rowsSecondMatrix;
    private String colsSecondMatrix;
    private int rows1;
    private int cols1;
    private int rows2;
    private int cols2;

    private String [][] firstMatrixTable;
    private String [][] secondMatrixTable;
    private Matrix firstMultiplier;
    private Matrix secondMultiplier;
    private Matrix resultMatrix;

    private String status;
    private boolean isOkButtonEnabled;
    private boolean isMultiplyButtonEnabled;
    private boolean isInputMatricesAvailable;


    public ViewModel() {
        rowsFirstMatrix = "";
        colsFirstMatrix = "";
        rowsSecondMatrix = "";
        colsSecondMatrix = "";
        status = Status.WAITING_COLS_AND_ROWS;

        firstMatrixTable = new String[INITIAL_SIZE_MATRIX][INITIAL_SIZE_MATRIX];
        secondMatrixTable = new String[INITIAL_SIZE_MATRIX][INITIAL_SIZE_MATRIX];

        isOkButtonEnabled = false;
        isMultiplyButtonEnabled = false;
        isInputMatricesAvailable = false;
    }

    public void processingInputColsRows() {
        parseInputColsRows();
        if (isOkButtonEnabled()) {
            firstMatrixTable = new String[rows1][cols1];
            secondMatrixTable = new String[rows2][cols2];
            firstMultiplier = new Matrix(rows1, cols1);
            secondMultiplier = new Matrix(rows2, cols2);
            status = Status.WAITING;
            isMultiplyButtonEnabled = true;
        }
    }

    public void parseInputColsRows() {
        isMultiplyButtonEnabled = false;

        try {
            tryParseInputColsRows();

            isOkButtonEnabled = false;
            if (isInputColsRowsAvailable() && cols1 == rows2) {
                isOkButtonEnabled = true;
            }
            if (status != Status.WAITING) {
                if (isOkButtonEnabled) {
                    status = Status.READY_OK;
                } else {
                    status = Status.WAITING_COLS_AND_ROWS;
                }
            }
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
            isOkButtonEnabled = false;
        }
    }

    private void tryParseInputColsRows() {
        if (!rowsFirstMatrix.isEmpty()) {
            rows1 = Integer.parseInt(rowsFirstMatrix);
        }
        if (!colsFirstMatrix.isEmpty()) {
            cols1 = Integer.parseInt(colsFirstMatrix);
        }
        if (!rowsSecondMatrix.isEmpty()) {
            rows2 = Integer.parseInt(rowsSecondMatrix);
        }
        if (!colsSecondMatrix.isEmpty()) {
            cols2 = Integer.parseInt(colsSecondMatrix);
        }
    }

    public boolean isOkButtonEnabled() { return isOkButtonEnabled; }

    private boolean isInputColsRowsAvailable() {
        boolean isAvailable = !rowsFirstMatrix.isEmpty() && !colsFirstMatrix.isEmpty()
                            && !rowsSecondMatrix.isEmpty() && !colsSecondMatrix.isEmpty();

        return isAvailable;
    }

    public void processingInputMatrices() {
        parseInputMatrices();
        if (isInputMatricesAvailable()) {
            multiplyMatrices();
            status = Status.SUCCESS;
        }
    }

    public  void parseInputMatrices() {
        if (status == Status.WAITING_COLS_AND_ROWS) {
            return;
        }

        try {
            tryParseInputMatrices();
            isInputMatricesAvailable = true;
            status = Status.READY_MULTIPLY;
        } catch (Exception e) {
            isInputMatricesAvailable = false;
            status = Status.BAD_FORMAT;
        }
    }

    private boolean isInputMatricesAvailable() { return isInputMatricesAvailable; }

    private void tryParseInputMatrices() throws Exception {
        LongNumber value;
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols1; j++) {
                value = new LongNumber(firstMatrixTable[i][j]);
                if (value.isUndefined()) {
                    throw new Exception("Can't parse input matrices, bad format");
                }
                firstMultiplier.setElement(i, j, value);
            }
        }

        for (int i = 0; i < rows2; i++) {
            for (int j = 0; j < cols2; j++) {
                value = new LongNumber(secondMatrixTable[i][j]);
                if (value.isUndefined()) {
                    throw new Exception("Can't parse input matrices, bad format");
                }
                secondMultiplier.setElement(i, j, value);
            }
        }
    }

    private void multiplyMatrices() {
        if (status == Status.READY_MULTIPLY) {
            resultMatrix = firstMultiplier.multiply(secondMultiplier);
        }
    }

    public String getRowsFirstMatrix() { return rowsFirstMatrix; }

    public void setRowsFirstMatrix(final String rowsFirstMatrix) {
        if (rowsFirstMatrix.equals(this.rowsFirstMatrix)) {
            return;
        }

        this.rowsFirstMatrix = rowsFirstMatrix;
    }

    public String getColsFirstMatrix() { return colsFirstMatrix; }

    public void setColsFirstMatrix(final String colsFirstMatrix) {
        if (colsFirstMatrix.equals(this.colsFirstMatrix)) {
            return;
        }

        this.colsFirstMatrix = colsFirstMatrix;
    }

    public String getRowsSecondMatrix() { return rowsSecondMatrix; }

    public void setRowsSecondMatrix(final String rowsSecondMatrix) {
        if (rowsSecondMatrix.equals(this.rowsSecondMatrix)) {
            return;
        }

        this.rowsSecondMatrix = rowsSecondMatrix;
    }

    public String getColsSecondMatrix() { return colsSecondMatrix; }

    public void setColsSecondMatrix(final String colsSecondMatrix) {
        if (colsSecondMatrix.equals(this.colsSecondMatrix)) {
            return;
        }

        this.colsSecondMatrix = colsSecondMatrix;
    }

    public boolean isMultiplyButtonEnabled() { return isMultiplyButtonEnabled; }

    public int getRows1() { return rows1; }

    public int getCols1() { return cols1; }

    public int getRows2() { return rows2; }

    public int getCols2() { return cols2; }

    public Matrix getResultMatrix() { return resultMatrix; }

    public String getStatus() { return status; }

    public void setValueToFirstMatrix(final int i, final int j, final String newValue) {
        if (i > -1 && i < rows1 && i > -1 && j < cols1) {
            firstMatrixTable[i][j] = newValue;
        }
    }

    public void setValueToSecondMatrix(final int i, final int j, final String newValue) {
        if (i > -1 && i < rows2 && i > -1 && j < cols2) {
            secondMatrixTable[i][j] = newValue;
        }
    }

    public final class Status {
        public static final String WAITING_COLS_AND_ROWS = "Please provide input data: M and N for matrices";
        public static final String WAITING = "Please provide input data: write in matrices";
        public static final String READY_OK = "Press 'Ok'";
        public static final String READY_MULTIPLY = "Press 'Multiply'";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";

        private Status() { }
    }
}
