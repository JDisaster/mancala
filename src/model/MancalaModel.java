package model;

/**
 * MancalaModel represents the main data structure of the Mancala game.
 */
public class MancalaModel {

    private int[] pits;          // 14 pits: 0-5 (Player A), 6 (A Mancala), 7-12 (Player B), 13 (B Mancala)
    private int[] previousBoard; //This array will be used to go back to the previous board when undo
    private Player currentPlayer;   // 0 = Player A, 1 = Player B
    private Player playerA;
    private Player playerB;
    private boolean gameOver;    // Flag to indicate if the game has ended

    /** Constructor: creates a new Mancala board with each player identified. */
    public MancalaModel(String nameA, String nameb) {
        pits = new int[14];
        playerA = new Player(nameA, true);
        playerB = new Player(nameb, false);
        currentPlayer = playerA;
        gameOver = false;
    }

    /**
     * Initializes the board with a given number of stones per pit.
     * Mancala pits (index 6 and 13) are set to 0.
     */
    public void initializeBoard(int stonesPerPit) {
        for (int i = 0; i < pits.length; i++) {
            if (i == 6 || i == 13) pits[i] = 0; 
            else pits[i] = stonesPerPit;
        }
        playerA.resetUndo();
        playerB.resetUndo();
        currentPlayer = playerA;
    }

    /** Prints the current board layout for testing. */
    public void printBoard() {
        System.out.println("    " + pits[12] + " " + pits[11] + " " + pits[10] + " " +
                           pits[9] + " " + pits[8] + " " + pits[7]);
        System.out.println(pits[13] + "                   " + pits[6]);
        System.out.println("    " + pits[0] + " " + pits[1] + " " + pits[2] + " " +
                           pits[3] + " " + pits[4] + " " + pits[5]);
    }
    /**
     * Get the current player
     * @return the current player
     */
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
}
