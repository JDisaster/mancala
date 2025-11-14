import model.BoardModel;
import view.BoardView;
import controller.BoardController;

public class MancalaMain {
    public static void main(String[] args) {
        BoardModel model = new BoardModel("Player A", "Player B");
        BoardView view = new BoardView();
        BoardController controller = new BoardController(model, view);

        controller.start();
    }
}