package ru.unn.agile.LongArithmetic.viewmodel;

import ru.unn.agile.LongArithmetic.model.LongNumber;
import ru.unn.agile.LongArithmetic.model.Matrix;

public class ViewModel {

    public static final int UNDEFINED_SIZE = -1;
    private static final int INITIAL_SIZE_MATRIX = 1;

    private String heightFirstMatrix;
    private String widthFirstMatrix;
    private String heightSecondMatrix;
    private String widthSecondMatrix;
    private int height1;
    private int width1;
    private int height2;
    private int width2;

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
        heightFirstMatrix = "";
        widthFirstMatrix = "";
        heightSecondMatrix = "";
        widthSecondMatrix = "";
        height1 = UNDEFINED_SIZE;
        width1 = UNDEFINED_SIZE;
        height2 = UNDEFINED_SIZE;
        width2 = UNDEFINED_SIZE;
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
            firstMatrixTable = new String[height1][width1];
            secondMatrixTable = new String[height2][width2];
            firstMultiplier = new Matrix(height1, width1);
            secondMultiplier = new Matrix(height2, width2);
            status = Status.WAITING;
            isMultiplyButtonEnabled = true;
        }
    }

    public void parseInputMatrixSizes() {
        isMultiplyButtonEnabled = false;
        isOkButtonEnabled = false;

        try {
            tryParseInputMatrixSizes();

            if (isInputMatrixSizesAvailable() && width1 == height2) {
                isOkButtonEnabled = true;
            }
            if (status != Status.WAITING) {
                if (isOkButtonEnabled) {
                    status = Status.READY_OK;
                } else {
                    status = Status.WAITING_WIDTH_AND_HEIGHT_MATRICES;
                }
            }
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
        }
    }

    private void tryParseInputMatrixSizes() {
        if (!heightFirstMatrix.isEmpty()) {
            height1 = Integer.parseInt(heightFirstMatrix);
        }
        if (!widthFirstMatrix.isEmpty()) {
            width1 = Integer.parseInt(widthFirstMatrix);
        }
        if (!heightSecondMatrix.isEmpty()) {
            height2 = Integer.parseInt(heightSecondMatrix);
        }
        if (!widthSecondMatrix.isEmpty()) {
            width2 = Integer.parseInt(widthSecondMatrix);
        }
    }

    public boolean isOkButtonEnabled() { return isOkButtonEnabled; }

    private boolean isInputMatrixSizesAvailable() {
        return !(heightFirstMatrix.isEmpty() || widthFirstMatrix.isEmpty()
               || heightSecondMatrix.isEmpty() || widthSecondMatrix.isEmpty());
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
        if (!heightFirstMatrix.equals(this.heightFirstMatrix)) {
            this.heightFirstMatrix = heightFirstMatrix;
            height1 = UNDEFINED_SIZE;
        }
    }

    public void setWidthFirstMatrix(final String widthFirstMatrix) {
        if (!widthFirstMatrix.equals(this.widthFirstMatrix)) {
            this.widthFirstMatrix = widthFirstMatrix;
            width1 = UNDEFINED_SIZE;
        }
    }

    public void setHeightSecondMatrix(final String heightSecondMatrix) {
        if (!heightSecondMatrix.equals(this.heightSecondMatrix)) {
            this.heightSecondMatrix = heightSecondMatrix;
            height2 = UNDEFINED_SIZE;
        }
    }

    public void setWidthSecondMatrix(final String widthSecondMatrix) {
        if (!widthSecondMatrix.equals(this.widthSecondMatrix)) {
            this.widthSecondMatrix = widthSecondMatrix;
            width2 = UNDEFINED_SIZE;
        }
    }

    public boolean isMultiplyButtonEnabled() { return isMultiplyButtonEnabled; }

    public int getHeightFirstMatrix() { return height1; }

    public int getWidthFirstMatrix() { return width1; }

    public int getHeightSecondMatrix() { return height2; }

    public int getWidthSecondMatrix() { return width2; }

    public Matrix getResultMatrix() { return resultMatrix; }

    public String getStatus() { return status; }

    public void setValueToFirstMatrix(final int i, final int j, final String newValue) {
        if (i > -1 && i < height1 && j > -1 && j < width1) {
            firstMatrixTable[i][j] = newValue;
        } else {
            throw new OutOfRangeMatrix("Error: out of range matrix");
        }
    }

    public void setValueToSecondMatrix(final int i, final int j, final String newValue) {
        if (i > -1 && i < height2 && j > -1 && j < width2) {
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
