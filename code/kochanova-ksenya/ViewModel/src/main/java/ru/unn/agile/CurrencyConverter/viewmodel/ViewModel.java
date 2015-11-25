package ru.unn.agile.CurrencyConverter.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.CurrencyConverter.Model.Currency;
import ru.unn.agile.CurrencyConverter.Model.UnitCurrency;


import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty inputValue = new SimpleStringProperty();
    private final StringProperty outputValue = new SimpleStringProperty();

    private final ObjectProperty<UnitCurrency> inputUnit = new SimpleObjectProperty<>();
    private final ObjectProperty<UnitCurrency> outputUnit = new SimpleObjectProperty<>();

    private final BooleanProperty convertationDisable = new SimpleBooleanProperty();

    private final ObjectProperty<ObservableList<UnitCurrency>> units =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(UnitCurrency.values()));

    private final StringProperty status = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        inputValue.set("");
        outputValue.set("");
        status.set(Status.WAITING.toString());
        inputUnit.set(UnitCurrency.RUBLE);
        outputUnit.set(UnitCurrency.EURO);

        BooleanBinding couldConvert = new BooleanBinding() {
            {
                super.bind(inputValue);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        convertationDisable.bind(couldConvert.not());
        final List<StringProperty> fields = new ArrayList<StringProperty>() { {
            add(inputValue);
        } };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void convert() {
        if (convertationDisable.get()) {
            return;
        }

        Currency inputCurrency = new Currency(inputValue.getValue(), inputUnit.get());
        outputValue.set(outputUnit.get().convertCurrency(inputCurrency,
                outputUnit.get()).toString());
        status.set(Status.DONE.toString());
    }

    public StringProperty inputValueProperty() {
        return inputValue;
    }

    public StringProperty outputValueProperty() {
        return outputValue;
    }

    public final ObjectProperty<UnitCurrency> inputUnitProperty() {
        return inputUnit;
    }

    public final String getOutputValue() {
        return outputValue.get();
    }

    public final ObjectProperty<UnitCurrency> outputUnitProperty() {
        return outputUnit;
    }

    public ObjectProperty<ObservableList<UnitCurrency>> unitsProperty() {
        return units;
    }

    public final ObservableList<UnitCurrency> getUnits() {
        return units.get();
    }

    public BooleanProperty convertationDisableProperty() {
        return convertationDisable;
    }

    public final boolean getConvertationDisable() {
        return convertationDisable.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public final String getStatus() {
        return status.get();
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        if (inputValue.get().isEmpty()) {
            inputStatus = Status.WAITING;
        }
        try {
            if (!inputValue.get().isEmpty()) {
               double value = Double.parseDouble(inputValue.get());
                if (value < 0) {
                    inputStatus = Status.WRONG_FORMAT;
                }
            }
        } catch (NumberFormatException e) {
            inputStatus = Status.WRONG_FORMAT;
        }

        return inputStatus;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String valueBefore, final String valueAfter) {
            status.set(getInputStatus().toString());
        }
    }
}

enum Status {
    WAITING("Please, input value and choose currency units"),
    READY("Press 'Convert'"),
    WRONG_FORMAT("Wrong format, please, use 0.00 format"),
    DONE("Done");

    private final String name;
    private Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
