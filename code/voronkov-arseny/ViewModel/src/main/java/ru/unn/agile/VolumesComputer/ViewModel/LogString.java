package ru.unn.agile.VolumesComputer.ViewModel;

public enum LogString {
    SOLVE_BAD_FIGURE("Solving failed. Reason: incorrect figure type."),
    SOLVE_BAD_PARAMETERS("Solving failed. Reason: negative parameters."),
    SOLVE_BAD_PARSE("Solving failed. Reason: unparsed parameters."),
    SOLVE("Solving success. Volume is %s."),
    SET_PARAMETER("Set %s to %s."),
    SET_FIGURE("Set current figure to %s.");

    private final String message;
    LogString(final String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return message;
    }
}
