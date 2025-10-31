import javax.swing.*;
import view.BoardView;

public class MancalaMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mancala Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.add(new BoardView());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
