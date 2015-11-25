package ru.unn.agile.Vec3.ViewModel;

import ru.unn.agile.Vec3.Model.Vector3;
import org.junit.Test;
import ru.unn.agile.Vec3.Model.Vector3Status;

import static org.junit.Assert.assertEquals;

public class Vector3ViewModelTest {
    private Vector3ViewModel viewModel = new Vector3ViewModel();

    @Test
    public void canGetNormOfDefaultFirstVector() {
        viewModel.getNormOfFirstVector();

        assertEquals(viewModel.getResultOfLastAction(), "0.0");
    }

    @Test
    public void canGetNormOfDefaultSecondVector() {
        viewModel.getNormOfSecondVector();

        assertEquals(viewModel.getResultOfLastAction(), "0.0");
    }

    @Test
    public void cannotNormalizeOfDefaultFirstVector() {
        viewModel.normalizeFirstVector();

        assertEquals(viewModel.getResultOfLastAction(), Vector3Status.ERROR_NORMALIZE);
    }

    @Test
    public void cannotNormalizeOfDefaultSecondVector() {
        viewModel.normalizeSecondVector();

        assertEquals(viewModel.getResultOfLastAction(), Vector3Status.ERROR_NORMALIZE);
    }

    @Test
    public void canGetNormOfSpecificFirstVector() {
        Vector3 vector = new Vector3(1.0, 2.0, 3.0);
        double norm = vector.getNorm();

        setFirstVectorInViewModel(vector);

        viewModel.getNormOfFirstVector();

        assertEquals(viewModel.getResultOfLastAction(), Double.toString(norm));
    }

    @Test
    public void canGetNormOfSpecificSecondVector() {
        Vector3 vector = new Vector3(1.0, 2.0, 3.0);
        double  norm   = vector.getNorm();

        setSecondVectorInViewModel(vector);

        viewModel.getNormOfSecondVector();

        assertEquals(viewModel.getResultOfLastAction(), Double.toString(norm));
    }

    @Test
    public void canNormalizeSpecificFirstVector() {
        Vector3 vector = new Vector3(1.0, 2.0, 3.0);

        setFirstVectorInViewModel(vector);

        viewModel.normalizeFirstVector();
        vector.normalize();

        assertEquals(viewModel.getResultOfLastAction(), vector.toString());
    }

    @Test
    public void canNormalizeSpecificSecondVector() {
        Vector3 vector = new Vector3(1.0, 2.0, 3.0);

        setSecondVectorInViewModel(vector);

        viewModel.normalizeSecondVector();
        vector.normalize();

        assertEquals(viewModel.getResultOfLastAction(), vector.toString());
    }

    @Test
    public void canCalculateDotProduct() {
        Vector3 firstVector  = new Vector3(1.0, 2.0, 3.0);
        Vector3 secondVector = new Vector3(3.0, 2.0, 1.0);

        setFirstVectorInViewModel(firstVector);
        setSecondVectorInViewModel(secondVector);

        viewModel.getDotProduct();
        double dot = firstVector.dot(secondVector);

        assertEquals(viewModel.getResultOfLastAction(), Double.toString(dot));
    }

    @Test
    public void getErrorByCalculateCrossProductWithDefaultVectors() {
        viewModel.getCrossProduct();

        assertEquals(viewModel.getResultOfLastAction(), Vector3Status.ERROR_NORMALIZE);
    }

    @Test
    public void canCalculateCrossProductFromSpecificVectors() {
        Vector3 firstVector  = new Vector3(1.0, 0.0, 0.0);
        Vector3 secondVector = new Vector3(0.0, 1.0, 0.0);

        setFirstVectorInViewModel(firstVector);
        setSecondVectorInViewModel(secondVector);

        viewModel.getCrossProduct();
        Vector3 crossProduct = firstVector.cross(secondVector);

        assertEquals(viewModel.getResultOfLastAction(), crossProduct.toString());
    }

    @Test
    public void isCorrectFirstComponentOfFistVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordX0(value);

        assertEquals(viewModel.getCoordX0(), value);
    }

    @Test
    public void isCorrectSecondComponentOfFirstVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordY0(value);

        assertEquals(viewModel.getCoordY0(), value);
    }

    @Test
    public void isCorrectThirdComponentOfFirstVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordZ0(value);

        assertEquals(viewModel.getCoordZ0(), value);
    }

    @Test
    public void isCorrectFirstComponentOfSecondVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordX1(value);

        assertEquals(viewModel.getCoordX1(), value);
    }

    @Test
    public void isCorrectSecondComponentOfSecondVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordY1(value);

        assertEquals(viewModel.getCoordY1(), value);
    }

    @Test
    public void isCorrectThirdComponentOfSecondVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordZ1(value);

        assertEquals(viewModel.getCoordZ1(), value);
    }

    @Test
    public void isCorrectFirstComponentOfFirstVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordX0(value);

        assertEquals(viewModel.getCoordX0AsDouble(), value, Double.MIN_VALUE);
    }

    @Test
    public void isCorrectSecondComponentOfFirstVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordY0(value);

        assertEquals(viewModel.getCoordY0AsDouble(), value, Double.MIN_VALUE);
    }

    @Test
    public void isCorrectThirdComponentOfFirstVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordZ0(value);

        assertEquals(viewModel.getCoordZ0AsDouble(), value, Double.MIN_VALUE);
    }

    @Test
    public void isCorrectFirstComponentOfSecondVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordX1(value);

        assertEquals(viewModel.getCoordX1AsDouble(), value, Double.MIN_VALUE);
    }

    @Test
    public void isCorrectSecondComponentOfSecondVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordY1(value);

        assertEquals(viewModel.getCoordY1AsDouble(), value, Double.MIN_VALUE);
    }

    @Test
    public void isCorrectThirdComponentOfSecondVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordZ1(value);

        assertEquals(viewModel.getCoordZ1AsDouble(), value, Double.MIN_VALUE);
    }

    @Test
    public void canProcessIncorrectValueFirstComponentOfFirstVector() {
        String correctValue = "666.0";

        viewModel.setCoordX0(correctValue);
        viewModel.setCoordX0("Ave satan!");

        assertEquals(viewModel.getCoordX0(), correctValue);
    }

    @Test
    public void canProcessIncorrectValueSecondComponentOfFirstVector() {
        String correctValue = "5.2";

        viewModel.setCoordY0(correctValue);
        viewModel.setCoordY0("Release me!");

        assertEquals(viewModel.getCoordY0(), correctValue);
    }

    @Test
    public void canProcessIncorrectValueThirdComponentOfFirstVector() {
        String correctValue = "-8000.0";

        viewModel.setCoordZ0(correctValue);
        viewModel.setCoordZ0("It is better to die for the Emperor than to live for yourself!");

        assertEquals(viewModel.getCoordZ0(), correctValue);
    }

    @Test
    public void canProcessIncorrectValueFirstComponentOfSecondVector() {
        String correctValue = "19.0";

        viewModel.setCoordX1(correctValue);
        viewModel.setCoordX1("Nevermore!");

        assertEquals(viewModel.getCoordX1(), correctValue);
    }

    @Test
    public void canProcessIncorrectValueSecondComponentOfSecondVector() {
        String correctValue = "2027.0";

        viewModel.setCoordY1(correctValue);
        viewModel.setCoordY1("I never asked for this!");

        assertEquals(viewModel.getCoordY1(), correctValue);
    }

    @Test
    public void canProcessIncorrectValueThirdComponentOfSecondVector() {
        String correctValue = "666.0";

        viewModel.setCoordZ1(correctValue);
        viewModel.setCoordZ1("WHERE IS THE TRIGGER?");

        assertEquals(viewModel.getCoordZ1(), correctValue);
    }

    private void setFirstVectorInViewModel(final Vector3 vector) {
        viewModel.setCoordX0(vector.x());
        viewModel.setCoordY0(vector.y());
        viewModel.setCoordZ0(vector.z());
    }

    private void setSecondVectorInViewModel(final Vector3 vector) {
        viewModel.setCoordX1(vector.x());
        viewModel.setCoordY1(vector.y());
        viewModel.setCoordZ1(vector.z());
    }
}
