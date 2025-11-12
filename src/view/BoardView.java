package view;

import javax.swing.*;
import java.awt.*;
import model.BoardModel;


public class BoardView extends JFrame {
    public JButton undoButton = new JButton("Undo");
    private BoardPanel boardPanel;
    private JLabel turnLabel = new JLabel("Current Turn: Player A");

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

        JPanel top = new JPanel(new FlowLayout());
        top.add(turnLabel);
        add(top,BorderLayout.NORTH);

        add(undoButton, BorderLayout.SOUTH); 
    }

    /** 
     * Updates the visual board with the current state from the model and whos turn it is
     */
    public void updateBoard(BoardModel model) {
        boardPanel.updateBoard(model);
        String currentPlayerName = model.getCurrentPlayer().getName();
        turnLabel.setText("Current Turn: " + currentPlayerName);
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
