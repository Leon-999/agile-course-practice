package ru.unn.agile.IntersectionFinder.viewmodel;

import ru.unn.agile.IntersectionFinder.model.*;

public class ViewModel {
    private String pointLineText;
    private String vectorLineText;
    private String pointPlaneText;
    private String normalPlaneText;
    private String result;
    private String error;
    private boolean isFinderButtonEnabled;

    public ViewModel() {
        pointLineText = "";
        vectorLineText = "";
        pointPlaneText = "";
        normalPlaneText = "";
        result = "";
        error = ErrorStatus.EMPTY_FIELDS.toString();
        isFinderButtonEnabled = false;
    }

    public boolean isFinderButtonEnabled() {
        return isFinderButtonEnabled;
    }

    private boolean isInputEmpty() {
        return pointLineText.isEmpty() || vectorLineText.isEmpty()
                || pointPlaneText.isEmpty() || normalPlaneText.isEmpty();
    }

    private boolean isInputContainsIllegalData() {
        try {
            Vector3D tmpVector = new Vector3D(0.0, 0.0, 0.0);
            if (!pointLineText.isEmpty()) {
                tmpVector.parse(pointLineText);
            }
            if (!vectorLineText.isEmpty()) {
                tmpVector.parse(vectorLineText);
            }
            if (!pointPlaneText.isEmpty()) {
                tmpVector.parse(pointPlaneText);
            }
            if (!normalPlaneText.isEmpty()) {
                tmpVector.parse(normalPlaneText);
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public boolean parseInput() {
        if (isInputContainsIllegalData()) {
            isFinderButtonEnabled = false;
            error = ErrorStatus.INCORRECT_DATA.toString();
            return false;
        }
        if (isInputEmpty()) {
            isFinderButtonEnabled = false;
            error = ErrorStatus.EMPTY_FIELDS.toString();
            return false;
        }
        isFinderButtonEnabled = true;
        error = ErrorStatus.NO_ERROR.toString();
        return true;
    }

    public void findIntersection() {
        Vector3D pointLine = new Vector3D(pointLineText);
        Vector3D vectorLine = new Vector3D(vectorLineText);
        Vector3D pointPlane = new Vector3D(pointPlaneText);
        Vector3D normalPlane = new Vector3D(normalPlaneText);
        Line line = new Line(pointLine, vectorLine);
        Plane plane = new Plane(pointPlane, normalPlane);
        IntersectionFinder intersectionFinder = new IntersectionFinder(line, plane);

        IntersectionFinder.TypeOfIntersection type = intersectionFinder.getTypeOfIntersection();
        switch (type) {
            case NoIntersection:
                error = ErrorStatus.NO_INTERSECTION.toString();
                result = "";
                break;
            case LineOnThePlane:
                error = ErrorStatus.PLANE_CONTAINS_LINE.toString();
                result = "";
                break;
            case OneIntersection:
                error = ErrorStatus.NO_ERROR.toString();
                result = intersectionFinder.getIntersectionPoint().toString();
                break;
            default:
                break;
        }
    }

    public String getError() {
        return error;
    }

    public String getResult() {
        return result;
    }

    public String getPointLine() {
        return pointLineText;
    }

    public void setPointLine(final String pointLineText) {
        if (pointLineText.equals(this.pointLineText)) {
            return;
        }

        this.pointLineText = pointLineText;
    }

    public String getVectorLine() {
        return vectorLineText;
    }

    public void setVectorLine(final String vectorLineText) {
        if (vectorLineText.equals(this.vectorLineText)) {
            return;
        }

        this.vectorLineText = vectorLineText;
    }

    public String getPointPlane() {
        return pointPlaneText;
    }

    public void setPointPlane(final String pointPlaneText) {
        if (pointPlaneText.equals(this.pointPlaneText)) {
            return;
        }

        this.pointPlaneText = pointPlaneText;
    }

    public String getNormalPlane() {
        return normalPlaneText;
    }

    public void setNormalPlane(final String normalPlane) {
        if (normalPlane.equals(this.normalPlaneText)) {
            return;
        }

        this.normalPlaneText = normalPlane;
    }

    enum ErrorStatus {
        NO_ERROR(""),
        NO_INTERSECTION("No Intersection!"),
        PLANE_CONTAINS_LINE("Plane contains line!"),
        EMPTY_FIELDS("Empty fields! Format a;b;c"),
        INCORRECT_DATA("Incorrect data! Format a;b;c");

        private final String name;

        private ErrorStatus(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
