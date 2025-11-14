package view;

import javax.swing.*;
import java.awt.*;
import model.BoardModel;

/**
 * BoardPanel is responsible for drawing the Mancala game layout
 * It represents the pits and Mancala
 */
public class BoardPanel extends JPanel {

    private PitButton[] pitButtons = new PitButton[14];

    /** 
     * Constructs the Mancala game board with 14 pits 
     * (0–5, 6 = A Mancala, 7–12, 13 = B Mancala)
     */
    public BoardPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 5, 5, 5);

        // Player B pits (top row: 12 → 7)
        for (int i = 12, col = 1; i >= 7; i--, col++) {
            pitButtons[i] = new PitButton(i);   
            pitButtons[i].setPreferredSize(new Dimension(80,80));
            gc.gridx = col;
            gc.gridy = 0;
            add(pitButtons[i], gc);
        }

        // Mancala for player A (left side, index 6)
        pitButtons[6] = new PitButton(6);
        pitButtons[6].setPreferredSize(new Dimension(80,160));
        gc.gridx = 7;
        gc.gridy = 0;
        gc.gridheight = 2;
        add(pitButtons[6], gc);
        gc.gridheight = 1;

        // Player A pits (bottom row: 0 → 5)
        for (int i = 0, col = 1; i <= 5; i++, col++) {
            pitButtons[i] = new PitButton(i);
            pitButtons[i].setPreferredSize(new Dimension(80,80));
            gc.gridx = col;
            gc.gridy = 1;
            add(pitButtons[i], gc);
        }

        // Mancala for player B (right side, index 13)
        pitButtons[13] = new PitButton(13);
        pitButtons[13].setPreferredSize(new Dimension(80,160));
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridheight = 2;
        add(pitButtons[13], gc);
    }

    /** 
     * Paints a title on the panel.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Mancala Game Board", 50, 50);
    }

    /** 
     * Updates all pit buttons with the current number of stones from the model.
     */
    public void updateBoard(BoardModel model) {
        int[] pits = model.getBoard();

        for (int i = 0; i < 14; i++) {
            pitButtons[i].updateStones(pits[i]);
        }

        repaint();
    }

    /** 
     * Gives access to the array of pit buttons (for controller use)
     */
    public PitButton[] getPitButtons() {
        return pitButtons;
    }
}