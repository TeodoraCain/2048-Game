package pkg2048.gameStates;

import javax.swing.JOptionPane;
import pkg2048.Controller;

public class EndGameState implements GameState{
    
    Controller controller;
    
    public EndGameState(Controller controller){
        this.controller = controller;
    }

    @Override
    public void play() {
       System.out.println("restart game?");
    }

    @Override
    public void pause() {
        System.out.println("game is ended and you cannot pause");
    }

    @Override
    public void endGame() {       
        System.out.println("the game is already ended");
         
    }
    
}
