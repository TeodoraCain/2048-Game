package pkg2048;

class MoveEfficiency implements Comparable<MoveEfficiency> {

    private int numberOfEmptyCells;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyCells, int score, Move move) {
        this.numberOfEmptyCells = numberOfEmptyCells;
        this.score = score;
        this.move = move;
    }

    @Override
    public int compareTo(MoveEfficiency o) {
        if (numberOfEmptyCells > o.numberOfEmptyCells) {
            return 1;
        }
        if (numberOfEmptyCells < o.numberOfEmptyCells) {
            return -1;
        }
        if(score>o.score){
            return 1;
        }
        if(score<o.score){
            return -1;
        }
        return 0;  
    }

    public Move getMove(){
        return move;
    }
}
