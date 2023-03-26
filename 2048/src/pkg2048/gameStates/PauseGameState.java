package pkg2048.gameStates;

import pkg2048.Controller;

public class PauseGameState implements GameState {

    Controller controller;

    public PauseGameState(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void play() {
        System.out.println("the game is being resumed");
    }

    @Override
    public void pause() {
        System.out.println("the game is already paused");
    }

    @Override
    public void endGame() {
        System.out.println("the game is was paused and now it's ended");
    }

}
