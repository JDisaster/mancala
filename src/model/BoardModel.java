/**
 * @author Aidan Zheng, Kwonjae Lee, Jacob Thomas
 * @version 1.0
 */

package model;

/**
 * MancalaModel represents the main data structure of the Mancala game.
 * It contains all the core game state and logic.
 * Responsible for working the undo function, pit ownership, stone distrubution,
 * and checking if game is over
 */

public class BoardModel {

    private int[] pits; // 14 pits: 0-5 (Player A), 6 (Mancala A), 7-12 (Player B), 13 (Mancala B)
    private int[] previousBoard; // This array will be used to go back to the previous board when undo
    private Player currentPlayer; // 0 = Player A, 1 = Player B
    private Player playerA;
    private Player playerB;
    private boolean gameOver; // Flag to indicate if the game has ended
    private Player lastPlayer;

    /**
     * Constructor: creates a new Mancala board with each player identified
     * 
     * @param nameA - Name of the first player
     * @param nameb - Name of the second player
     */
    public BoardModel(String nameA, String nameb) {
        pits = new int[14];
        playerA = new Player(nameA, true);
        playerB = new Player(nameb, false);
        currentPlayer = playerA;
        gameOver = false;
    }

    /**
     * Initializes the board with a given number of stones per pit.
     * Mancala pits (index 6 and 13) are set to 0
     * 
     * @param stonesPerPit - the number of initial stones per pit
     */
    public void initializeBoard(int stonesPerPit) {
        for (int i = 0; i < pits.length; i++) {
            if (i == 6 || i == 13)
                pits[i] = 0;
            else
                pits[i] = stonesPerPit;
        }
        playerA.resetUndo();
        playerB.resetUndo();
        currentPlayer = playerA;
        currentPlayer.setUndoLastMove(false);
    }

    /**
     * Getter for current player
     * 
     * @return - the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Getter for the board
     * 
     * @return - a clone of the board (to avoid direct modification)
     */
    public int[] getBoard() {
        return pits.clone();
    }

    /**
     * Saves the boards previous state
     */
    public void saveState() {
        previousBoard = getBoard();
    }

    /**
     * Switches the current players turn
     */
    public void switchTurn() {
        if (currentPlayer == playerA) {
            currentPlayer = playerB;
        } else {
            currentPlayer = playerA;
        }
    }

    /**
     * Checks if the selected pit belongs to the current current players side
     * 
     * @param pitIndex - index of the selected pit
     * @return - true if pit is valid, false if otherwise
     */
    public boolean isValidPit(int pitIndex) {
        if (pitIndex == 6 || pitIndex == 13) {
            return false;
        }
        if (currentPlayer == playerA && pitIndex >= 0 && pitIndex <= 5) {
            return true;
        }
        if (currentPlayer == playerB && pitIndex >= 7 && pitIndex <= 12) {
            return true;
        }
        return false;
    }

    /**
     * Returns the index of the opposite pit so player can capture
     * 
     * @param pitIndex - index of the selected pit
     * @return - the opposite pit index or -1 for invalid pit (if pit is Mancala)
     */
    public int getOppositePit(int pitIndex) {
        if (pitIndex >= 0 && pitIndex <= 5) {
            return 12 - pitIndex;
        } else if (pitIndex >= 7 && pitIndex <= 12) {
            return 12 - pitIndex;
        }
        return -1;
    }

    /**
     * Makes a move based on the selected pit and checks if the game is over, there
     * is an invalid selection, a pit is captured, or if the current player
     * must be switched
     * 
     * @param pitIndex - index of the selected pit
     */
    public void makeMove(int pitIndex) {
        // Ignore empty or invalid pits
        if (!isValidPit(pitIndex)) {
            return;
        }
        if (pits[pitIndex] == 0) {
            return;
        }
        // Undo functionality
        if (getCurrentPlayer().getUndoCount() < 3) {
            saveState();
            lastPlayer = currentPlayer;
            currentPlayer.setUndoLastMove(false);
        }

        // Grab all stones in selected pit
        int stones = pits[pitIndex];
        pits[pitIndex] = 0;

        // Distribute stones to each pit after picked up one
        while (stones > 0) {
            pitIndex = (pitIndex + 1) % 14;
            // Skip opponents mancala
            if (currentPlayer == playerA && pitIndex == 13) {
                continue;
            }
            if (currentPlayer == playerB && pitIndex == 6) {
                continue;
            }
            // Drop a stone into index pit
            pits[pitIndex]++;
            stones--;
        }

        // If player A lands in empty pit of their own, capture enemies pit
        if (currentPlayer == playerA && pitIndex >= 0 && pitIndex <= 5 && pits[pitIndex] == 1) {
            int opposite = getOppositePit(pitIndex);
            if (pits[opposite] > 0) {
                pits[6] += pits[opposite] + pits[pitIndex];
                pits[opposite] = 0;
                pits[pitIndex] = 0;
            }
        }
        // If player B lands in empty pit of their own, capture enemies pit
        else if (currentPlayer == playerB && pitIndex >= 7 && pitIndex <= 12 && pits[pitIndex] == 1) {
            int opposite = getOppositePit(pitIndex);
            if (pits[opposite] > 0) {
                pits[13] += pits[opposite] + pits[pitIndex];
                pits[opposite] = 0;
                pits[pitIndex] = 0;
            }
        }
        // Switch players if last stone doesn't land in current players Mancala
        if ((currentPlayer == playerA && pitIndex != 6) || (currentPlayer == playerB && pitIndex != 13)) {
            switchTurn();
            currentPlayer.resetUndo();
        }
        // If one side of board is empty, move remaining stones in to their Mancala
        // sides
        if (isGameOver()) {
            int sumA = 0;
            int sumB = 0;

            for (int i = 0; i < 6; i++) {
                sumA += pits[i];
                pits[i] = 0;
            }
            for (int i = 7; i < 13; i++) {
                sumB += pits[i];
                pits[i] = 0;
            }
            pits[6] += sumA;
            pits[13] += sumB;
            gameOver = true;
        }
    }

    /**
     * Check if the game is over based on if a side is empty
     * 
     * @return - true or false as a checker
     */
    public boolean isGameOver() {
        boolean sideAEmpty = true;
        boolean sideBEmpty = true;

        for (int i = 0; i < 6; i++) {
            if (pits[i] != 0) {
                sideAEmpty = false;
            }
            if (pits[i + 7] != 0) {
                sideBEmpty = false;
            }
        }
        gameOver = sideAEmpty || sideBEmpty;
        return gameOver;
    }

    /**
     * Getter for previous board
     * 
     * @return - previous board as int[]
     */
    public int[] getPreviousBoard() {
        return previousBoard;
    }

    /**
     * Setter for the board
     * 
     * @param newBoard - new board to update the board to
     */
    public void setBoard(int[] newBoard) {
        pits = newBoard;
    }

    /**
     * Internal undo method used by the controller
     */
    public void undo() {
        if (!canUndo())
            return;
        pits = previousBoard.clone();
        currentPlayer = lastPlayer;
        currentPlayer.setUndoLastMove(true);
        currentPlayer.incUndoCount();

        previousBoard = null;
    }

    /**
     * Checks if an internal undo is possible
     * 
     * @return - true if possible, false if not
     */
    public boolean canUndo() {
        return previousBoard != null && lastPlayer != null && !lastPlayer.getUndoLastMove()
                && lastPlayer.getUndoCount() < 3;
    }
}
