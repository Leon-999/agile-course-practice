package ru.unn.agile.LongArithmetic;

import java.util.Arrays;

public class Matrix {

    static final Matrix UNDEFINED = null;

    private LongNumber[][] element;

    private int width;
    private int height;

    public Matrix() {
        this.width = 0;
        this.height = 0;
        this.element = null;
    }

    public Matrix(final int height, final int width) {
        this.width = width;
        this.height = height;

        this.element = new LongNumber[height][width];
        for (int i = 0; i < height; ++i) {
            Arrays.fill(this.element[i], new LongNumber(0));
        }
    }

    public Matrix(final Matrix copiedMatrix) {
        this.width = copiedMatrix.width;
        this.height = copiedMatrix.height;

        this.element = new LongNumber[this.height][this.width];
        for (int i = 0; i < this.height; ++i) {
            System.arraycopy(copiedMatrix.element[i], 0, this.element[i], 0, this.width);
        }
    }

    public Matrix multiply(final Matrix secondMultiplier) {
        Matrix resultMatrix;

        if (this.width == secondMultiplier.height) {
            resultMatrix = new Matrix(this.height, secondMultiplier.width);

            for (int i = 0; i < resultMatrix.height; ++i) {
                for (int j = 0; j < resultMatrix.width; ++j) {
                    resultMatrix.element[i][j] = countUpElement(i, j, this, secondMultiplier);
                }
            }
        } else {
            resultMatrix = Matrix.UNDEFINED;
        }

        return resultMatrix;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public boolean equals(final Matrix matrix) {
        boolean result = true;

        if (this.height == matrix.height && this.width == matrix.width) {
            for (int i = 0; i < this.height; ++i) {
                for (int j = 0; j < this.width; ++j) {
                    if (!this.element[i][j].equals(matrix.element[i][j])) {
                        result = false;
                    }
                }
            }
        } else {
            result = false;
        }

        return result;
    }

    private LongNumber countUpElement(final int i, final int j, final Matrix firstMultiplier,
            final Matrix secondMultiplier) {
        LongNumber result = new LongNumber();
        LongNumber partSum;

        for (int w = 0; w < firstMultiplier.width; ++w) {
            partSum = firstMultiplier.element[i][w].multiply(secondMultiplier.element[w][j]);
            result = result.add(partSum);
        }

        return result;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public LongNumber getElement(final int i, final int j) {
        return this.element[i][j];
    }

    public void setElement(final int i, final int j, final LongNumber newValue) {
        this.element[i][j] = newValue;
    }

    public void setElement(final int i, final int j, final int newValue) {
        this.element[i][j] = new LongNumber(newValue);
    }
}