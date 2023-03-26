package pkg2048;

import pkg2048.cells.Cell;

public class Board {

    private static volatile Board instance;
    private Cell[][] gameBoard;
    private static final int FIELD_WIDTH = 5;

    private Board() {
        gameBoard = new Cell[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameBoard[i][j] = new Cell();
            }
        }

    }

    public synchronized void setBoard(Cell[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public synchronized static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }
    
    public Cell[][] getBoard(){
        return gameBoard;
    }

}
