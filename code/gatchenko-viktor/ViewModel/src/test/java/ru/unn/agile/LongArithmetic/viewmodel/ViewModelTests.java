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
        assertEquals("", viewModel.getRowsFirstMatrix());
        assertEquals("", viewModel.getColsFirstMatrix());
        assertEquals("", viewModel.getRowsSecondMatrix());
        assertEquals("", viewModel.getColsSecondMatrix());
        assertEquals(Status.WAITING_COLS_AND_ROWS, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingColsRowsInTheBeginning() {
        assertEquals(Status.WAITING_COLS_AND_ROWS, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyOkWhenFieldsColsRowsAreFill() {
        fillColsRowsFields();

        viewModel.parseInputColsRows();

        assertEquals(Status.READY_OK, viewModel.getStatus());
    }

    private void fillColsRowsFields() {
        viewModel.setRowsFirstMatrix("1");
        viewModel.setColsFirstMatrix("1");
        viewModel.setRowsSecondMatrix("1");
        viewModel.setColsSecondMatrix("1");
    }


    @Test
    public void isStatusWaitingWhenMultiplyWithEmptyFieldsColsRows() {
        viewModel.processingInputMatrices();

        assertEquals(Status.WAITING_COLS_AND_ROWS, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.setRowsFirstMatrix("a");
        viewModel.processingInputColsRows();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.setRowsFirstMatrix("a");
        viewModel.processingInputColsRows();
        viewModel.setRowsFirstMatrix("1");
        viewModel.processingInputColsRows();

        assertEquals(Status.WAITING_COLS_AND_ROWS, viewModel.getStatus());
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
        fillColsRowsFields();
        viewModel.processingInputColsRows();
        assertEquals(true, viewModel.isOkButtonEnabled());

        viewModel.setRowsFirstMatrix("some");
        viewModel.processingInputColsRows();

        assertEquals(false, viewModel.isOkButtonEnabled());
    }

    @Test
    public void isOkButtonDisabledWithIncompleteInput() {
        viewModel.setRowsFirstMatrix("1");
        viewModel.setColsFirstMatrix("1");

        viewModel.processingInputColsRows();

        assertEquals(false, viewModel.isOkButtonEnabled());
    }

    @Test
    public void isOkButtonEnabledWithCorrectInput() {
        fillColsRowsFields();

        viewModel.processingInputColsRows();

        assertEquals(true, viewModel.isOkButtonEnabled());
    }

    @Test
    public void canPerformMultiplyAction() {
        fillColsRowsFields();
        viewModel.processingInputColsRows();
        viewModel.setValueToFirstMatrix(0, 0, "2");
        viewModel.setValueToSecondMatrix(0, 0, "2");

        viewModel.processingInputMatrices();
        Matrix resultMatrix = viewModel.getResultMatrix();
        boolean success = resultMatrix.getElement(0, 0).equals(4);

        assertEquals(true, success);
    }

    @Test
    public void canPerformMultiplyMatrix1x2And2x1() {
        defaultInput();
        viewModel.processingInputMatrices();

        Matrix resultMatrix = viewModel.getResultMatrix();
        boolean success = resultMatrix.getElement(0, 0).equals(4);

        assertEquals(true, success);
    }

    private void fillColsRowsFields1x2And2x1() {
        viewModel.setRowsFirstMatrix("1");
        viewModel.setColsFirstMatrix("2");
        viewModel.setRowsSecondMatrix("2");
        viewModel.setColsSecondMatrix("1");
    }

    private void fillMatrix1x2And2x1() {
        viewModel.setValueToFirstMatrix(0, 0, "1");
        viewModel.setValueToFirstMatrix(0, 1, "1");
        viewModel.setValueToSecondMatrix(0, 0, "2");
        viewModel.setValueToSecondMatrix(1, 0, "2");
    }

    @Test
    public void canSetSuccessMessage() {
        defaultInput();
        viewModel.processingInputMatrices();

        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyMultiplyWhenAllFieldsAreFill() {
        defaultInput();
        viewModel.parseInputMatrices();

        assertEquals(Status.READY_MULTIPLY, viewModel.getStatus());
    }

    private void defaultInput() {
        fillColsRowsFields1x2And2x1();
        viewModel.processingInputColsRows();
        fillMatrix1x2And2x1();
    }

    @Test
    public void checkGettersAndSettersForRowsAndCols() {
        fillColsRowsFields1x2And2x1();
        viewModel.processingInputColsRows();

        boolean success = false;
        if (viewModel.getRows1() == 1 && viewModel.getCols1() == 2
           && viewModel.getRows2() == 2 && viewModel.getCols2() == 1) {
            success = true;
        }
        assertEquals(true, success);
    }
}
