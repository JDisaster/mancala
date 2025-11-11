package model;

public class ModelTest {
    public static void main(String[] args) {
        BoardModel model = new BoardModel("A", "B");
        model.initializeBoard(4);

        System.out.println("Start state:");
        model.printBoard();

        model.makeMove(0);
        System.out.println("\nAfter move from pit 0:");
        model.printBoard();

        model.makeMove(7);
        System.out.println("\nAfter move from pit 7:");
        model.printBoard();
    }
}