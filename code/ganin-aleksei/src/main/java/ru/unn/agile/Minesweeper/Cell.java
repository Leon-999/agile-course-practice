package ru.unn.agile.Minesweeper;

public class Cell {
    private boolean mine;
    private boolean issue;
    private boolean flag;
    private boolean openV;
    private int value;

    private final int y;
    private final int x;

    public Cell(final int y, final int x) {
        this.y = y;
        this.x = x;
    }

    public void setMine() {
        mine = true;
    }
    public boolean isMine() {
        return mine;
    }

    public void setValue(final int val) {
        value = val;
    }

    public int getValue() {
        return value;
    }

    public void setIssue() {
        issue = true;
    }

    public boolean isIssue() {
        return issue;
    }

    public void unsetIssue() {
        issue = false;
    }

    public void setFlag() {
        flag = true;
    }

    public boolean isFlag() {
        return flag;
    }

    public void unsetFlag() {
        flag = false;
    }

    public void open() {
        openV = true;
    }

    public boolean isOpen() {
        return openV;
    }

    public void clear() {
        unsetIssue();
        unsetFlag();
        setValue(0);
        mine = false;
        openV = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
