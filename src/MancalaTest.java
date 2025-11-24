import model.BoardModel;
import view.BoardView;
import controller.BoardController;

/**
 * Simple dirver class for launching the Mancala game
 * 
 * Author: Kwonjae Lee, Jacob Thomas
 */

public class MancalaTest {
    public static void main(String[] args) {
        BoardModel model = new BoardModel("Player A", "Player B");
        BoardView view = new BoardView();
        BoardController controller = new BoardController(model, view);

        controller.start();
    }
}