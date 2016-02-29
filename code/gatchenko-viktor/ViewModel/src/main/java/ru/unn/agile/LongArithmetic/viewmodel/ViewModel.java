package ru.unn.agile.LongArithmetic.viewmodel;

import ru.unn.agile.LongArithmetic.model.LongNumber;
import ru.unn.agile.LongArithmetic.model.Matrix;

public class ViewModel {

    public static final int UNDEFINED_SIZE = -1;
    private static final int INITIAL_SIZE_MATRIX = 1;

    private String tittleHeightFirstMatrix;
    private String tittleWidthFirstMatrix;
    private String tittleHeightSecondMatrix;
    private String tittleWidthSecondMatrix;
    private int heightFirstMatrix;
    private int widthFirstMatrix;
    private int heightSecondMatrix;
    private int widthSecondMatrix;

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
        tittleHeightFirstMatrix = "";
        tittleWidthFirstMatrix = "";
        tittleHeightSecondMatrix = "";
        tittleWidthSecondMatrix = "";
        heightFirstMatrix = UNDEFINED_SIZE;
        widthFirstMatrix = UNDEFINED_SIZE;
        heightSecondMatrix = UNDEFINED_SIZE;
        widthSecondMatrix = UNDEFINED_SIZE;
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
            firstMatrixTable = new String[heightFirstMatrix][widthFirstMatrix];
            secondMatrixTable = new String[heightSecondMatrix][widthSecondMatrix];
            firstMultiplier = new Matrix(heightFirstMatrix, widthFirstMatrix);
            secondMultiplier = new Matrix(heightSecondMatrix, widthSecondMatrix);
            status = Status.WAITING;
            isMultiplyButtonEnabled = true;
        }
    }

    public void parseInputMatrixSizes() {
        isMultiplyButtonEnabled = false;
        isOkButtonEnabled = false;

        try {
            tryParseInputMatrixSizes();

            if (isInputMatrixSizesAvailable() && widthFirstMatrix == heightSecondMatrix) {
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
        if (!tittleHeightFirstMatrix.isEmpty()) {
            heightFirstMatrix = Integer.parseInt(tittleHeightFirstMatrix);
        }
        if (!tittleWidthFirstMatrix.isEmpty()) {
            widthFirstMatrix = Integer.parseInt(tittleWidthFirstMatrix);
        }
        if (!tittleHeightSecondMatrix.isEmpty()) {
            heightSecondMatrix = Integer.parseInt(tittleHeightSecondMatrix);
        }
        if (!tittleWidthSecondMatrix.isEmpty()) {
            widthSecondMatrix = Integer.parseInt(tittleWidthSecondMatrix);
        }
    }

    public boolean isOkButtonEnabled() { return isOkButtonEnabled; }

    private boolean isInputMatrixSizesAvailable() {
        return !(tittleHeightFirstMatrix.isEmpty() || tittleWidthFirstMatrix.isEmpty()
               || tittleHeightSecondMatrix.isEmpty() || tittleWidthSecondMatrix.isEmpty());
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
        if (!heightFirstMatrix.equals(this.tittleHeightFirstMatrix)) {
            this.tittleHeightFirstMatrix = heightFirstMatrix;
            this.heightFirstMatrix = UNDEFINED_SIZE;
        }
    }

    public void setWidthFirstMatrix(final String widthFirstMatrix) {
        if (!widthFirstMatrix.equals(this.tittleWidthFirstMatrix)) {
            this.tittleWidthFirstMatrix = widthFirstMatrix;
            this.widthFirstMatrix = UNDEFINED_SIZE;
        }
    }

    public void setHeightSecondMatrix(final String heightSecondMatrix) {
        if (!heightSecondMatrix.equals(this.tittleHeightSecondMatrix)) {
            this.tittleHeightSecondMatrix = heightSecondMatrix;
            this.heightSecondMatrix = UNDEFINED_SIZE;
        }
    }

    public void setWidthSecondMatrix(final String widthSecondMatrix) {
        if (!widthSecondMatrix.equals(this.tittleWidthSecondMatrix)) {
            this.tittleWidthSecondMatrix = widthSecondMatrix;
            this.widthSecondMatrix = UNDEFINED_SIZE;
        }
    }

    public boolean isMultiplyButtonEnabled() { return isMultiplyButtonEnabled; }

    public int getHeightFirstMatrix() { return heightFirstMatrix; }

    public int getWidthFirstMatrix() { return widthFirstMatrix; }

    public int getHeightSecondMatrix() { return heightSecondMatrix; }

    public int getWidthSecondMatrix() { return widthSecondMatrix; }

    public Matrix getResultMatrix() { return resultMatrix; }

    public String getStatus() { return status; }

    public void setValueToFirstMatrix(final int i, final int j, final String newValue) {
        if (i > -1 && i < heightFirstMatrix && j > -1 && j < widthFirstMatrix) {
            firstMatrixTable[i][j] = newValue;
        } else {
            throw new OutOfRangeMatrix("Error: out of range matrix");
        }
    }

    public void setValueToSecondMatrix(final int i, final int j, final String newValue) {
        if (i > -1 && i < heightSecondMatrix && j > -1 && j < widthSecondMatrix) {
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
