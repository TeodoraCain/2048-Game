package pkg2048.gameStates;

import javax.swing.JOptionPane;
import pkg2048.Controller;

public class PlayGameState implements GameState {

    Controller controller;

    public PlayGameState(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void play() {
        System.out.println("The game is being played.");
    }

    @Override
    public void pause() {
        System.out.println("The game is being paused.");
    }

    @Override
    public void endGame() {

        if (controller.getView().isGameWon()) {
            JOptionPane.showMessageDialog(controller.getView(), "Game won! :)");
        } else if (controller.getView().isGameLost()) {
            JOptionPane.showMessageDialog(controller.getView(), "Game lost! :(");
        }

        controller.gameThread.stop();
    }

}
