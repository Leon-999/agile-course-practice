package ru.unn.agile.LongArithmetic.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ru.unn.agile.LongArithmetic.model.Matrix;
import ru.unn.agile.LongArithmetic.viewmodel.ViewModel.Status;

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
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getMFirstMatrix());
        assertEquals("", viewModel.getNFirstMatrix());
        assertEquals("", viewModel.getMSecondMatrix());
        assertEquals("", viewModel.getNSecondMatrix());
        assertEquals(Status.WAITINGMN, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingMNInTheBeginning() {
        assertEquals(Status.WAITINGMN, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyOKWhenFieldsMNAreFill() {
        fillMNFields();

        viewModel.parseInputMN();

        assertEquals(Status.READYOK, viewModel.getStatus());
    }

    private void fillMNFields() {
        viewModel.setMFirstMatrix("1");
        viewModel.setNFirstMatrix("1");
        viewModel.setMSecondMatrix("1");
        viewModel.setNSecondMatrix("1");
    }


    @Test
    public void isStatusWaitingWhenMultiplyWithEmptyFieldsMN() {
        viewModel.processingInputMatrices();

        assertEquals(Status.WAITINGMN, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.setMFirstMatrix("a");
        viewModel.processingInputMN();

        assertEquals(Status.BADFORMAT, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.setMFirstMatrix("a");
        viewModel.processingInputMN();
        viewModel.setMFirstMatrix("1");
        viewModel.processingInputMN();

        assertEquals(Status.WAITINGMN, viewModel.getStatus());
    }

    @Test
    public void isOkButtonDisabledInitially() {
        assertEquals(false, viewModel.isOkButtonEnabled());
    }

    @Test
    public void isMultiplyButtonDisabledInitially() {
        assertEquals(false, viewModel.isMultiplyButtonEnabled());
    }

    @Test
    public void isOkButtonDisabledWhenFormatIsBad() {
        fillMNFields();
        viewModel.processingInputMN();
        assertEquals(true, viewModel.isOkButtonEnabled());

        viewModel.setMFirstMatrix("some");
        viewModel.processingInputMN();

        assertEquals(false, viewModel.isOkButtonEnabled());
    }

    @Test
    public void isOkButtonDisabledWithIncompleteInput() {
        viewModel.setMFirstMatrix("1");
        viewModel.setNFirstMatrix("1");

        viewModel.processingInputMN();

        assertEquals(false, viewModel.isOkButtonEnabled());
    }

    @Test
    public void isOkButtonEnabledWithCorrectInput() {
        fillMNFields();

        viewModel.processingInputMN();

        assertEquals(true, viewModel.isOkButtonEnabled());
    }

    @Test
    public void canPerformMultiplyAction() {
        fillMNFields();
        viewModel.processingInputMN();
        viewModel.setValueToFirstMatrix(0, 0, "2");
        viewModel.setValueToSecondMatrix(0, 0, "2");

        viewModel.processingInputMatrices();
        Matrix resultMatrix = viewModel.getResultMatrix();
        boolean success = resultMatrix.getElement(0, 0).equals(4);

        assertEquals(true, success);
    }

    @Test
    public void canPerformMultiplyMatrix1x2And2x1() {
        fillMNFields1x2And2x1();
        viewModel.processingInputMN();
        fillMatrix1x2And2x1();
        viewModel.processingInputMatrices();

        Matrix resultMatrix = viewModel.getResultMatrix();
        boolean success = resultMatrix.getElement(0, 0).equals(4);

        assertEquals(true, success);
    }

    private void fillMNFields1x2And2x1() {
        viewModel.setMFirstMatrix("1");
        viewModel.setNFirstMatrix("2");
        viewModel.setMSecondMatrix("2");
        viewModel.setNSecondMatrix("1");
    }

    private void fillMatrix1x2And2x1() {
        viewModel.setValueToFirstMatrix(0, 0, "1");
        viewModel.setValueToFirstMatrix(0, 1, "1");
        viewModel.setValueToSecondMatrix(0, 0, "2");
        viewModel.setValueToSecondMatrix(1, 0, "2");
    }

    @Test
    public void canSetSuccessMessage() {
        fillMNFields1x2And2x1();
        viewModel.processingInputMN();
        fillMatrix1x2And2x1();
        viewModel.processingInputMatrices();

        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyMultiplyWhenAllFieldsAreFill() {
        fillMNFields1x2And2x1();
        viewModel.processingInputMN();;
        fillMatrix1x2And2x1();
        viewModel.parseInputMatrices();

        assertEquals(Status.READYMULTIPLY, viewModel.getStatus());
    }
}
