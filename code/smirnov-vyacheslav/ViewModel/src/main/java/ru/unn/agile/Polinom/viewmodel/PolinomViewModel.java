package ru.unn.agile.Polinom.viewmodel;

import javafx.beans.property.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.unn.agile.Polinom.Model.Polinom;

public class PolinomViewModel {
    public enum Operation {
        ADD {
            public Polinom apply(final Polinom first, final Polinom second) {
                first.add(second);
                return first;
            }
        },
        SUBSTRACT {
            public Polinom apply(final Polinom first, final Polinom second) {
                first.subtract(second);
                return first;
            }
        },
        MULTIPLY {
            public Polinom apply(final Polinom first, final Polinom second) {
                first.multiply(second);
                return first;
            }
        },
        DIVIDE {
            public Polinom apply(final Polinom first, final Polinom second) {
                first.divide(second);
                return first;
            }
        };
        public abstract Polinom apply(final Polinom first, final Polinom second);
    }

    public enum Errors {
        BAD_FORMAT("Incorrect Input"),
        EMPTY_FIELD("Set Polinoms"),
        DIVIDE_BY_ZERO("Divider can't be zero!"),
        DIVIDE_BY_LARGE_DEGREE("Divider's degree can't be large than dividend's!"),
        UNKNOWN_EXCEPTION("Unknown exception");

        private String message;

        Errors(final String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    private final StringProperty firstOperand = new SimpleStringProperty();
    private final StringProperty secondOperand = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    public PolinomViewModel() {
        firstOperand.set("");
        secondOperand.set("");
        result.set("");
    }

    public void operation(final Operation operation) {
        if (firstOperand.get().isEmpty() || secondOperand.get().isEmpty()) {
            result.set(Errors.EMPTY_FIELD.getMessage());
            return;
        }

        Polinom first = convertIntoPolinom(firstOperand.get());
        Polinom second = convertIntoPolinom(secondOperand.get());

        if (first == null || second == null) {
            result.set(Errors.BAD_FORMAT.getMessage());
            return;
        }

        try {
            setResult(operation.apply(first, second));
        } catch (IllegalArgumentException iae) {
            Errors error = getErrorByStringValue(iae.getMessage());
            if (error == null) {
                result.set(Errors.UNKNOWN_EXCEPTION.getMessage());
            } else {
                result.set(error.getMessage());
            }
        }
    }

    public StringProperty firstOperandProperty() {
        return firstOperand;
    }

    public StringProperty secondOperandProperty() {
        return secondOperand;
    }
    public StringProperty resultProperty() {
        return result;
    }
    public final String getResult() {
        return result.get();
    }

    private Polinom convertIntoPolinom(final String input) {
        Matcher matchedPolinomString = Pattern.
        compile("((^| *[+-] *)\\d+(\\.\\d+)?(x\\^\\d+)?)+ *").matcher(input);

        if (!matchedPolinomString.matches()) {
            return null;
        }

        Polinom operand = new Polinom();
        Matcher matchedMonomString = Pattern.
        compile("([+-] *)?\\d+(\\.\\d+)?(x\\^\\d+)?").matcher(input);
        while (matchedMonomString.find()) {
            int monomDegree = 0;
            String[] monom = matchedMonomString.group().split("x\\^");
            if (monom.length > 1) {
                monomDegree = Integer.parseInt(monom[1]);
            }
            double[] monomCoefficients = new double[monomDegree + 1];
            monomCoefficients[monomDegree] = Double.parseDouble(monom[0].replace(" ", ""));
            operand.add(new Polinom(monomCoefficients));
        }
        return operand;
    }

    private void setResult(final Polinom result) {
        double[] coefficients = result.getCoefficients();
        String resultPolinom = "";
        for (int i = coefficients.length - 1; i >= 0; i--) {
            resultPolinom += coefficients[i] + "x^" + String.valueOf(i) + " ";
        }

        this.result.set(resultPolinom.trim().replace(" -", "-").replace(" ", "+").
            replaceAll("\\+0\\.0x\\^\\d+", "").replace("x^0", ""));
    }

    private Errors getErrorByStringValue(final String errorMessage) {
        for (Errors error : Errors.values()) {
            if (error.getMessage() == errorMessage) {
                return error;
            }
        }
        return null;
    }
}
