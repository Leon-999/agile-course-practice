package ru.unn.agile.Vec3.View;

public enum ActionList {
    GET_NORM_FIRST_VECTOR("Get norm first vector", 0),
    GET_NORM_SECOND_VECTOR("Get norm second vector", 1),
    NORMAlIZE_FIRST_VECTOR("Normalize first vector", 2),
    NORMALIZE_SECOND_VECTOR("Normalize second vector", 3),
    CALCULATE_DOT_PRODUCT("Calculate dot product", 4),
    CALCULATE_CROSS_PRODUCT("Calculate cross product", 5);

    private final String actionName;
    private final int index;

    ActionList(final String actionName,
               final int index) {
        this.actionName = actionName;
        this.index = index;
    }

    public String toString() {
        return actionName;
    }

    public final int getIndex() {
        return index;
    }
}
