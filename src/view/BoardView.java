package view;

import javax.swing.*;
import java.awt.*;
import model.BoardModel;


public class BoardView extends JFrame {
    public JButton undoButton = new JButton("Undo");
    private BoardPanel boardPanel;

     /*
    //player A's buttons
    public PitButton A1 = new PitButton();
    public PitButton A2 = new PitButton();
    public PitButton A3 = new PitButton();
    public PitButton A4 = new PitButton();
    public PitButton A5 = new PitButton();
    public PitButton A6 = new PitButton();

    //player B's buttons
    public PitButton B1 = new PitButton();
    public PitButton B2 = new PitButton();
    public PitButton B3 = new PitButton();
    public PitButton B4 = new PitButton();
    public PitButton B5 = new PitButton();
    public PitButton B6 = new PitButton();

    */

    /** 
     * Constructs a new view for the program
     */
    public BoardView() {
        setTitle("Mancala Game");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        boardPanel = new BoardPanel();
        add(boardPanel, BorderLayout.CENTER);
        add(undoButton, BorderLayout.SOUTH);
    }

    /** 
     * Updates the visual board with the current state from the model
     */
    public void updateBoard(BoardModel model) {
        boardPanel.updateBoard(model);
    }

    /** 
     * Displays a popup notification message
     */
    public void displayNotif(String message) {
        JOptionPane.showMessageDialog(this, message, "Notification", JOptionPane.INFORMATION_MESSAGE);
    }
    public PitButton[] getPitButtons() {
        return boardPanel.getPitButtons();
    }
}
