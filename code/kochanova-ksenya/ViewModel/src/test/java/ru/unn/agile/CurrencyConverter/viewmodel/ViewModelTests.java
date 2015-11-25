package ru.unn.agile.CurrencyConverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.CurrencyConverter.Model.UnitCurrency;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultInputValue() {
        assertEquals("", viewModel.inputValueProperty().get());
    }

    @Test
    public void canSetDefaultOutputValue() {
        assertEquals("", viewModel.outputValueProperty().get());
    }

    @Test
         public void canSetDefaultInputUnit() {
        assertEquals(UnitCurrency.RUBLE, viewModel.inputUnitProperty().get());
    }

    @Test
    public void canSetDefaultOutputUnit() {
        assertEquals(UnitCurrency.EURO, viewModel.outputUnitProperty().get());
    }

    @Test
    public void canSetDefaultMessage() {
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenValueFieldEmpty() {
        viewModel.inputValueProperty().set("");

        viewModel.convert();

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenInputValueIsCorrect() {
        viewModel.inputValueProperty().set("500.70");

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canDetectWrongFormat() {
        viewModel.inputValueProperty().set("^-^");

        assertEquals(Status.WRONG_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void convertButtonIsDisabledWhenFormatIsWrong() {

        viewModel.inputValueProperty().set("1,1");

        assertTrue(viewModel.convertationDisableProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.inputValueProperty().set(".");

        assertTrue(viewModel.convertationDisableProperty().get());
    }

    @Test
    public void canSetDoneMessageWhenConvertationIsDone() {
        viewModel.inputValueProperty().set("70");
        viewModel.inputUnitProperty().set(UnitCurrency.RUBLE);
        viewModel.outputUnitProperty().set(UnitCurrency.DOLLAR);

        viewModel.convert();

        assertEquals(Status.DONE.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetWrongFormatMessage() {
        viewModel.inputValueProperty().set("^-^");

        assertEquals(Status.WRONG_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenDataIsCorrect() {
        viewModel.inputValueProperty().set("135.20");
        viewModel.inputUnitProperty().set(UnitCurrency.EURO);
        viewModel.outputUnitProperty().set(UnitCurrency.DOLLAR);

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void convertRubleToDollarIsCorrect() {
        viewModel.inputValueProperty().set("100");
        viewModel.inputUnitProperty().set(UnitCurrency.RUBLE);
        viewModel.outputUnitProperty().set(UnitCurrency.DOLLAR);

        viewModel.convert();

        assertEquals("1.6", viewModel.outputValueProperty().get());
        assertEquals(UnitCurrency.DOLLAR, viewModel.outputUnitProperty().get());
    }

}
