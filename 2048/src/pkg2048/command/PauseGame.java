package pkg2048.command;

public class PauseGame implements Command{
    Game game;
    
    public PauseGame(Game newGame){
        game = newGame;
    }

    @Override
    public void execute() {
        game.pause();
    }
    
}
