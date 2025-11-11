import javax.swing.*;
import model.BoardModel;
import view.BoardView;
import controller.BoardController;

public class MancalaMain {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(
            "Enter the number of stones per pit (3 or 4):"
        );
        int stonesPerPit = 4; // default
        try {
            int inputNum = Integer.parseInt(input);
            if (inputNum == 3 || inputNum == 4) {
                stonesPerPit = inputNum;
            }
        } catch (Exception e) {
            // if input invalid, default stays 4
        }

        BoardModel model = new BoardModel("Player A", "Player B");
        model.initializeBoard(stonesPerPit); // 

        BoardView view = new BoardView();
        BoardController controller = new BoardController(model, view);

        view.updateBoard(model);
        controller.start();
    }
}