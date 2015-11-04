package ru.unn.agile.TemperatureConverter.Model;

public enum TemperatureScaleName {
    FAHRENHEIT("Fahrenheit", 32.0, 1.8),
    KELVIN("Kelvin", 273.0, 1.0),
    NEWTON("Newton", 0.0, 0.33);

    private final String name;
    private final double base;
    private final double scaling;

    TemperatureScaleName(final String name, final double base, final double scaling) {
        this.name = name;
        this.base = base;
        this.scaling = scaling;
    }

    public double getBase() {
        return base;
    }

    public double getScaling() {
        return scaling;
    }

    public String toString() {
        return name;
    }
}

