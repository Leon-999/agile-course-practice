package ru.unn.agile.LongArithmetic.viewmodel;

import ru.unn.agile.LongArithmetic.model.LongNumber;
import ru.unn.agile.LongArithmetic.model.Matrix;

public class ViewModel {

    private static final int InitialSizeMatrix = 1;

    private String mFirstMatrix;
    private String nFirstMatrix;
    private String mSecondMatrix;
    private String nSecondMatrix;
    private int m1;
    private int n1;
    private int m2;
    private int n2;

    private String [][] firstMatrixTable;
    private String [][] secondMatrixTable;
    private Matrix firstMultiplier;
    private Matrix secondMultiplier;
    private Matrix resultMatrix;

    private String status;
    private boolean isOkButtonEnabled = false;
    private boolean isMultiplyButtonEnabled = false;

    public ViewModel() {
        mFirstMatrix = "";
        nFirstMatrix = "";
        mSecondMatrix = "";
        nSecondMatrix = "";
        status = Status.WAITINGMN;

        firstMatrixTable = new String[InitialSizeMatrix][InitialSizeMatrix];
        secondMatrixTable = new String[InitialSizeMatrix][InitialSizeMatrix];

        isOkButtonEnabled = false;
        isMultiplyButtonEnabled = false;

    }

    public void processKeyInTextField(final int keyCode) {
        parseInputMN();
        parseInputMatrices();

        if (keyCode == KeyboardKeys.ENTER) {
            enterPresed();
        }
        if(keyCode == KeyboardKeys.M) {
            mPresed();
        }
    }

    private boolean parseInputMN() {
        isMultiplyButtonEnabled = false;

        try {
            if (!mFirstMatrix.isEmpty()) {
                m1 = Integer.parseInt(mFirstMatrix);
            }
            if (!nFirstMatrix.isEmpty()) {
                n1 = Integer.parseInt(nFirstMatrix);
            }
            if (!mSecondMatrix.isEmpty()) {
                m2 = Integer.parseInt(mSecondMatrix);
            }
            if (!nSecondMatrix.isEmpty()) {
                n2 = Integer.parseInt(nSecondMatrix);
            }

        } catch (Exception e) {
            status = Status.BAD_FORMAT;
            isOkButtonEnabled = false;
            return false;
        }

        isOkButtonEnabled = isInputMNAvailable();
        if(status != Status.WAITING) {
            if (isOkButtonEnabled) {
                status = Status.READYOK;
            } else {
                status = Status.WAITINGMN;
            }
        }

        return isOkButtonEnabled;
    }

    private void enterPresed() {
        if (isOkButtonEnabled()) {
            firstMatrixTable = new String[m1][n1];
            secondMatrixTable = new String[m2][n2];
            firstMultiplier = new Matrix(m1, n1);
            secondMultiplier = new Matrix(m2, n2);
            status = Status.WAITING;
        }
    }

    public boolean isOkButtonEnabled() { return isOkButtonEnabled; }

    private  void mPresed() {
        if (isMultiplyButtonEnabled()) {
            initializeMatrices();
            multiplyMatrices();
            status = Status.SUCCESS;
        }
    }

    public boolean isMultiplyButtonEnabled() { return isMultiplyButtonEnabled; }

    private boolean isInputMNAvailable() {
        boolean isAvailable = !mFirstMatrix.isEmpty() && !nFirstMatrix.isEmpty() &&
                              !mSecondMatrix.isEmpty() && !nSecondMatrix.isEmpty();

        return isAvailable;
    }

    private void initializeMatrices() {
        parseInputMatrices();
    }

    private  void parseInputMatrices() {
        if(status != Status.WAITING) {
            return;
        }

        try {
            LongNumber value;
            for (int i = 0; i < m1; i++) {
                for (int j = 0; j < n1; j++) {
                    value = new LongNumber(firstMatrixTable[i][j]);
                    firstMultiplier.setElement(i, j, value);
                }
            }

            for (int i = 0; i < m2; i++) {
                for (int j = 0; j < n2; j++) {
                    value = new LongNumber(secondMatrixTable[i][j]);
                    secondMultiplier.setElement(i, j, value);
                }
            }

            status = Status.READYMULTIPLY;
            isMultiplyButtonEnabled = true;
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
            isMultiplyButtonEnabled = false;
        }
    }

    public void multiplyMatrices() {
        if (status == Status.READYMULTIPLY) {
            resultMatrix = firstMultiplier.multiply(secondMultiplier);
        }
    }

    public String getMFirstMatrix() { return mFirstMatrix; }

    public void setMFirstMatrix(final String mFirstMatrix) {
        if (mFirstMatrix.equals(this.mFirstMatrix)) {
            return;
        }

        this.mFirstMatrix = mFirstMatrix;
    }

    public String getNFirstMatrix() { return nFirstMatrix; }

    public void setNFirstMatrix(final String nFirstMatrix) {
        if (nFirstMatrix.equals(this.nFirstMatrix)) {
            return;
        }

        this.nFirstMatrix = nFirstMatrix;
    }

    public String getMSecondMatrix() { return mSecondMatrix; }

    public void setMSecondMatrix(final String mSecondMatrix) {
        if (mSecondMatrix.equals(this.mSecondMatrix)) {
            return;
        }

        this.mSecondMatrix = mSecondMatrix;
    }

    public String getNSecondMatrix() { return nSecondMatrix; }

    public void setNSecondMatrix(final String nSecondMatrix) {
        if (nSecondMatrix.equals(this.nSecondMatrix)) {
            return;
        }

        this.nSecondMatrix = nSecondMatrix;
    }

    public Matrix getResultMatrix() { return resultMatrix; }

    public String getStatus() { return status; }

    public void setValueToFirstMatrix(int i, int j, String newValue) {
        if(i > -1 && i < m1 && i > -1 && j < n1) {
            firstMatrixTable[i][j] = newValue;
        }
    }

    public void setValueToSecondMatrix(int i, int j, String newValue) {
        if(i > -1 && i < m2 && i > -1 && j < n2) {
            secondMatrixTable[i][j] = newValue;
        }
    }

    public final class Status {
        public static final String WAITINGMN = "Please provide input data: M and N for matrices";
        public static final String WAITING = "Please provide input data: write in matrices";
        public static final String READYOK = "Press 'Ok'";
        public static final String READYMULTIPLY = "Press 'Multiply'";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";

        private Status() { }
    }
}