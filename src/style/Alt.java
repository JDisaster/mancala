/**
 * @author Aidan Zheng, Jacob Thomas
 * @version 1.0
 */

package style;

import view.BoardView;
import javax.swing.*;
import java.awt.*;

/**
 * Alternative visual style that the players can choose
 * This implements a neon style game board utilizing fun colors
 */

public class Alt implements BoardStyle {
    /**
     * Completes the contract of BoardStyle
     * Changes the board's view style by changing colors of different components
     * Moves the components into the desired location on board
     * 
     * @param view - the view object to modify
     */
    @Override
    public void initView(BoardView view) {
        view.setTitle("Neon Mancala Style");
        view.setSize(800, 400);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setLayout(new BorderLayout());
        view.getContentPane().setBackground(Color.BLACK);
        view.getBoardPanel().setBackground(Color.BLACK);

        for (var pit : view.getBoardPanel().getPitButtons()) {
            pit.setColor(Color.BLACK, Color.WHITE, Color.GREEN, Color.YELLOW, Color.BLACK, Color.WHITE, Color.BLUE,
                    Color.RED);
        }
        view.add(view.getBoardPanel(), BorderLayout.CENTER);

        JPanel top = new JPanel(new FlowLayout());
        top.setBackground(Color.BLACK);
        top.add(view.getTurnLabel());
        view.add(top, BorderLayout.NORTH);

        view.getUndoButton().setOpaque(true);

        view.getUndoButton().setBorderPainted(false);

        view.getUndoButton().setBackground(new Color(40, 40, 40));
        view.getUndoButton().setForeground(Color.WHITE);
        view.add(view.getUndoButton(), BorderLayout.SOUTH);
    }
}
