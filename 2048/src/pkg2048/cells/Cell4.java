package pkg2048.cells;

import java.awt.Color;


public class Cell4 extends Cell{
    
    public Cell4(){
        this.setValue(4);
        this.setX(0);
        this.setY(2);
        this.setColor(Color.blue);
        System.out.println("Cell4 created"+ this);
    }
}
