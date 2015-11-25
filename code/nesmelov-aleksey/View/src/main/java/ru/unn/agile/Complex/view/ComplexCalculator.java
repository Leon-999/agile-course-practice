package ru.unn.agile.Complex.view;

import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import ru.unn.agile.Complex.viewmodel.*;

public class ComplexCalculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField firstRealTextField;
    @FXML
    private TextField firstImaginaryTextField;
    @FXML
    private TextField secondRealTextField;
    @FXML
    private TextField secondImaginaryTextField;
    @FXML
    private Text resultText;
    @FXML
    private Text errorsText;
    @FXML
    private ComboBox<Operation> operationsComboBox;
    @FXML
    private Button calculateButton;

    @FXML
    void initialize() {
        StringProperty tmpProperty = viewModel.getFirstRealProperty();
        firstRealTextField.textProperty().bindBidirectional(tmpProperty);

        tmpProperty = viewModel.getFirstImaginaryProperty();
        firstImaginaryTextField.textProperty().bindBidirectional(tmpProperty);

        tmpProperty = viewModel.getSecondRealProperty();
        secondRealTextField.textProperty().bindBidirectional(tmpProperty);

        tmpProperty = viewModel.getSecondImaginaryProperty();
        secondImaginaryTextField.textProperty().bindBidirectional(tmpProperty);

        tmpProperty = viewModel.getResultProperty();
        resultText.textProperty().bindBidirectional(tmpProperty);

        tmpProperty = viewModel.getErrorsProperty();
        errorsText.textProperty().bindBidirectional(tmpProperty);

        operationsComboBox.valueProperty().bindBidirectional(viewModel.getOperationProperty());
        calculateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent actionEvent) {
                viewModel.calculate();
            }
        });
    }
}
