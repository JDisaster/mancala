package view;

import javax.swing.*;
import java.awt.*;
import model.BoardModel;
import style.*;


public class BoardView extends JFrame {
    public JButton undoButton;
    private BoardPanel boardPanel;
    private JLabel turnLabel;
    private BoardStyle style = new Default();

    /** 
     * Constructs a new view for the program
     */
    public BoardView() {
        undoButton = new JButton("Undo");
        boardPanel = new BoardPanel();
        turnLabel = new JLabel("Current Turn: Player A");
    }

    /** 
     * Updates the visual board with the current state from the model and whose turn it is
     */
    public void updateBoard(BoardModel model) {
        boardPanel.updateBoard(model);
        String currentPlayerName = model.getCurrentPlayer().getName();
        turnLabel.setText("Current Turn: " + currentPlayerName);
    }

    /**
     * Displays the style selection screen, allowing the user to
     * choose a style for the board
     */
    public void displayStyleSelection() {
        JDialog stylePopup = new JDialog((Frame) null, "Choose a style", true);
        stylePopup.setSize(300, 100);
        stylePopup.setLayout(new FlowLayout());

        JButton defaultStyle = new JButton("Default Style");
        JButton altStyle = new JButton("Alternative Style");

        defaultStyle.addActionListener(e -> {
            setStyle(new Default());
            stylePopup.dispose();
        });
        altStyle.addActionListener(e -> {
            setStyle(new Alt());
            stylePopup.dispose();
        });

        stylePopup.add(defaultStyle);
        stylePopup.add(altStyle);
        stylePopup.setLocationRelativeTo(null);
        stylePopup.setVisible(true);

        style.initView(this);
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

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public JButton getUndoButton() {
        return undoButton;
    }

    public JLabel getTurnLabel() {
        return turnLabel;
    }

    public void setStyle(BoardStyle newStyle) {
        style = newStyle;
    }
}
