package controller;

import view.BoardView;
import view.PitButton;

import javax.swing.*;
import java.awt.*;

import model.BoardModel;

public class BoardController {

    private BoardModel model;
    private BoardView view;

    /**
     * Creates a controller with model and view components and
     * connects functionality of model to GUI of view.
     */
    public BoardController(BoardModel model, BoardView view) {
        this.model = model;
        this.view = view;

        // Undo button listener
        view.undoButton.addActionListener(e -> undo());

        // Pit button listeners
        for (PitButton pit : view.getPitButtons()) {
            pit.addActionListener(e -> handlePitClick(pit.getIndex()));
        }
    }

    /** "Starts" the program by setting the view visible. */
    public void start() {
        //Show an empty board first after syle selection
        view.displayStyleSelection();
        model.initializeBoard(0);
        view.updateBoard(model);
        view.setVisible(true);

        int stones = view.displayStoneCount();
     
        model.initializeBoard(stones);
        view.updateBoard(model);
    }

    /** Handles the Undo button action. */
    public void undo() {
        if (!model.canUndo()) {
            view.displayNotif("Undo not allowed (either already undone, limit reached, or no saved state)");
            return;
        }
        model.undo();
        view.updateBoard(model);
    }

    /** Handles when a pit button is clicked. */
    private void handlePitClick(int pitIndex) {
        if (!model.isValidPit(pitIndex)) {
            view.displayNotif("Invalid pit selected!");
            return;
        }

        model.makeMove(pitIndex);
        view.updateBoard(model);

        if (model.isGameOver()) {
            view.displayNotif("Game Over!");
        }
    }
}
