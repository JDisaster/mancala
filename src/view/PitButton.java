package view;

import javax.swing.*;

/**
 * A JButton subclass that stores its pit index.
 */
public class PitButton extends JButton {
    private int index;  // pit index (0–13)

    public PitButton(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
    public void updateStones(int stones) {
        setText(String.valueOf(stones));
    }
}
