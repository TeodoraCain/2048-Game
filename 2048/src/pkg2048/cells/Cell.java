package pkg2048.cells;

import java.awt.Color;

public class Cell {

    private int value = 0;
    private int x;
    private int y;
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void erase() {
        this.value = 0;
        this.x = 0;
        this.y = 0;
        this.color = Color.gray;
    }

    @Override
    public String toString() {
        return "Cell{" + "value=" + value + ", x=" + x + ", y=" + y + '}';
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public Color getFontColor() {
        return value < 16 ? Color.black : Color.blue;
    }

}
