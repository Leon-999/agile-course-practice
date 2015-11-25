package ru.unn.agile.CurrencyConverter.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.CurrencyConverter.Model.UnitCurrency;
import ru.unn.agile.CurrencyConverter.viewmodel.ViewModel;


public class CurrencyConverter {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField inputValue;
    @FXML
    private ComboBox<UnitCurrency> inputCurrency;
    @FXML
    private ComboBox<UnitCurrency> outputCurrency;
    @FXML
    private Button btnConvert;

    @FXML
    void initialize() {

        inputValue.textProperty().bindBidirectional(viewModel.inputValueProperty());
        inputCurrency.valueProperty().bindBidirectional(viewModel.inputUnitProperty());
        outputCurrency.valueProperty().bindBidirectional(viewModel.outputUnitProperty());
        btnConvert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {

                viewModel.convert();
            }
        });
    }
}
