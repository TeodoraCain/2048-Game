package pkg2048.cells;

import java.awt.Color;

public class Cell2 extends Cell{
    
    public Cell2(){
        this.setValue(2);
        this.setX(0);
        this.setY(2);
        this.setColor(Color.yellow);
        System.out.println("Cell2 created"+ this);
    }
}
