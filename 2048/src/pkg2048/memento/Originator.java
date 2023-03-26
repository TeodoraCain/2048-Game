package pkg2048.memento;

import pkg2048.cells.Cell;

public class Originator {

    private Cell[][] board;
    private Cell lastCell;
    private int score;

    public void setMemento(Cell[][] board, Cell lastCell, int score) {
        this.board = board;
        this.lastCell = lastCell; 
        this.score = score;
    }

    public Memento storeInMemento(){
        return new Memento(board, lastCell, score);
    }
    
    public Cell[][] restoreBoardFromMemento(Memento memento) {
        board = memento.getBoard();
        return board;
    }

    public Cell restoreLastCellFromMemento(Memento memento) {
        lastCell = memento.getLastCell();
        return lastCell;
    }

    public int restoreScoreFromMemento(Memento memento) {
        score = memento.getScore();        
        return score;
    }
    
    
}
