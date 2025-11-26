/**
 * @author Kwonjae Lee, Jacob Thomas
 * @version 1.0
 */

import model.BoardModel;
import view.BoardView;
import controller.BoardController;

/**
 * Simple driver class for launching the Mancala game, initializes
 * model, view, and controller with the model and view objects and
 * launches the game via the controller
 */

public class MancalaTest {
    public static void main(String[] args) {
        BoardModel model = new BoardModel("Player A", "Player B");
        BoardView view = new BoardView();
        BoardController controller = new BoardController(model, view);

        controller.start();
    }
}