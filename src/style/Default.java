/**
 * @author Jacob Thomas
 * @version 1.0
 */

package style;

import javax.swing.*;
import java.awt.*;

import view.BoardView;

/**
 * Default visual style for the Mancala board
 * Incorporates a wood board and basic stone color
 */

public class Default implements BoardStyle {
    /**
     * Completes the contract of BoardStyle
     * Moves panels and buttons into desired locations
     * 
     * @param view - the view object to modify
     */
    @Override
    public void initView(BoardView view) {
        view.setTitle("Default Mancala Game Style");
        view.setSize(800, 400);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setLayout(new BorderLayout());

        view.add(view.getBoardPanel(), BorderLayout.CENTER);

        JPanel top = new JPanel(new FlowLayout());
        top.add(view.getTurnLabel());
        view.add(top, BorderLayout.NORTH);

        view.add(view.getUndoButton(), BorderLayout.SOUTH);
    }
}