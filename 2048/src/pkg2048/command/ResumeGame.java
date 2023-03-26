package pkg2048.command;

public class ResumeGame implements Command{
    Game game;
    
    public ResumeGame(Game newGame){
        game = newGame;
    }

    @Override
    public void execute() {
        game.resume();
    }
    
}
