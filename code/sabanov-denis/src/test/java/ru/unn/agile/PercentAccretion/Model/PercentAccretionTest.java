package ru.unn.agile.PercentAccretion.Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PercentAccretionTest {

    private PercentData data;

    @Before
    public void initializePercentData() {
        data = new PercentData();
        data.setInitialSum(100);
        data.setPercentRate(50);
        data.setCountOfYears(1);
        data.setPercentPayingPerYear(0);
    }

    @Test
    public void canCalculateSumWithSimplePercent() {
        double expectedSum = 150.0;
        double actualSum = PercentAccretion.calculateSumWithSimplePercent(data);
        double delta = 0.00001;
        assertEquals(expectedSum, actualSum, delta);
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchExceptionWithNormalPercentRateSumWithNULL() {
        PercentAccretion.calculateSumWithNominalPercentRate(data);
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchExceptionWithEffectivePercentRateSumWithNULL() {
        PercentAccretion.calculateSumWithEffectivePercentRate(data);
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchExceptionWithEffectivePercentRateWithNULL() {
        PercentAccretion.calculateEffectivePercentRate(data);
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchExceptionWhenWrongArgInSimplePercent() {
        data.setPercentRate(-1);
        PercentAccretion.calculateSumWithSimplePercent(data);
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchExceptionWhenWrongArgInComplexPercent() {
        data.setPercentRate(-1);
        PercentAccretion.calculateSumWithComplexPercent(data);
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchExceptionWhenWrongArgsInNominalPercent() {
        data.setPercentRate(-1);
        PercentAccretion.calculateSumWithNominalPercentRate(data);
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchExceptionWhenWrongArgsInEffectivePercentRate() {
        data.setPercentRate(-1);
        PercentAccretion.calculateEffectivePercentRate(data);
    }

    @Test (expected = IllegalArgumentException.class)
    public void catchExceptionWhenWrongArgsEffectivePercentSum() {
        data.setPercentRate(-1);
        PercentAccretion.calculateSumWithEffectivePercentRate(data);
    }

    @Test
    public void canCalculateSumWithComplexPercent() {
        double expectedValue = 150.0;
        double actualValue = PercentAccretion.calculateSumWithComplexPercent(data);
        double delta = 0.00001;
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void canCalculateSumWithNominalRate() {
        double expectedValue = 150.0;
        data.setPercentPayingPerYear(1);
        double actualValue = PercentAccretion.calculateSumWithNominalPercentRate(data);
        double delta = 0.1;
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void canCalculateEffectiveRate() {
        double expectedValue = 50.0;
        data.setPercentPayingPerYear(1);
        double actualValue = PercentAccretion.calculateEffectivePercentRate(data);
        double delta = 0.01;
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void canCalculateSumWithEffectiveRate() {
        double expectedValue = 150.0;
        data.setPercentPayingPerYear(1);
        double actualValue = PercentAccretion.calculateSumWithEffectivePercentRate(data);
        double delta = 0.1;
        assertEquals(expectedValue, actualValue, delta);
    }

    @Test
    public void compareNominalAndEffectiveSum() {
        double delta = 0.0001;
        data.setPercentPayingPerYear(1);
        assertEquals(PercentAccretion.calculateSumWithNominalPercentRate(data),
                PercentAccretion.calculateSumWithEffectivePercentRate(data), delta);
    }
}
