package pkg2048.cells;

public class CellFactory {
    
    public Cell addCell(int value){
                
        switch(value){
            case 2: return new Cell2();
            case 4: return new Cell4();
            case 8: return new Cell8();
            case 16: return new Cell16();
            case 32: return new Cell32();
            case 64: return new Cell64();
            default: return new Cell();
    }
    }
    
}
