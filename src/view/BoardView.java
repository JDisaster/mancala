package view;

import javax.swing.*;
import java.awt.*;
import controller.BoardController;

public class BoardView extends JFrame {
    public BoardView() {
        setTitle("Mancala Game");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        BoardPanel panel = new BoardPanel();
        add(panel);
    }
}
