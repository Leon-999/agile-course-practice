package ru.unn.agile.Metrics.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.unn.agile.Metrics.viewmodel.DistanceCalculatorViewModel;


public class DistanceCalculator {

    @FXML
    private DistanceCalculatorViewModel viewModel;
    @FXML
    private TextField txtField1stVector;
    @FXML
    private TextField txtField2ndVector;
    @FXML
    private Button btnCalculate;
    @FXML
    private ToggleGroup toggleGroupMetrics;
    @FXML
    private RadioButton radioBtnRhoInf;
    private RadioButton radioBtnSelected;

    @FXML
    public void initialize() {
        radioBtnSelected = radioBtnRhoInf;

        txtField1stVector.textProperty().bindBidirectional(viewModel.firstVectorProperty());
        txtField2ndVector.textProperty().bindBidirectional(viewModel.secondVectorProperty());

        btnCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });

        toggleGroupMetrics.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(final ObservableValue<? extends Toggle> observable,
                                final Toggle oldValue, final Toggle newValue) {
                radioBtnSelected = (RadioButton) newValue.getToggleGroup().getSelectedToggle();
                viewModel.setMetric(radioBtnSelected.getText());
            }
        });
    }
}
