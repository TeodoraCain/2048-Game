package pkg2048.cells;

import java.awt.Color;


public class Cell16 extends Cell{
    
    public Cell16(){
        this.setValue(16);
        this.setX(0);
        this.setY(2);
        this.setColor(Color.red);
        System.out.println("Cell8 created"+ this);
    }
}