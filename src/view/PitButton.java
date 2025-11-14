package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 * A JButton subclass that stores its pit index.
 */
public class PitButton extends JButton {

    private int index;  // pit index (0–13)
    private int stones = 0;

    public PitButton(int index) {
        this.index = index;
        setBorderPainted(false);
    }

    /**
     * Checks if index is layes Mancala
     * @return true or false accordingly
     */
    private boolean isMancala(){
        return index == 6 || index == 13;
    }
    /**
     * Get the current index 
     * @return the index its currently on
     */
    public int getIndex() {
        return index;
    }
    /**
     * Update the stones in each pit
     * @param stones the amount of stones
     */
    public void updateStones(int stones) {
        this.stones = stones;
        repaint();
    }

    /**
     * Method alters the paintComponent so now it can draw shapes for the pits and Mancalas as well as the stones
     */
    @Override 
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        int width = getWidth();
        int height = getHeight();

        //Draw mancalas
        if(isMancala()){
            g2.setColor(new Color(235,220,180));
            g2.fillRoundRect(0, 0, width, height, 60, 60);
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(2));
            g2.drawRoundRect(5,5,width - 10,height-10,60,60);
        }
        //Draw the pits
        else{
            g2.setColor(new Color(235,220,180));
            g2.fillOval(0, 0, width, height);
            g2.setColor(Color.BLACK);
            g2.drawOval(5,5,width-10,height-10);
        }
        //Draw the cirles for the stones and have it randomly be placed within the circle
        g2.setColor(Color.BLUE);
        int stoneDiameter = 10;
        int padding = 5;
        int radius = Math.min(width,height)/2-padding - stoneDiameter/2;
        int centerX = width / 2;
        int centerY = height / 2;
        Random rand = new Random();
        //Randomly pick where to place the circle (stones)
        for(int i = 0; i < stones; i++){
            double angle = rand.nextDouble() * 2 * Math.PI;
            double r = rand.nextDouble() * radius;
            int x = (int)(centerX + r * Math.cos(angle) - (stoneDiameter/2));
            int y = (int)(centerY + r * Math.sin(angle) - (stoneDiameter/2));
            g2.fillOval(x,y,stoneDiameter,stoneDiameter);
        }
    }

    //Alter contains so now the buttons could be the shape of the pits and mancala
    @Override
    public boolean contains(int x, int y){
        if (isMancala()){
            return new Rectangle(0,0,getWidth(),getHeight()).contains(x,y);
        }
        else{
            Ellipse2D circle = new Ellipse2D.Double(0,0,getWidth(),getHeight());
            return circle.contains(x,y);
        }
    }
}
