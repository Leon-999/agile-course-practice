package ru.unn.agile.LongArithmetic.viewmodel;

import ru.unn.agile.LongArithmetic.model.LongNumber;
import ru.unn.agile.LongArithmetic.model.Matrix;

import java.util.Vector;

public class ViewModel {

    public static final int UNDEFINED_SIZE = -1;
    private static final int INITIAL_SIZE_MATRIX = 1;

    private String titleHeightFirstMatrix;
    private String titleWidthFirstMatrix;
    private String titleHeightSecondMatrix;
    private String titleWidthSecondMatrix;
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

    private ILogger logger;

    public ViewModel(final ILogger logger) {
        setLogger(logger);
        titleHeightFirstMatrix = "";
        titleWidthFirstMatrix = "";
        titleHeightSecondMatrix = "";
        titleWidthSecondMatrix = "";
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

    public void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Illegal argument: logger parameter can't be null");
        }
        this.logger = logger;
    }

    public void processingInputMatrixSizes() {
        logInputMatrixSizes();

        parseInputMatrixSizes();
        if (isOkButtonEnabled()) {
            logger.write(LogMessages.OK_WAS_PRESSED);
            firstMatrixTable = new String[heightFirstMatrix][widthFirstMatrix];
            secondMatrixTable = new String[heightSecondMatrix][widthSecondMatrix];
            firstMultiplier = new Matrix(heightFirstMatrix, widthFirstMatrix);
            secondMultiplier = new Matrix(heightSecondMatrix, widthSecondMatrix);
            status = Status.WAITING;
            isMultiplyButtonEnabled = true;
        }
    }

    private void logInputMatrixSizes() {
        String message = LogMessages.EDITING_SIZE_MATRICES_FINISHED
                            + "Size first matrix: "
                            + titleHeightFirstMatrix + " x "
                            + titleWidthFirstMatrix + "; "
                            + "Size second matrix: "
                            + titleHeightSecondMatrix + " x "
                            + titleWidthSecondMatrix;
        logger.write(message);
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
        if (!titleHeightFirstMatrix.isEmpty()) {
            heightFirstMatrix = Integer.parseInt(titleHeightFirstMatrix);
        }
        if (!titleWidthFirstMatrix.isEmpty()) {
            widthFirstMatrix = Integer.parseInt(titleWidthFirstMatrix);
        }
        if (!titleHeightSecondMatrix.isEmpty()) {
            heightSecondMatrix = Integer.parseInt(titleHeightSecondMatrix);
        }
        if (!titleWidthSecondMatrix.isEmpty()) {
            widthSecondMatrix = Integer.parseInt(titleWidthSecondMatrix);
        }
    }

    public boolean isOkButtonEnabled() {
        return isOkButtonEnabled;
    }

    private boolean isInputMatrixSizesAvailable() {
        return !(titleHeightFirstMatrix.isEmpty() || titleWidthFirstMatrix.isEmpty()
               || titleHeightSecondMatrix.isEmpty() || titleWidthSecondMatrix.isEmpty());
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
            logger.write(LogMessages.BAD_INPUT);
        }
    }

    private boolean isInputMatricesAvailable() {
        return isInputMatricesAvailable;
    }

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
        logger.write(LogMessages.MULTIPLY_WAS_PRESSED);
        if (isInputMatricesAvailable() && status == Status.READY_MULTIPLY) {
            resultMatrix = firstMultiplier.multiply(secondMultiplier);
            status = Status.SUCCESS;
            logger.write(LogMessages.MULTIPLY_WAS_ENDED + Status.SUCCESS);
        }
    }

    public void setHeightFirstMatrix(final String titleHeightFirstMatrix) {
        if (!titleHeightFirstMatrix.equals(this.titleHeightFirstMatrix)) {
            this.titleHeightFirstMatrix = titleHeightFirstMatrix;
            this.heightFirstMatrix = UNDEFINED_SIZE;
        }
    }

    public void setWidthFirstMatrix(final String titleWidthFirstMatrix) {
        if (!titleWidthFirstMatrix.equals(this.titleWidthFirstMatrix)) {
            this.titleWidthFirstMatrix = titleWidthFirstMatrix;
            this.widthFirstMatrix = UNDEFINED_SIZE;
        }
    }

    public void setHeightSecondMatrix(final String titleHeightSecondMatrix) {
        if (!titleHeightSecondMatrix.equals(this.titleHeightSecondMatrix)) {
            this.titleHeightSecondMatrix = titleHeightSecondMatrix;
            this.heightSecondMatrix = UNDEFINED_SIZE;
        }
    }

    public void setWidthSecondMatrix(final String titleWidthSecondMatrix) {
        if (!titleWidthSecondMatrix.equals(this.titleWidthSecondMatrix)) {
            this.titleWidthSecondMatrix = titleWidthSecondMatrix;
            this.widthSecondMatrix = UNDEFINED_SIZE;
        }
    }

    public boolean isMultiplyButtonEnabled() {
        return isMultiplyButtonEnabled;
    }

    public int getHeightFirstMatrix() {
        return heightFirstMatrix;
    }

    public int getWidthFirstMatrix() {
        return widthFirstMatrix;
    }

    public int getHeightSecondMatrix() {
        return heightSecondMatrix;
    }

    public int getWidthSecondMatrix() {
        return widthSecondMatrix;
    }

    public Matrix getResultMatrix() {
        return resultMatrix;
    }

    public String getStatus() {
        return status;
    }

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

    public Vector<String> getLog() {
        return logger.read();
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

    public final class LogMessages {
        public static final String OK_WAS_PRESSED = "Input data - width and height for matrices: ";
        public static final String MULTIPLY_WAS_PRESSED = "Input data - matrices: ";
        public static final String EDITING_SIZE_MATRICES_FINISHED
                                               = "Updated input - width and height for matrices: ";
        public static final String MULTIPLY_WAS_ENDED = "Multiply was ended to ";
        public static final String BAD_INPUT = "Bad input data";

        private LogMessages() { }
    }
}
