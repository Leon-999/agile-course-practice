package ru.unn.agile.IntersectionOfSegments.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import ru.unn.agile.IntersectionOfSegments.model.Intersection;
import ru.unn.agile.IntersectionOfSegments.model.Point;
import ru.unn.agile.IntersectionOfSegments.model.Segment;
import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty seg1Point1X = new SimpleStringProperty();
    private final StringProperty seg1Point1Y = new SimpleStringProperty();
    private final StringProperty seg1Point2X = new SimpleStringProperty();
    private final StringProperty seg1Point2Y = new SimpleStringProperty();

    private final StringProperty seg2Point1X = new SimpleStringProperty();
    private final StringProperty seg2Point1Y = new SimpleStringProperty();
    private final StringProperty seg2Point2X = new SimpleStringProperty();
    private final StringProperty seg2Point2Y = new SimpleStringProperty();

    private final StringConverter<Double> converter = new DoubleStringConverter();

    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final List<StringProperty> fields = new ArrayList<StringProperty>() { {
        add(seg1Point1X);
        add(seg1Point1Y);
        add(seg1Point2X);
        add(seg1Point2Y);

        add(seg2Point1X);
        add(seg2Point1Y);
        add(seg2Point2X);
        add(seg2Point2Y);
    } };

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        for (StringProperty field : fields) {
            field.set("");
        }

        result.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldFind = new BooleanBinding() {
            {
                super.bind(seg1Point1X, seg1Point1Y, seg1Point2X, seg1Point2Y,
                        seg2Point1X, seg2Point1Y, seg2Point2X, seg2Point2Y);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldFind.not());

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void calculate() {
        Segment segment1 = new Segment(getPoint(seg1Point1X, seg1Point1Y),
                getPoint(seg1Point2X, seg1Point2Y));
        Segment segment2 = new Segment(getPoint(seg2Point1X, seg2Point1Y),
                getPoint(seg2Point2X, seg2Point2Y));

        setStringResult(segment1.isIntersectedWith(segment2));

        status.set(Status.SUCCESS.toString());
    }

    private Point getPoint(final StringProperty x, final StringProperty y) {
        return new Point(converter.fromString(x.get()), converter.fromString(y.get()));
    }

    private void setStringResult(final Intersection intersection) {
        switch (intersection.getTypeOfIntersection()) {
            case NotIntersection:
                result.set("Segments not intersection.");
                break;
            case IntersectionInOnePoint:
                result.set("Segments intersection in one point "
                        + intersection.getSegment().getStart().toString()
                        + ".");
                break;
            case OnePartOfOther:
            case SegmentsHaveCommonPart:
                result.set("Segments have common part ["
                        + intersection.getSegment().getStart().toString()
                        + "; " + intersection.getSegment().getFinish().toString()
                        + "].\nLength = "
                        + Double.toString(intersection.getSegment().getLengthSegment()));
                break;
            default:
                break;
        }
    }

    public StringProperty seg1Point1XProperty() {
        return seg1Point1X;
    }

    public StringProperty seg1Point1YProperty() {
        return seg1Point1Y;
    }

    public StringProperty seg1Point2XProperty() {
        return seg1Point2X;
    }

    public StringProperty seg1Point2YProperty() {
        return seg1Point2Y;
    }

    public StringProperty seg2Point1XProperty() {
        return seg2Point1X;
    }

    public StringProperty seg2Point1YProperty() {
        return seg2Point1Y;
    }

    public StringProperty seg2Point2XProperty() {
        return seg2Point2X;
    }

    public StringProperty seg2Point2YProperty() {
        return seg2Point2Y;
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public final boolean getCalculationDisabled() {
        return calculationDisabled.get();
    }

    public StringProperty resultProperty() {
        return result;
    }

    public final String getResult() {
        return result.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public final String getStatus() {
        return status.get();
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;
        try {
            for (StringProperty field : fields) {
                if (field.get().isEmpty()) {
                    inputStatus = Status.WAITING;
                }
                converter.fromString(field.get());
            }
        } catch (Exception e) {
            inputStatus = Status.BAD_FORMAT;
        }
        return inputStatus;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }
}

enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Find of intersection' or Enter"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    private Status(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
