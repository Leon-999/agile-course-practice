package ru.unn.agile.VolumesComputer.Model;

public final class VolumesComputer {
    private static final String NEGATIVE_PARAMS_ERROR =
            "Parameters must not be negative.";

    public static double cuboid(final double width,
                                final double height,
                                final double length) {
        if (width >= 0.0 && height >= 0.0 && length >= 0.0) {
            return width * height * length;
        } else {
            throw new IllegalArgumentException(NEGATIVE_PARAMS_ERROR);
        }
    }
    public static double spheroid(final double semimajorAxis,
                                  final double semiminorAxis) {
        if (semimajorAxis >= 0.0 && semiminorAxis >= 0.0) {
            final double four = 4.0;
            final double three = 3.0;
            return four / three * Math.PI * semimajorAxis
                    * semiminorAxis * semiminorAxis;
        } else {
            throw new IllegalArgumentException(NEGATIVE_PARAMS_ERROR);
        }
    }
    public static double rightCylinder(final double semimajorAxis,
                                       final double semiminorAxis,
                                       final double height) {
        if (semimajorAxis >= 0.0 && semiminorAxis >= 0.0 && height >= 0.0) {
            return Math.PI * semimajorAxis * semiminorAxis * height;
        } else {
            throw new IllegalArgumentException(NEGATIVE_PARAMS_ERROR);
        }
    }
    public static double rightCircularCone(final double radius,
                                           final double height) {
        if (radius >= 0.0 && height >= 0.0) {
            final double three = 3.0;
            return Math.PI / three * radius * radius * height;
        } else {
            throw new IllegalArgumentException(NEGATIVE_PARAMS_ERROR);
        }
    }
    private VolumesComputer() {
    }
}
