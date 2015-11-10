package ru.unn.agile.AreaConverter.viewmodel;

import ru.unn.agile.AreaConverter.model.AreaMeasure;

public class ViewModel {
    private String inputArea;
    private String resultArea;
    private String status;
    private AreaMeasure from;
    private AreaMeasure to;
    private boolean isConvertButtonEnable;

    public ViewModel() {
        inputArea = "";
        resultArea = "";
        status = Status.WAITING;
        from = AreaMeasure.SQUARE_METER;
        to = AreaMeasure.SQUARE_KILOMETER;
        isConvertButtonEnable = false;
    }

    public String getInputArea() {
        return inputArea;
    }

    public String getResultArea() {
        return resultArea;
    }

    public String getStatus() {
        return status;
    }

    public AreaMeasure getFrom() {
        return from;
    }

    public AreaMeasure getTo() {
        return to;
    }

    public boolean isConvertButtonEnabled() {
        return isConvertButtonEnable;
    }

    public void setInputArea(final String inputArea) {
        this.inputArea = inputArea;
    }

    public void parseInput() {
        if (inputArea.isEmpty()) {
            status = Status.WAITING;
            isConvertButtonEnable = false;
        } else {
            status = Status.READY;
            isConvertButtonEnable = true;
        }
    }
}
