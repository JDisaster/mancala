package view;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Mancala Game Board", 50, 50);
    }
}