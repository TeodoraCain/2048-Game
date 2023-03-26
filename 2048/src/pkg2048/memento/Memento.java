package pkg2048.memento;

import pkg2048.cells.Cell;

public class Memento {

    private Cell[][] board;
    private Cell lastCell;
    private int score;

    public Memento(Cell[][] board, Cell lastCell, int score) {
        this.board = board;
        this.lastCell = lastCell;
        this.score = score;
    }

    public Cell getLastCell() {
        return lastCell;
    }

    public int getScore() {
        return score;
    }

    public Cell[][] getBoard() {
        return this.board;
    }

}
