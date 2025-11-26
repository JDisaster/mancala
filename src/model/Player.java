/**
 * @author Aidan Zheng
 * @version 1.0
 */

package model;

/**
 * The player class is used to keep track of player info such as their name,
 * undo count, and which players turn it is
 */
public class Player {
    private String name; // name of the player
    private boolean isPlayerA; // whether or not this is player A
    private int undoCount; // amount of undo actions taken
    private boolean undoLastMove; // whether or not undo was the last move executed

    // Constructor for players
    public Player(String name, boolean isPlayerA) {
        this.name = name;
        this.isPlayerA = isPlayerA;
        this.undoCount = 0;
    }

    /**
     * Getter for player name
     * 
     * @return - the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Method used to check which player turn it is
     * 
     * @return - whether or not this is player A
     */
    public boolean isPlayerA() {
        return isPlayerA;
    }

    /**
     * Check how many undos a player used
     * 
     * @return - the amount of undo actions executed
     */
    public int getUndoCount() {
        return undoCount;
    }

    /**
     * Increments the undo count, used when a player executes an undo
     */
    public void incUndoCount() {
        undoCount++;
    }

    /**
     * Reset the undo count when a new turn is reached
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
