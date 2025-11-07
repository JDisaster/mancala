package model;

/**
 * This class is used to test the MancalaModel independently
 */
public class ModelTest {
    public static void main(String[] args) {
        MancalaModel model = new MancalaModel("p1", "p2");

        // board with 3 stones per pit
        model.initializeBoard(3);
        System.out.println("Initial board:");
        model.printBoard();
    }
}