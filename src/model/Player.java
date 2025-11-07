package model;

public class Player {
    private String name;
    private boolean isPlayerA;
    private int undoCount;

    public Player(String name, boolean isPlayerA){
        this.name = name;
        this.isPlayerA = isPlayerA;
        this.undoCount = 0;
    }

    public String getName(){
        return name;
    }
    public boolean isPlayerA(){
        return isPlayerA;
    }
    public int getUndoCount(){
        return undoCount;
    }
    public void incUndoCount(){
        undoCount++;
    }
    public void resetUndo(){
        undoCount = 0;
    }
}
