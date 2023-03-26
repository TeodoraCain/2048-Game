package pkg2048;

import pkg2048.memento.Memento;
import pkg2048.memento.Caretaker;
import pkg2048.memento.Originator;
import pkg2048.cells.Cell;
import pkg2048.cells.CellFactory;
import java.util.*;

public class Model {

    private static final int FIELD_WIDTH = 5;
    private Cell[][] gameBoard;
    private Board board = Board.getInstance();
    private Cell currentCell;
    private CellFactory cellFactory = new CellFactory();
    private int[] possibleCells = {2, 4, 8, 16, 32, 64};

    boolean ok = true;
    int score = 0;
    int maxCell = 2;

    private final Caretaker caretaker = new Caretaker();
    private final Originator originator = new Originator();
    private int savedStates = 0;
    private int currentState = 0;

    public Model() {
        resetBoard();
    }

    private List<Cell> getEmptyCells() {
        List<Cell> result = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameBoard[i][j].getValue() == 0) {
                    result.add(gameBoard[i][j]);
                }
            }
        }
        return result;
    }

    void addCell() {
        List<Cell> emptyCells = getEmptyCells();
        if (!emptyCells.isEmpty() && gameBoard[0][1].isEmpty() && gameBoard[0][3].isEmpty()) {
            int index = 2;
            currentCell = emptyCells.get(index);
            currentCell.setX(0);
            currentCell.setY(index);

            int min = 0;
            int max = 5;
            int randomValue = (int) Math.floor(Math.random() * (max - min + 1) + min);
            Cell newCell = cellFactory.addCell(possibleCells[randomValue]);
            currentCell.setValue(newCell.getValue());
            currentCell.setColor(newCell.getColor());

        }

    }

    public void resetBoard() {
        gameBoard = new Cell[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameBoard[i][j] = new Cell();
            }
        }
        board.setBoard(gameBoard);
        addCell();
    }

    private boolean compressCells(Cell[] board, int newIndex) {
        boolean changed = false;
        int index = currentCell.getY();

        if (newIndex >= 0 && newIndex <= 4) {
            if (board[newIndex].isEmpty()) {
                Cell temp = board[newIndex];
                board[newIndex] = board[index];
                board[newIndex].setY(newIndex);
                board[index] = temp;
                changed = true;
                currentCell = board[newIndex];

            }
        }

        return changed;
    }

    private boolean mergeCells(Cell[] board, int index) {
        boolean changed = false;
        int i = currentCell.getY();
        if (index >= 0 && index <= 4) {

            if (board[index].getValue() == board[i].getValue()) {
                board[i].setValue(board[i].getValue() * 2);  
                board[i].setColor(cellFactory.addCell(board[i].getValue()).getColor());
              
                currentCell.setValue(board[i].getValue());
                currentCell.setX(board[i].getX());
                currentCell.setY(board[i].getY());
                board[index].erase();

                changed = true;
                int newValue = board[i].getValue();
                score += newValue;
                if (newValue > maxCell) {
                    maxCell = newValue;
                }

            } else {
                compressCells(board, index);
            }
        }

        return changed;
    }

    private void rotateGameBoard() {

        Cell[][] newBoard = new Cell[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                newBoard[j][4 - i] = gameBoard[i][j];
            }
        }
        int x = currentCell.getX();
        currentCell.setX(currentCell.getY());
        currentCell.setY(4 - x);
        gameBoard = newBoard;

    }

    void left() {

        int newIndex = currentCell.getY() - 1;
        mergeCells(gameBoard[currentCell.getX()], newIndex);
        checkBoard();

    }

    void checkBoard() {

        ok = false;
        while (!ok) {
            ok = true;
            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_WIDTH - 1; j++) {

                    if (!gameBoard[i][j].isEmpty() && gameBoard[i][j].getValue() == gameBoard[i][j + 1].getValue()) {
                        gameBoard[i][j].setValue(gameBoard[i][j].getValue() * 2);
                        gameBoard[i][j].setColor(cellFactory.addCell(gameBoard[i][j].getValue()).getColor());
                        //gameBoard[i][j].setValue(cellFactory.addCell(gameBoard[i][j].getValue() * 2).getValue());
                        
                        gameBoard[i][j + 1].erase();
                        ok = false;
                        //System.out.println("checkBoard:" + currentCell);
                        int newValue = gameBoard[i][j].getValue();
                        score += newValue;
                        if (newValue > maxCell) {
                            maxCell = newValue;

                        }

                    }
                }
            }
        }

    }

    void down() {
        ok = false;
        saveState(gameBoard);

        if (currentCell.isEmpty()) {
            addCell();
        }

        if (canGoDown()) {
            rotateGameBoard();
            left();
            rotateGameBoard();
            rotateGameBoard();
            rotateGameBoard();
        }

        ok = true;
    }

    private int getLastIndex(int column) {
        for (int i = 4; i >= 0; i--) {
            if (gameBoard[i][column].getValue() == 0) {
                return i;
            }
        }
        return 0;
    }

    public boolean canGoDown() {

        if (currentCell.getX() - 1 == getLastIndex(currentCell.getY())) {
            addCell();

            return false;

        }
        return true;
    }

    void right() {
        ok = false;

        if (canGoDown()) {
            rotateGameBoard();
            rotateGameBoard();
            left();
            rotateGameBoard();
            rotateGameBoard();
        }
        ok = true;
    }

    public Cell[][] getGameBoard() {
        return gameBoard;
    }

    private boolean isFull() {
        return !canGoDown() && !gameBoard[0][2].isEmpty() && !gameBoard[1][2].isEmpty();
    }

    public boolean canMove() {
        if (!isFull()) {
            return true;
        }
        if (!gameBoard[1][2].isEmpty() || !gameBoard[0][1].isEmpty() || !gameBoard[0][3].isEmpty()) {
            return false;
        }

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                Cell cell = gameBoard[i][j];
                if (((i < FIELD_WIDTH - 1) && (cell.getValue() == gameBoard[i + 1][j].getValue()))
                        || ((j < FIELD_WIDTH - 1) && (cell.getValue() == gameBoard[i][j + 1].getValue()))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void saveState(Cell[][] gameBoard) {
        Cell[][] gameBoard1 = new Cell[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                Cell block = new Cell();
                block.setValue(gameBoard[i][j].getValue());
                gameBoard1[i][j] = block;
            }
        }

        board.setBoard(gameBoard1);

        originator.setMemento(gameBoard1, currentCell, score);
        caretaker.addMemento(originator.storeInMemento());
        savedStates++;
        currentState++;

    }

    public void undo() {
        if (currentState >= 1) {
            currentState--;
            Memento memento;
            memento = caretaker.getMemento(currentState);

            gameBoard = originator.restoreBoardFromMemento(memento);
            Cell restoredCell = originator.restoreLastCellFromMemento(memento);
            currentCell.setColor(restoredCell.getColor());
            currentCell.setValue(restoredCell.getValue());
            currentCell.setX(restoredCell.getX());
            currentCell.setY(restoredCell.getY());
            score = originator.restoreScoreFromMemento(memento);

        }
    }

    boolean hasBoardChanged() {
        Memento memento = caretaker.getMemento(currentState);
        Cell[][] savedGameBoard = originator.restoreBoardFromMemento(memento);
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameBoard[i][j].getValue() != savedGameBoard[i][j].getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    MoveEfficiency getMoveEfficiency(Move move) {
        move.move();

        int emptyCells = -1;
        int newScore = 0;

        if (hasBoardChanged()) {
            emptyCells = getEmptyCells().size();
            newScore = score;
        }
        MoveEfficiency moveEfficiency = new MoveEfficiency(emptyCells, newScore, move);
        undo();

        return moveEfficiency;
    }

}
