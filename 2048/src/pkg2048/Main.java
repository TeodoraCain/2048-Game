package pkg2048;

import pkg2048.command.DeviceButton;
import pkg2048.command.PauseGame;
import pkg2048.command.ResumeGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        JFrame game = new JFrame();
        
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        PauseGame pauseCommand = new PauseGame(controller);
        ResumeGame resumeCommand = new ResumeGame(controller);
        
        DeviceButton onPaused = new DeviceButton(pauseCommand);
        DeviceButton onResumed = new DeviceButton(resumeCommand);

        JButton pauseButton = new JButton("Pause");
        pauseButton.setBounds(130, 568, 100, 20);
        pauseButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                onPaused.press();
            }
            
        });
        game.add(pauseButton);
        
        JButton resumeButton = new JButton("Resume");
        resumeButton.setBounds(230, 568, 100, 20);
         resumeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                onResumed.press();
            }
            
        });
        game.add(resumeButton);

        game.setSize(557, 620);
        game.setResizable(false);
        game.add(controller.getView());
        game.setTitle("2048 Game");

        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }

}
