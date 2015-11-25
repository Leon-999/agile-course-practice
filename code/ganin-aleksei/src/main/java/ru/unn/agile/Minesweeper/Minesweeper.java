package ru.unn.agile.Minesweeper;

public class Minesweeper {

    private final Board board;

    private static final int BOARD_HEIGHT = 32;
    private static final int BOARD_WIDTH = 32;

    private static final int DEFAULT_MINES = 100;

    public Minesweeper() {
        board = new Board(BOARD_HEIGHT, BOARD_WIDTH);
        newGame();
    }

    public static void main(final String[] args) {
        new Minesweeper();
    }

    public final void newGame() {
        board.clear();
        board.setMinesRandom(DEFAULT_MINES);
    }

    public boolean isEnd() {
        return board.isEnd();
    }
}
