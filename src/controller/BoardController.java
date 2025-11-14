package controller;

import view.BoardView;
import view.PitButton;

import javax.swing.*;

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

    /** 
     * Starts the program by getting user input to determine style and stones per pit (defaulting to 4),
     * initializing the board, notifying the view, and finally displaying the view
    */
    public void start() {
        String input = JOptionPane.showInputDialog(
            "Enter the number of stones per pit (3 or 4):"
        );
        int stonesPerPit = 4; // default
        try {
            int inputNum = Integer.parseInt(input);
            if (inputNum == 3 || inputNum == 4) {
                stonesPerPit = inputNum;
            }
        } catch (Exception e) {
            view.displayNotif("Invalid input, defaulting to 4 stones per pit");
        }
        model.initializeBoard(stonesPerPit);
        view.displayStyleSelection();
        view.updateBoard(model);
        view.setVisible(true);
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
