package pkg2048.cells;

import java.awt.Color;


public class Cell8 extends Cell{
    
    public Cell8(){
        this.setValue(8);
        this.setX(0);
        this.setY(2);
        this.setColor(Color.orange);
        System.out.println("Cell8 created"+ this);
    }
}
