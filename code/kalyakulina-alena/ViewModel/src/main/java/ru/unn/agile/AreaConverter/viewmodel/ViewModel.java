package ru.unn.agile.AreaConverter.viewmodel;

import ru.unn.agile.AreaConverter.model.AreaConverter;
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

    public void setFrom(final AreaMeasure from) {
        this.from = from;
    }

    public void setTo(final AreaMeasure to) {
        this.to = to;
    }

    public boolean parseInput() {
        if (inputArea.isEmpty()) {
            status = Status.WAITING;
            isConvertButtonEnable = false;
        } else {
            double parsedInputArea;

            try {
                parsedInputArea = Double.parseDouble(inputArea);
            } catch (Exception exception) {
                status = Status.WRONG_FORMAT;
                isConvertButtonEnable = false;
                return false;
            }

            if (parsedInputArea < 0.0) {
                status = Status.NEGATIVE_AREA;
                isConvertButtonEnable = false;
            } else {
                status = Status.READY;
                resultArea = "";
                isConvertButtonEnable = true;
            }
        }
        return isConvertButtonEnable;
    }

    public void convert() {
        if (parseInput()) {
            AreaConverter converter = new AreaConverter(from, to);
            double convertedArea = converter.convert(Double.parseDouble(inputArea));
            resultArea = Double.toString(convertedArea);
            status = Status.SUCCESS;
        }
    }
}
