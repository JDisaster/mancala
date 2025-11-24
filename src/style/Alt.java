package style;

import view.BoardView;
import javax.swing.*;
import java.awt.*;

/**
 * Alternative visual style that the players can choose
 * This implements a neon style game board utilizing fun colors 
 * 
 * Author: Aidan Zheng, Jacob Thomas
 */

public class Alt implements BoardStyle {
    @Override
    public void initView(BoardView view){
        view.setTitle("Neon Mancala Style");
        view.setSize(800,400);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setLayout(new BorderLayout());
        view.getContentPane().setBackground(Color.BLACK);
        view.getBoardPanel().setBackground(Color.BLACK);

        for(var pit : view.getBoardPanel().getPitButtons()){
            pit.setColor(Color.BLACK, Color.WHITE, Color.GREEN, Color.YELLOW, Color.BLACK, Color.WHITE, Color.BLUE, Color.RED);
        }
        view.add(view.getBoardPanel(), BorderLayout.CENTER);

        JPanel top = new JPanel(new FlowLayout());
        top.setBackground(Color.BLACK);
        top.add(view.getTurnLabel());
        view.add(top, BorderLayout.NORTH);

        view.getUndoButton().setOpaque(true);

        //view.getUndoButton().setContentAreaFilled(false);
        view.getUndoButton().setBorderPainted(false);

        view.getUndoButton().setBackground(new Color(40,40,40));
        view.getUndoButton().setForeground(Color.WHITE);
        view.add(view.getUndoButton(), BorderLayout.SOUTH); 
        //new Color(157, 0, 255)
        //new Color(0,240,255)
    }
}
