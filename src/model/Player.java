package model;

/**
 * The player class is used to keep track of player info such as their name,
 * undo count, and which players turn it is
 */
public class Player {
    private String name;
    private boolean isPlayerA;
    private int undoCount;

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
}
