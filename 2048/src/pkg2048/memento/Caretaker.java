package pkg2048.memento;

import java.util.ArrayList;

public class Caretaker {
    
    ArrayList<Memento> savedStates = new ArrayList<>();
    
    public void addMemento(Memento memento){
        savedStates.add(memento);
    }
    
    public Memento getMemento(int index){
        return savedStates.get(index);
    }
    
}
