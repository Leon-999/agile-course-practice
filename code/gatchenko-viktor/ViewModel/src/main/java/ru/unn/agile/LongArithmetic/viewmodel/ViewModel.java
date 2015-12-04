package ru.unn.agile.LongArithmetic.viewmodel;

import ru.unn.agile.LongArithmetic.model.Matrix;

public class ViewModel {

    private String mFirstMatrix;
    private String nFirstMatrix;
    private String mSecondMatrix;
    private String nSecondMatrix;

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

        isOkButtonEnabled = false;
        isMultiplyButtonEnabled = false;

    }

    public void processKeyInTextField(final int keyCode) {
        parseInputMN();
    }

    private boolean parseInputMN() {
        isMultiplyButtonEnabled = false;

        try {
            if (!mFirstMatrix.isEmpty()) {
                Integer.parseInt(mFirstMatrix);
            }
            if (!nFirstMatrix.isEmpty()) {
                Integer.parseInt(nFirstMatrix);
            }
            if (!mSecondMatrix.isEmpty()) {
                Integer.parseInt(mSecondMatrix);
            }
            if (!nSecondMatrix.isEmpty()) {
                Integer.parseInt(nSecondMatrix);
            }
        } catch (Exception e) {
            status = Status.BAD_FORMAT;
            isOkButtonEnabled = false;
            return false;
        }

        isOkButtonEnabled = isInputMNAvailable();
        if (isOkButtonEnabled) {
            status = Status.READYOK;
        } else {
            status = Status.WAITINGMN;
        }

        return isOkButtonEnabled;
    }

    private boolean isInputMNAvailable() {
        boolean isAvailable = !mFirstMatrix.isEmpty() && !nFirstMatrix.isEmpty() &&
                              !mSecondMatrix.isEmpty() && !nSecondMatrix.isEmpty();

        return isAvailable;
    }

    private void initializeMatrices() {

    }

    public void multiplyMatrices() {

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

    public final class Status {
        public static final String WAITINGMN = "Please provide input data: M and N for matrices";
        public static final String WAITING = "Please provide input data: write in matrices";
        public static final String READYOK = "Press 'Ok'";
        public static final String READY = "Press 'Multiply'";
        public static final String BAD_FORMAT = "Bad format";
        public static final String SUCCESS = "Success";

        private Status() { }
    }
}
