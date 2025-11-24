package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * A JButton subclass that stores its pit index.
 * This JButton represents a single put or mancala
 * This class holds the physical properties for both the pits, mancala, and stones 
 * 
 * Author: Aidan Zheng, Kwonjae Lee, Jacob Thomas
 */
public class PitButton extends JButton {

    private int index;  // pit index (0–13)
    private int stones = 0;
    private Color pitColor = new Color(235,220,180);
    private Color stoneColor = new Color(144,213,255);
    private Color outlineAColor = Color.BLACK;
    private Color outlineBColor = Color.BLACK;
    private Color stoneOutline = Color.BLACK;
    private Color labelColor = Color.BLACK;
    private Color MancalaAColor = Color.BLACK;
    private Color MancalaBColor = Color.BLACK;

    public PitButton(int index) {
        this.index = index;
        setBorderPainted(false);
    }

    public void setColor(Color pit, Color stone, Color outlineA, Color outlineB, Color StoneOut, Color label, Color manA, Color manB){
        this.pitColor = pit;
        this.stoneColor = stone;
        this.outlineAColor = outlineA;
        this.outlineBColor = outlineB;
        this.stoneOutline = StoneOut;
        this.labelColor = label;
        this.MancalaAColor = manA;
        this.MancalaBColor = manB;
        repaint();
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
        String label = "";

        //Draw mancalas
        if(isMancala()){
            g2.setColor(pitColor);
            g2.setStroke(new BasicStroke(2));
            if(index == 6){
                g2.fillRoundRect(0, 0, width-40, height, 60, 60);
                g2.setColor(MancalaAColor);
                g2.drawRoundRect(width-115,5,width - 50,height-10,60,60);

            }
            if(index == 13){
                g2.fillRoundRect(width-85, 0, width-40, height, 60, 60);
                g2.setColor(MancalaBColor);
                g2.drawRoundRect(width-80,5,width - 50,height-10,60,60);
            }
            
            //Label the Mancala pits in vertical orientation
            String vertText = "";
            int labX = 0;
            int labY = 20;
            if(index == 6){
                vertText = "MANCALA A";
                labX = width-20;
            }
            else{
                vertText = "MANCALA B";
                labX = 5;
            }
            for(int i = 0; i<vertText.length(); i++){
                char c = vertText.charAt(i);
                g2.setColor(labelColor);
                g2.setFont(new Font("Arial", Font.BOLD, 15));
                g2.drawString(String.valueOf(c),labX,labY + i * 15);
            }
        }
        //Draw the pits
        else{
            int circleD = Math.min(width,height-20);
            int circleX = (width-circleD)/2;
            int circleY = 14;

            g2.setColor(pitColor);
            g2.fillOval(circleX, circleY, circleD, circleD);
            if(index < 7){
                g2.setColor(outlineAColor);
                g2.drawOval(circleX+5,circleY+5,circleD-10,circleD-10);
            }
            else{
                g2.setColor(outlineBColor);
                g2.drawOval(circleX+5,circleY+5,circleD-10,circleD-10);
            }
            
            
            
            //Create labels for each pit
            //g2.setColor(outlineColor);
            int labelX = 0;
            int labelY = 0;

            if(index<=5){
                label = "A" + (index + 1);
                labelX = width/2 - 10;
                labelY = height-12;
            }
            else{
               label = "B" + (index - 6);
               labelX =width/2 - 8;
               labelY = 12;
            }
            g2.setColor(labelColor);
            g2.setFont(new Font("Arial", Font.BOLD, 15));
            g2.drawString(label, labelX, labelY);
        }
        //Draw the cirles for the stones and have it randomly be placed within the circle
        g2.setColor(stoneColor);
        int stoneDiameter = 10;
        int padding = 2;
        int mPitX;
        int mPitY;
        int mPitH;
        if(isMancala()){
            mPitY = 0;
            mPitH = height;
            if(index == 6){mPitX = 0;}
            else{mPitX = width - 85;}
            int arc = 60;
            int cor = arc/2 + padding;
            int drawX = mPitX + padding;
            int drawY = mPitY + cor;
            int drawH = mPitH - 2 * cor;
            int stonesPerCol = Math.max(1,drawH / (stoneDiameter + padding)); //Number of stones to fit in each column

            //Draw circles down a line in mancala until no space then make new row
            for(int i = 0; i < stones; i++){
                int col = i/stonesPerCol;
                int row = i%stonesPerCol;
                int x = drawX + col * (stoneDiameter + padding) + (padding*3);
                int y = drawY + row * (stoneDiameter + padding);
                g2.setColor(stoneColor);
                g2.fillOval(x,y,stoneDiameter+1,stoneDiameter+1);
                g2.setColor(stoneOutline);
                g2.setStroke(new BasicStroke(1));
                g2.drawOval(x, y, stoneDiameter, stoneDiameter);
            }
        }
        else{
            //Draw stones in pits
            int circleD = Math.min(width,height-20);
            int circleX = (width-circleD)/2;
            int circleY = 14;
            int pitCenterX = circleX + circleD /2;
            int pitCenterY = circleY + circleD /2;
            int spacing = 2;
            int stonesLeft = stones;

            //Draw the inital stone to be circled
            if(stonesLeft > 0){
                int x = pitCenterX - stoneDiameter/2;
                int y = pitCenterY - stoneDiameter/2;
                g2.setColor(stoneColor);
                g2.fillOval(x,y,stoneDiameter+1,stoneDiameter+1);
                g2.setColor(stoneOutline);
                g2.setStroke(new BasicStroke(1));
                g2.drawOval(x, y, stoneDiameter, stoneDiameter);
                stonesLeft--;
            }

            //Create rings around the stone for easier counting and no over laps
            int ringStep = stoneDiameter + spacing;
            int ring = 1;
            while(stonesLeft > 0){

                int rad = ring * ringStep;

                double circum = 2*Math.PI*rad;
                int maxStoneRing = Math.max(1,(int)(circum / (stoneDiameter+spacing))); //Num of stones to fit in a ring size
                int stoneCurrRing = Math.min(stonesLeft, maxStoneRing); //The amount of stones to be in current ring

                for(int i = 0; i < stoneCurrRing; i++){
                    double angle = (2 * Math.PI /stoneCurrRing) * i;
                    int x = (int)(pitCenterX + rad * Math.cos(angle) - (stoneDiameter/2));
                    int y = (int)(pitCenterY + rad * Math.sin(angle) - (stoneDiameter/2));
                    g2.setColor(stoneColor);
                    g2.fillOval(x,y,stoneDiameter+1,stoneDiameter+1);
                    g2.setColor(stoneOutline);
                    g2.setStroke(new BasicStroke(1));
                    g2.drawOval(x, y, stoneDiameter, stoneDiameter);
                }
                stonesLeft -= stoneCurrRing;
                ring++;
            }
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

    /**
     * Get the amount of stones in pit
     * @return the amount of stones in pit
     */
    public int getStoneCount(){
        return stones;
    }
}
