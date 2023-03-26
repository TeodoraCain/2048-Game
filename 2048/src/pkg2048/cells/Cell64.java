package pkg2048.cells;

import java.awt.Color;


public class Cell64 extends Cell{
    
    public Cell64(){
        this.setValue(64);
        this.setX(0);
        this.setY(2);
        this.setColor(Color.pink);
        System.out.println("Cell4 created"+ this);
    }
}
