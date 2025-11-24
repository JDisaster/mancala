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
        int[] pits = model.getBoard();
        int scoreA = pits[6];
        int scoreB = pits[13];

        turnLabel.setText("Current Turn: " + currentPlayerName
            + " | Score: A = " + scoreA + " | B = " + scoreB);

        if (currentPlayerName.equals("Player A"))
            turnLabel.setForeground(Color.BLUE);
        else
            turnLabel.setForeground(Color.RED);
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
     * Displays the stone count that the players want to start with
     * @return an array of stones
     */
    public int displayStoneCount(){

        final int[] result = {-1};
        JDialog selectDialog = new JDialog((Frame) null, "Select Stones per Pit", true);
        selectDialog.setLayout(new FlowLayout());
        selectDialog.setSize(300, 120);
        selectDialog.setLocationRelativeTo(null);

        JLabel label = new JLabel("Choose the number of stones per pit:");
        JButton threeBtn = new JButton("3 Stones");
        JButton fourBtn = new JButton("4 Stones");
        JButton cancelBtn = new JButton("Cancel");

        threeBtn.addActionListener(e -> {
            result[0] = 3;
            selectDialog.dispose();
        });

        fourBtn.addActionListener(e -> {
            result[0] = 4;
            selectDialog.dispose();
        });

        cancelBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    null, "Exit game?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        });


        selectDialog.add(label);
        selectDialog.add(threeBtn);
        selectDialog.add(fourBtn);
        selectDialog.add(cancelBtn);
        selectDialog.setVisible(true);

        return result[0];
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
