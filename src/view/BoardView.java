package view;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JFrame {
    public JButton undoButton = new JButton("Undo"); //undo button

    public PitButton A1 = new PitButton();
    public PitButton A2 = new PitButton();
    public PitButton A3 = new PitButton();
    public PitButton A4 = new PitButton();
    public PitButton A5 = new PitButton();
    public PitButton A6 = new PitButton();

    public PitButton B1 = new PitButton();
    public PitButton B2 = new PitButton();
    public PitButton B3 = new PitButton();
    public PitButton B4 = new PitButton();
    public PitButton B5 = new PitButton();
    public PitButton B6 = new PitButton();

    /**
     * Constructs a new view for the program
     */
    public BoardView() {
        setTitle("Mancala Game");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        BoardPanel panel = new BoardPanel();
        add(panel);
    }

    /**
     * Displays a GUI notification popup given a message
     * 
     * @param message - the message to display to the user
     */
    public void displayNotif(String message) {
        JOptionPane.showMessageDialog(null, message, "Notification", JOptionPane.INFORMATION_MESSAGE);
    }
}