package style;

import javax.swing.*;
import java.awt.*;

import view.BoardView;

public class Alt implements BoardStyle {
    public void initView(BoardView view) {
        view.setTitle("Alt Mancala Game Style");
        view.setSize(800, 400);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setLayout(new BorderLayout());

        view.add(view.getBoardPanel(), BorderLayout.CENTER);

        JPanel top = new JPanel(new FlowLayout());
        top.add(view.getTurnLabel());
        view.add(top, BorderLayout.SOUTH);

        view.add(view.getUndoButton(), BorderLayout.NORTH); 
    }
}