package view;

import javax.swing.*;
import java.awt.*;

/**
 * BoardPanel is responsible for drawing the mancala game layout
 * It represents the pits and mancala
 */
public class BoardPanel extends JPanel {
    private final PitButton[] pits = new PitButton[14];

    public BoardPanel(){
        createPitButtons();
    }

    public void createPitButtons(){
        int pitWidth = 60;
        int pitHeight = 80;
        int space = 20;
        int startCoorX = 150;
        int startCoorY = 100;

        //Player B pits (top row)
        for(int i = 12; i>=7; i--){
            int x = startCoorX + (12-i)*(pitWidth + space);
            pits[i] = new PitButton(i);
            pits[i].setBounds(x,startCoorY,pitWidth,pitHeight);
            add(pits[i]);
        }

        //Player A pits (Bot row)
        for(int i = 0; i < 6; i++){
            int x = startCoorX + i*(pitWidth + space);
            pits[i] = new PitButton(i);
            pits[i].setBounds(x,startCoorY + pitHeight + space,pitWidth,pitHeight);
            add(pits[i]);
        }

        //Mancala player A
        pits[6] = new PitButton(6);
        pits[6].setBounds(startCoorX - pitWidth - space, startCoorY, pitWidth, pitHeight * 2 + space);
        add(pits[6]);

        //Mancala player B
        pits[13] = new PitButton(13);
        pits[13].setBounds(startCoorX + 6 * (pitWidth + space), startCoorY, pitWidth, pitHeight * 2 + space);
        add(pits[13]);
    }
    public PitButton[] getPitButtons(){
        return pits;
    }

    public void updateBoard(int[] board){
        for(int i = 0 ; i < board.length; i++){
            pits[i].updateStones(board[i]);
        }
    }
}
