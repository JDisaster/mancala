package style;

import view.BoardView;
import javax.swing.*;
import java.awt.*;

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
            pit.setColor(Color.BLACK, new Color(157, 0, 255), new Color(0,240,255),Color.WHITE, Color.WHITE);
        }
        view.add(view.getBoardPanel(), BorderLayout.CENTER);

        JPanel top = new JPanel(new FlowLayout());
        top.add(view.getTurnLabel());
        view.add(top, BorderLayout.NORTH);

        view.add(view.getUndoButton(), BorderLayout.SOUTH); 
    }
}
