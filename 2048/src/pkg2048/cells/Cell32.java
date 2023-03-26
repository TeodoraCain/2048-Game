package pkg2048.cells;

import java.awt.Color;


public class Cell32 extends Cell{
    
    public Cell32(){
        this.setValue(32);
        this.setX(0);
        this.setY(2);
        this.setColor(Color.cyan);
        System.out.println("Cell4 created"+ this);
    }
}
