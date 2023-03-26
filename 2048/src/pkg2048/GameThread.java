package pkg2048;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameThread extends Thread {

    private Model model;
    private View view;

    public GameThread(Controller controller) {
        this.model = controller.getModel();
        this.view = controller.getView();
    }

    @Override
    public void run() {
        try {
            
            while (model.canMove() && !view.isGameLost() && !view.isGameWon()) {
            view.repaint();
            Thread.sleep(1000 * 2);
            
                if (model.canGoDown()) {
                    model.down();
                   
                }

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
