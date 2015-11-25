package ru.unn.agile.Minesweeper;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CellTest {

    private Cell cell;
    private int cellX = 12;
    private int cellY = 23;


    @Before
    public void before() {
        cell = new Cell(cellY, cellX);
    }

    @Test
    public void isMineShouldReturnTrueWhenSetMine() {
        cell.setMine();
        assertEquals(true, cell.isMine());
    }

    @Test
    public void getValueShouldReturn8WhenSetValue8() {
        int value = 8;
        cell.setValue(value);
        assertEquals(value, cell.getValue());
    }

    @Test
    public void isIssueShouldReturnTrueWhenSetIssue() {
        cell.setIssue();
        assertEquals(true, cell.isIssue());
    }

    @Test
    public void isIssueShouldReturnFalseWhenUnsetIssue() {
        cell.unsetIssue();
        assertEquals(false, cell.isIssue());
    }

    @Test
    public void isFlagShouldReturnTrueWhenSetFlag() {
        cell.setFlag();
        assertEquals(true, cell.isFlag());
    }

    @Test
    public void isFlagShouldReturnFalseWhenUnsetFlag() {
        cell.unsetFlag();
        assertEquals(false, cell.isFlag());
    }

    @Test
    public void isOpenShouldReturnTrueWhenOpenCell() {
        cell.open();
        assertEquals(true, cell.isOpen());
    }

    @Test
    public void isMineShouldReturnFalseWhenClearCell() {
        cell.setMine();
        cell.clear();
        assertEquals(false, cell.isMine());
    }

    @Test
    public void returnGetXShouldBeEqualsCellX() {
        assertEquals(cellX, cell.getX());
    }

    @Test
    public void returnGetYShouldBeEqualsCellY() {
        assertEquals(cellY, cell.getY());
    }
}
