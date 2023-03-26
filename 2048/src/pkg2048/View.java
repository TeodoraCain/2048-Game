package pkg2048;

import pkg2048.cells.Cell;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class View extends JPanel {

    private static final Color BACKGROUND_COLOR = new Color(0xbbada0);
    private static final String FONT_NAME = "Arial";
    private static final int TILE_SIZE = 96;
    private static final int TILE_MARGIN = 12;

    private Controller controller;

    private boolean isGameWon = false;
    private boolean isGameLost = false;

    public boolean isGameWon() {
        return isGameWon;
    }

    public void setIsGameWon(boolean isGameWon) {
        this.isGameWon = isGameWon;
    }

    public boolean isGameLost() {
        return isGameLost;
    }

    public void setIsGameLost(boolean isGameLost) {
        this.isGameLost = isGameLost;
    }

    public View(Controller controller) {
        setFocusable(true);
        this.controller = controller;
        addKeyListener(controller);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                drawCell(g, controller.getCells()[y][x], x, y);
            }
        }
        g.drawString("Score: " + controller.getScore(), 20, 585);

//        if (isGameWon) {
//            JOptionPane.showMessageDialog(this, "Game won! :)");
//        } else if (isGameLost) {
//            JOptionPane.showMessageDialog(this, "Game lost! :(");
//        }
    }

    private void drawCell(Graphics gr, Cell cell, int x, int y) {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
        int value = cell.getValue();
        int xOffset = offsetColors(x);
        int yOffset = offsetColors(y);
        g.setColor(cell.getColor()!= null ? cell.getColor(): Color.gray);
        g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 8, 8);
        g.setColor(Color.black);
        final int size = 24;
        final Font font = new Font(FONT_NAME, Font.BOLD, size);
        g.setFont(font);

        String s = String.valueOf(value);
        final FontMetrics fm = getFontMetrics(font);

        final int w = fm.stringWidth(s);
        final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];

        if (value != 0) {
            g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);
        }
    }

    private static int offsetColors(int arg) {
        return arg * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN;
    }

}
