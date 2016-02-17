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
        assertEquals("", viewModel.getHeightFirstMatrixStr());
        assertEquals("", viewModel.getWidthFirstMatrixStr());
        assertEquals("", viewModel.getHeightSecondMatrixStr());
        assertEquals("", viewModel.getWidthSecondMatrixStr());
        assertEquals(Status.WAITING_COLS_AND_ROWS, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingColsRowsInTheBeginning() {
        assertEquals(Status.WAITING_COLS_AND_ROWS, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyOkWhenFieldsColsRowsAreFill() {
        fillColsRowsFields();

        viewModel.parseInputMatrixSizes();

        assertEquals(Status.READY_OK, viewModel.getStatus());
    }

    private void fillColsRowsFields() {
        viewModel.setHeightFirstMatrixStr("1");
        viewModel.setWidthFirstMatrixStr("1");
        viewModel.setHeightSecondMatrixStr("1");
        viewModel.setWidthSecondMatrixStr("1");
    }


    @Test
    public void isStatusWaitingWhenMultiplyWithEmptyFieldsColsRows() {
        viewModel.processingInputMatrices();

        assertEquals(Status.WAITING_COLS_AND_ROWS, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.setHeightFirstMatrixStr("a");
        viewModel.processingInputMatrixSizes();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.setHeightFirstMatrixStr("a");
        viewModel.processingInputMatrixSizes();
        viewModel.setHeightFirstMatrixStr("1");
        viewModel.processingInputMatrixSizes();

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
        viewModel.processingInputMatrixSizes();
        assertEquals(true, viewModel.isOkButtonEnabled());

        viewModel.setHeightFirstMatrixStr("some");
        viewModel.processingInputMatrixSizes();

        assertEquals(false, viewModel.isOkButtonEnabled());
    }

    @Test
    public void isOkButtonDisabledWithIncompleteInput() {
        viewModel.setHeightFirstMatrixStr("1");
        viewModel.setWidthFirstMatrixStr("1");

        viewModel.processingInputMatrixSizes();

        assertEquals(false, viewModel.isOkButtonEnabled());
    }

    @Test
    public void isOkButtonEnabledWithCorrectInput() {
        fillColsRowsFields();

        viewModel.processingInputMatrixSizes();

        assertEquals(true, viewModel.isOkButtonEnabled());
    }

    @Test
    public void canPerformMultiplyAction() {
        fillColsRowsFields();
        viewModel.processingInputMatrixSizes();
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
        viewModel.setHeightFirstMatrixStr("1");
        viewModel.setWidthFirstMatrixStr("2");
        viewModel.setHeightSecondMatrixStr("2");
        viewModel.setWidthSecondMatrixStr("1");
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
        viewModel.processingInputMatrixSizes();
        fillMatrix1x2And2x1();
    }

    @Test
    public void checkGettersAndSettersForRowsAndCols() {
        fillColsRowsFields1x2And2x1();
        viewModel.processingInputMatrixSizes();

        boolean success = false;
        if (viewModel.getHeightFirstMatrix() == 1 && viewModel.getWidthFirstMatrix() == 2
           && viewModel.getHeightSecondMatrix() == 2 && viewModel.getWidthSecondMatrix() == 1) {
            success = true;
        }
        assertEquals(true, success);
    }
}
