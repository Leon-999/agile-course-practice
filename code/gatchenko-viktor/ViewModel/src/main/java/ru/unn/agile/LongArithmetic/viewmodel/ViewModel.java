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

    public ViewModel() {
        mFirstMatrix = "";
        nFirstMatrix = "";
        mSecondMatrix = "";
        nSecondMatrix = "";
        status = Status.WAITINGMN;

    }

    private void initializeMatrices() {

    }

    private void multiplyMatrices() {

    }

    public String getMFirstMatrix() { return mFirstMatrix; }

    public String getNFirstMatrix() { return nFirstMatrix; }

    public String getMSecondMatrix() { return mSecondMatrix; }

    public String getNSecondMatrix() { return nSecondMatrix; }

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
