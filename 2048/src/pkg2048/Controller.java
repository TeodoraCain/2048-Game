
package pkg2048;

import pkg2048.command.Game;
import pkg2048.gameStates.GameState;
import pkg2048.gameStates.PlayGameState;
import pkg2048.gameStates.EndGameState;
import pkg2048.gameStates.PauseGameState;
import pkg2048.cells.Cell;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter implements Game{
    private Model model;
    private View view;
    public GameThread gameThread;
    
    //Game States
    GameState play = new PlayGameState(this);
    GameState pause = new PauseGameState(this);
    GameState endGame = new EndGameState(this);
    
    GameState gameState = play;
    
    private static final int WINNING_CELL = 2048;

    public Controller(Model model){
        this.model = model;
        this.view = new View(this);
        
        startGame();
        
    }
      
    public void startGame(){
        gameThread = new GameThread(this);
        gameThread.start(); 
        
        gameState.play();
    }
    
    void setGameState(GameState newGameState){
        gameState = newGameState;
    }
    
    public Model getModel() {
        return model;
    }
    
    Cell[][] getCells(){
        return model.getGameBoard();
    }
    
    int getScore(){
        return model.score;
    }
    
    void resetGame(){
        model.score = 0;
        model.maxCell = 2;
        
        view.setIsGameLost(false);
        view.setIsGameWon(false);
        
        model.resetBoard();
    }
    
    @Override
    public void keyPressed(KeyEvent e){
     if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
         resetGame();
     }   
     
     if(!model.canMove()){
         view.setIsGameLost(true);
         gameState.endGame();
         gameState = endGame;
     }
     
     if(!view.isGameLost() && !view.isGameWon()){
         if(e.getKeyCode()== KeyEvent.VK_LEFT){
             model.left();
         }
         if(e.getKeyCode()== KeyEvent.VK_RIGHT){
             model.right();
         }
         if(e.getKeyCode()== KeyEvent.VK_DOWN){
             model.down();
         }
         if(e.getKeyCode()== KeyEvent.VK_Z){
             model.undo();
         }
     } else{
         gameThread.stop();
     }
     
     
     if(model.maxCell == WINNING_CELL){
         
         view.setIsGameWon(true);
         
         gameState.endGame();
         gameState = endGame;
         
     }
     
     view.repaint();
     
    }
    
    public View getView(){
        return view;
    }

    @Override
    public void pause() {
 
        gameThread.suspend();
        System.out.println("Game paused!");
        gameState.pause();
        setGameState(pause);
    }

    @Override
    public void resume() {
        gameThread.resume();
        System.out.println("Game resumed!");
        gameState.play();
        setGameState(play);
    }
}
