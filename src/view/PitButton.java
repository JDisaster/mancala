package view;

import javax.swing.*;

/**
 * A JButton subclass that should allow a dynamic amount of stones
 * to be displayed on the button
 */
public class PitButton extends JButton {
    private int pitIndex;

    public PitButton(int pitIndex){
        this.pitIndex = pitIndex;
        updateStones(0);
    }

    public int getPitIndex(){
        return pitIndex;
    }

    /**
     * Updates the button label to say how many stones in pits
     * @param stones used for the amount of stones in pits
     */
    public void updateStones(int stones){
        setText(String.valueOf(stones));
    }
}