package ru.unn.agile.LongArithmetic.viewmodel;

import ru.unn.agile.LongArithmetic.model.LongNumber;
import ru.unn.agile.LongArithmetic.model.Matrix;

public class ViewModel {

    public static final int UNDEFINED_SIZE = -1;
    private static final int INITIAL_SIZE_MATRIX = 1;

    private String stringHeight1;
    private String stringWidth1;
    private String stringHeight2;
    private String stringWidth2;
    private int intHeight1;
    private int intWidth1;
    private int intHeight2;
    private int intWidth2;

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
        stringHeight1 = "";
        stringWidth1 = "";
        stringHeight2 = "";
        stringWidth2 = "";
        intHeight1 = UNDEFINED_SIZE;
        intWidth1 = UNDEFINED_SIZE;
        intHeight2 = UNDEFINED_SIZE;
        intWidth2 = UNDEFINED_SIZE;
        status = Status.WAITING_WIDTH_AND_HEIGHT_MATRICES;

        firstMatrixTable = new String[INITIAL_SIZE_MATRIX][INITIAL_SIZE_MATRIX];
        secondMatrixTable = new String[INITIAL_SIZE_MATRIX][INITIAL_SIZE_MATRIX];

        isOkButtonEnabled = false;
        isMultiplyButtonEnabled = false;
        isInputMatricesAvailable = false;
    }

    public void processingInputMatrixSizes() {
        parseInputMatrixSizes();
        if (isOkButtonEnabled()) {
            firstMatrixTable = new String[intHeight1][intWidth1];
            secondMatrixTable = new String[intHeight2][intWidth2];
            firstMultiplier = new Matrix(intHeight1, intWidth1);
            secondMultiplier = new Matrix(intHeight2, intWidth2);
            status = Status.WAITING;
            isMultiplyButtonEnabled = true;
        }
    }

    public void parseInputMatrixSizes() {
        isMultiplyButtonEnabled = false;
        isOkButtonEnabled = false;

        try {
            tryParseInputMatrixSizes();

            if (isInputMatrixSizesAvailable() && intWidth1 == intHeight2) {
                isOkButtonEnabled = true;
            }
            if (isOkButtonEnabled) {
                status = Status.READY_OK;
            } else {
                status = Status.WAITING_WIDTH_AND_HEIGHT_MATRICES;
            }
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
        }
    }

    private void tryParseInputMatrixSizes() {
        if (!stringHeight1.isEmpty()) {
            intHeight1 = Integer.parseInt(stringHeight1);
        }
        if (!stringWidth1.isEmpty()) {
            intWidth1 = Integer.parseInt(stringWidth1);
        }
        if (!stringHeight2.isEmpty()) {
            intHeight2 = Integer.parseInt(stringHeight2);
        }
        if (!stringWidth2.isEmpty()) {
            intWidth2 = Integer.parseInt(stringWidth2);
        }
    }

    public boolean isOkButtonEnabled() { return isOkButtonEnabled; }

    private boolean isInputMatrixSizesAvailable() {
        return !(stringHeight1.isEmpty() || stringWidth1.isEmpty()
               || stringHeight2.isEmpty() || stringWidth2.isEmpty());
    }

    public  void parseInputMatrices() {
        if (status == Status.WAITING_WIDTH_AND_HEIGHT_MATRICES) {
            return;
        }
        try {
            tryParseMatrix(firstMatrixTable);
            tryParseMatrix(secondMatrixTable);
            isInputMatricesAvailable = true;
            status = Status.READY_MULTIPLY;
        } catch (Exception e) {
            isInputMatricesAvailable = false;
            status = Status.BAD_FORMAT;
        }
    }

    private boolean isInputMatricesAvailable() { return isInputMatricesAvailable; }

    private void tryParseMatrix(final String[][] matrix) throws Exception {
        LongNumber value;
        int rows = matrix.length;
        for (int i = 0; i < rows; i++) {
            int cols = matrix[i].length;
            for (int j = 0; j < cols; j++) {
                value = new LongNumber(matrix[i][j]);
                if (value.isUndefined()) {
                    throw new BadFormatToCellException("Can't parse input matrices, bad format");
                }
                if (matrix.equals(firstMatrixTable)) {
                    firstMultiplier.setElement(i, j, value);
                } else {
                    secondMultiplier.setElement(i, j, value);
                }
            }
        }
    }

    public void multiplyMatrices() {
        if (isInputMatricesAvailable() && status == Status.READY_MULTIPLY) {
            resultMatrix = firstMultiplier.multiply(secondMultiplier);
            status = Status.SUCCESS;
        }
    }

    public void setHeightFirstMatrix(final String heightFirstMatrix) {
        if (!heightFirstMatrix.equals(this.stringHeight1)) {
            this.stringHeight1 = heightFirstMatrix;
            intHeight1 = UNDEFINED_SIZE;
        }
    }

    public void setWidthFirstMatrix(final String widthFirstMatrix) {
        if (!widthFirstMatrix.equals(this.stringWidth1)) {
            this.stringWidth1 = widthFirstMatrix;
            intWidth1 = UNDEFINED_SIZE;
        }
    }

    public void setHeightSecondMatrix(final String heightSecondMatrix) {
        if (!heightSecondMatrix.equals(this.stringHeight2)) {
            this.stringHeight2 = heightSecondMatrix;
            intHeight2 = UNDEFINED_SIZE;
        }
    }

    public void setWidthSecondMatrix(final String widthSecondMatrix) {
        if (!widthSecondMatrix.equals(this.stringWidth2)) {
            this.stringWidth2 = widthSecondMatrix;
            intWidth2 = UNDEFINED_SIZE;
        }
    }

    public boolean isMultiplyButtonEnabled() { return isMultiplyButtonEnabled; }

    public int getHeightFirstMatrix() { return intHeight1; }

    public int getWidthFirstMatrix() { return intWidth1; }

    public int getHeightSecondMatrix() { return intHeight2; }

    public int getWidthSecondMatrix() { return intWidth2; }

    public Matrix getResultMatrix() { return resultMatrix; }

    public String getStatus() { return status; }

    public void setValueToFirstMatrix(final int i, final int j, final String newValue) {
        if (i > -1 && i < intHeight1 && j > -1 && j < intWidth1) {
            firstMatrixTable[i][j] = newValue;
        } else {
            throw new OutOfRangeMatrix("Error: out of range matrix");
        }
    }

    public void setValueToSecondMatrix(final int i, final int j, final String newValue) {
        if (i > -1 && i < intHeight2 && j > -1 && j < intWidth2) {
            secondMatrixTable[i][j] = newValue;
        } else {
            throw new OutOfRangeMatrix("Error: out of range matrix");
        }
    }

    public final class Status {
        public static final String WAITING_WIDTH_AND_HEIGHT_MATRICES
                               = "Please provide input data: width and height for matrices";
        public static final String WAITING = "Please provide input data: write in matrices";
        public static final String READY_OK = "Press 'Ok'";
        public static final String READY_MULTIPLY = "Press 'Multiply'";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";

        private Status() { }
    }
}
