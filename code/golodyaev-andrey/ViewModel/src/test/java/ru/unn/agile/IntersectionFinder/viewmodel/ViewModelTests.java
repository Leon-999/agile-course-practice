package ru.unn.agile.IntersectionFinder.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    public void canSetDefaultPointLine() {
        assertEquals("", viewModel.getPointLine());
    }

    @Test
    public void canSetDefaultVectorLine() {
        assertEquals("", viewModel.getVectorLine());
    }

    @Test
    public void canSetDefaultPointPlane() {
        assertEquals("", viewModel.getPointPlane());
    }

    @Test
    public void canSetDefaultNormalPlane() {
        assertEquals("", viewModel.getNormalPlane());
    }

    @Test
    public void canSetDefaultResult() {
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void canSetDefaultError() {
        assertEquals(ViewModel.ErrorStatus.EMPTY_FIELDS.toString(), viewModel.getError());
    }

    @Test
    public void isFinderButtonDisabledInTheBeginning() {
        assertFalse(viewModel.isFinderButtonEnabled());
    }

    @Test
    public void errorTextIsEmptyWhenFieldsContainCorrectData() {
        fillCorrectInputFields();

        viewModel.parseInput();

        assertEquals("", viewModel.getError());
    }

    @Test
    public void finderButtonIsEnabledWhenFieldsContainCorrectData() {
        fillCorrectInputFields();

        viewModel.parseInput();

        assertTrue(viewModel.isFinderButtonEnabled());
    }

    @Test
    public void checkErrorTextWhenFieldsAreEmpty() {
        fillEmptyInputFields();

        viewModel.parseInput();

        assertEquals(ViewModel.ErrorStatus.EMPTY_FIELDS.toString(), viewModel.getError());
    }

    @Test
    public void finderButtonIsDisabledWhenFieldsContainCorrectData() {
        fillEmptyInputFields();

        viewModel.parseInput();

        assertFalse(viewModel.isFinderButtonEnabled());
    }

    @Test
    public void checkErrorTextWhenFieldsContainIncorrectData() {
        fillCorrectInputFields();
        viewModel.setPointPlane("1; 2; a");

        viewModel.parseInput();

        assertEquals(ViewModel.ErrorStatus.INCORRECT_DATA.toString(), viewModel.getError());
    }

    @Test
    public void finderButtonIsDisabledWhenFieldsContainIncorrectData() {
        fillCorrectInputFields();
        viewModel.setPointPlane("1; 2; a");

        viewModel.parseInput();

        assertFalse(viewModel.isFinderButtonEnabled());
    }

    @Test
    public void checkErrorTextWhenOneIntersection() {
        fillCorrectInputFields();

        viewModel.findIntersection();

        assertEquals(ViewModel.ErrorStatus.NO_ERROR.toString(), viewModel.getError());
    }

    @Test
    public void checkErrorTextWhenNoIntersection() {
        fillInputFieldsNoIntersection();

        viewModel.findIntersection();

        assertEquals(ViewModel.ErrorStatus.NO_INTERSECTION.toString(), viewModel.getError());
    }

    @Test
    public void checkErrorTextWhenPlaneContainsLine() {
        fillInputFieldsLineOnThePlane();

        viewModel.findIntersection();

        assertEquals(ViewModel.ErrorStatus.PLANE_CONTAINS_LINE.toString(), viewModel.getError());
    }

    @Test
    public void resultIsCorrectWhenOneIntersection() {
        fillCorrectInputFields();

        viewModel.findIntersection();

        assertEquals("0.0; 0.0; 0.0", viewModel.getResult());
    }

    @Test
    public void resultIsEmptyWhenNoIntersection() {
        fillCorrectInputFields();
        viewModel.findIntersection();
        fillInputFieldsNoIntersection();

        viewModel.findIntersection();

        assertEquals("", viewModel.getResult());
    }

    @Test
    public void resultIsEmptyWhenPlaneContainsLine() {
        fillCorrectInputFields();
        viewModel.findIntersection();
        fillInputFieldsLineOnThePlane();

        viewModel.findIntersection();

        assertEquals("", viewModel.getResult());
    }

    private void fillEmptyInputFields() {
        viewModel.setPointLine("");
        viewModel.setVectorLine("");
        viewModel.setPointPlane("");
        viewModel.setNormalPlane("");
    }

    private void fillCorrectInputFields() {
        viewModel.setPointLine("0; 0; 0");
        viewModel.setVectorLine("0; 0; 1");
        viewModel.setPointPlane("0; 0; 0");
        viewModel.setNormalPlane("0; 0; 1");
    }

    private void fillInputFieldsNoIntersection() {
        viewModel.setPointLine("0; 0; 1");
        viewModel.setVectorLine("0; 1; 0");
        viewModel.setPointPlane("0; 0; 0");
        viewModel.setNormalPlane("0; 0; 1");
    }

    private void fillInputFieldsLineOnThePlane() {
        viewModel.setPointLine("0; 0; 0");
        viewModel.setVectorLine("0; 1; 0");
        viewModel.setPointPlane("0; 0; 0");
        viewModel.setNormalPlane("0; 0; 1");
    }
}
