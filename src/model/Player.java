package model;

/**
 * The player class is used to keep track of player info such as their name, undo count, and which players turn it is
 */
public class Player {
    private String name;
    private boolean isPlayerA;
    private int undoCount;
    private boolean undoLastMove;


    // Constructor for players
    public Player(String name, boolean isPlayerA) {
        this.name = name;
        this.isPlayerA = isPlayerA;
        this.undoCount = 0;
    }

    /**
     * Method used to get player name
     */
    public String getName() {
        return name;
    }

    /**
     * Method used to check which player turn it is
     */
    public boolean isPlayerA() {
        return isPlayerA;
    }

    /**
     * Check how many undos a player used
     */
    public int getUndoCount() {
        return undoCount;
    }

    /**
     * Increment the undo count when player undos move
     */
    public void incUndoCount() {
        undoCount++;
    }

    /**
     * Reset the undo count when new player turn
     */
    public void resetUndo() {
        undoCount = 0;
    }

    /**
     * Getter for state of undo last move
     * 
     * @return - boolean for whether or not undo was the last move
     */
    public boolean getUndoLastMove() {
        return undoLastMove;
    }
    
    /**
     * Setter for the state of undo last move
     * 
     * @param newState - new state of whether or not undo was the last move
     */
    public void setUndoLastMove(boolean newState) {
        undoLastMove = newState;
    }
}
