package ru.unn.agile.Minesweeper;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MinesweeperTest {

    private Minesweeper minesweeper;
    @Before
    public void before() {
        minesweeper = new Minesweeper();
    }

    @Test
    public void postEndNewGameIsEndUnset() {
        minesweeper.newGame();
        assertEquals(false, minesweeper.isEnd());
    }
}
