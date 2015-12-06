package ru.unn.agile.LongArithmetic.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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

        viewModel.processKeyInTextField(KeyboardKeys.ANY);

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
        viewModel.multiplyMatrices();

        assertEquals(Status.WAITINGMN, viewModel.getStatus());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.setMFirstMatrix("a");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void canCleanStatusIfParseIsOK() {
        viewModel.setMFirstMatrix("a");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);
        viewModel.setMFirstMatrix("1");
        viewModel.processKeyInTextField(KeyboardKeys.ANY);

        assertEquals(Status.WAITINGMN, viewModel.getStatus());
    }

}
