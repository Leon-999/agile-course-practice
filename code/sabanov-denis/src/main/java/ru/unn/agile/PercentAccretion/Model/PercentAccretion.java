package ru.unn.agile.PercentAccretion.Model;

public final class PercentAccretion {

    private static final double FROM_PERCENT = 0.01;
    private static final double TO_PERCENT = 100;

    private PercentAccretion() {
    }

    public static double calculateSumWithSimplePercent(final PercentData data) {
        if (data.getInitialSum() >= 0 && data.getPercentRate() >= 0
                && data.getCountOfYears() >= 0) {
            return data.getInitialSum() * (1 + data.getPercentRate()
                * FROM_PERCENT * data.getCountOfYears());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static double calculateSumWithComplexPercent(final PercentData data) {
        if (data.getInitialSum() >= 0 && data.getPercentRate() >= 0
                && data.getCountOfYears() >= 0) {
            return data.getInitialSum() * Math.pow(1 + data.getPercentRate()
                * FROM_PERCENT, data.getCountOfYears());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static double calculateSumWithNominalPercentRate(final PercentData data) {
        if (data.getInitialSum() >= 0 && data.getPercentRate() >= 0 && data.getCountOfYears() >= 0
                && data.getPercentPayingPerYear() > 0) {
            return data.getInitialSum() * Math.pow(1 + data.getPercentRate()
                    * FROM_PERCENT / data.getPercentPayingPerYear(),
                    data.getCountOfYears() * data.getPercentPayingPerYear());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static double calculateEffectivePercentRate(final PercentData data) {
        if (data.getPercentRate() >= 0 && data.getPercentPayingPerYear() > 0) {
            return TO_PERCENT * (Math.pow(1 + (data.getPercentRate()
                    * FROM_PERCENT) / data.getPercentPayingPerYear(),
                    data.getPercentPayingPerYear()) - 1);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static double calculateSumWithEffectivePercentRate(final PercentData percentData) {
        if (percentData.getInitialSum() >= 0 && percentData.getPercentRate() >= 0
                && percentData.getCountOfYears() >= 0
                && percentData.getPercentPayingPerYear() > 0) {
            return percentData.getInitialSum() * Math.pow(1 + FROM_PERCENT
                   * calculateEffectivePercentRate(percentData),
                    percentData.getCountOfYears());
        } else {
            throw new IllegalArgumentException();
        }
    }
}
