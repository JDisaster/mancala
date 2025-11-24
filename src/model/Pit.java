package model;

/**
 * Pit represents a single pit in the Mancala board.
 * It stores and manages the number of stones inside the pit.
 * 
 * Author: Kwonjae Lee
 */
public class Pit {
    private int stones;

    /** Creates a pit with the given number of stones. */
    public Pit(int stones) {
        this.stones = stones;
    }

    /** Returns the current number of stones in this pit. */
    public int getStones() {
        return stones;
    }

    /** Sets the number of stones in this pit. */
    public void setStones(int stones) {
        this.stones = stones;
    }
}
