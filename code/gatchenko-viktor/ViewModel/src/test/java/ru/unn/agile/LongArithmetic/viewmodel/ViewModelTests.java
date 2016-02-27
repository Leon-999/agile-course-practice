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
        assertEquals(ViewModel.UNDEFINED_SIZE, viewModel.getHeightFirstMatrix());
        assertEquals(ViewModel.UNDEFINED_SIZE, viewModel.getWidthFirstMatrix());
        assertEquals(ViewModel.UNDEFINED_SIZE, viewModel.getHeightSecondMatrix());
        assertEquals(ViewModel.UNDEFINED_SIZE, viewModel.getWidthSecondMatrix());
        assertEquals(Status.WAITING_WIDTH_AND_HEIGHT_MATRICES, viewModel.getStatus());
    }

    @Test
    public void isStatusWaitingMatrixSizesInTheBeginning() {
        assertEquals(Status.WAITING_WIDTH_AND_HEIGHT_MATRICES, viewModel.getStatus());
    }

    @Test
    public void isStatusReadyOkWhenFieldsMatrixSizesAreFill() {
        fillMatrixSizesFields();
        viewModel.parseInputMatrixSizes();

        assertEquals(Status.READY_OK, viewModel.getStatus());
    }

    private void fillMatrixSizesFields() {
        viewModel.setHeightFirstMatrix("1");
        viewModel.setWidthFirstMatrix("1");
        viewModel.setHeightSecondMatrix("1");
        viewModel.setWidthSecondMatrix("1");
    }


    @Test
    public void isStatusWaitingWhenMultiplyWithEmptyFieldsMatrixSizes() {
        viewModel.processingInputMatrices();

        assertEquals(Status.WAITING_WIDTH_AND_HEIGHT_MATRICES, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.setHeightFirstMatrix("a");
        viewModel.processingInputMatrixSizes();

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.setHeightFirstMatrix("a");
        viewModel.processingInputMatrixSizes();
        viewModel.setHeightFirstMatrix("1");
        viewModel.processingInputMatrixSizes();

        assertEquals(Status.WAITING_WIDTH_AND_HEIGHT_MATRICES, viewModel.getStatus());
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
        fillMatrixSizesFields();
        viewModel.processingInputMatrixSizes();
        assertEquals(true, viewModel.isOkButtonEnabled());

        viewModel.setHeightFirstMatrix("some");
        viewModel.processingInputMatrixSizes();

        assertEquals(false, viewModel.isOkButtonEnabled());
    }

    @Test
    public void isOkButtonDisabledWithIncompleteInput() {
        viewModel.setHeightFirstMatrix("1");
        viewModel.setWidthFirstMatrix("1");
        viewModel.processingInputMatrixSizes();

        assertEquals(false, viewModel.isOkButtonEnabled());
    }

    @Test
    public void isOkButtonEnabledWithCorrectInput() {
        fillMatrixSizesFields();
        viewModel.processingInputMatrixSizes();

        assertEquals(true, viewModel.isOkButtonEnabled());
    }

    @Test
    public void canPerformMultiplyAction() {
        fillMatrixSizesFields();
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

    private void fillMatrixSizeFields1x2And2x1() {
        viewModel.setHeightFirstMatrix("1");
        viewModel.setWidthFirstMatrix("2");
        viewModel.setHeightSecondMatrix("2");
        viewModel.setWidthSecondMatrix("1");
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
        fillMatrixSizeFields1x2And2x1();
        viewModel.processingInputMatrixSizes();
        fillMatrix1x2And2x1();
    }

    @Test
    public void checkGettersAndSettersForMatrixSizes() {
        fillMatrixSizeFields1x2And2x1();
        viewModel.processingInputMatrixSizes();

        boolean success = false;
        if (viewModel.getHeightFirstMatrix() == 1 && viewModel.getWidthFirstMatrix() == 2
           && viewModel.getHeightSecondMatrix() == 2 && viewModel.getWidthSecondMatrix() == 1) {
            success = true;
        }
        assertEquals(true, success);
    }
}
