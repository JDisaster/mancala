import view.BoardView;
import controller.BoardController;
import model.BoardModel;

public class MancalaMain {
    public static void main(String[] args) {
        BoardModel model = new BoardModel("player1", "player2");
        BoardView view = new BoardView();
        BoardController controller = new BoardController(model, view);
        controller.start();
    }
}