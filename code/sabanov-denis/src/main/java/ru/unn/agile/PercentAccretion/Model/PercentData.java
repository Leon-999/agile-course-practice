package ru.unn.agile.PercentAccretion.Model;

public class PercentData {
    private double initialSum;
    private double percentRate;
    private int percentPayingPerYear;
    private int countOfYears;

    public PercentData() {
        initialSum = 0;
        percentRate = 0;
        percentPayingPerYear = 0;
        countOfYears = 0;
    }

    public void setInitialSum(final double newInitialSum) {
        initialSum = newInitialSum;
    }

    public void setPercentRate(final double newPercentRate) {
        percentRate = newPercentRate;
    }

    public void setPercentPayingPerYear(final int newPercentPayingPerYear) {
        percentPayingPerYear = newPercentPayingPerYear;
    }

    public void setCountOfYears(final int newCountOfYears) {
        countOfYears = newCountOfYears;
    }

    public double getInitialSum() {
        return initialSum;
    }

    public double getPercentRate() {
        return percentRate;
    }

    public int getPercentPayingPerYear() {
        return percentPayingPerYear;
    }

    public int getCountOfYears() {
        return countOfYears;
    }
}
