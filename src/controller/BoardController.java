/**
 * @author Aidan Zheng, Kwonjae Lee, Jacob Thomas
 * @version 1.0
 */

package controller;

import view.BoardView;
import view.PitButton;

import model.BoardModel;

/**
 * Controller for coordinating interactions between the BoardModel and Boardview
 * in the application. Wires user actions from the GUI to the game logic.
 * Initializes game, handles pit selections, and manages undo function
 */

public class BoardController {

    private BoardModel model;
    private BoardView view;

    /**
     * Creates a controller with model and view components and
     * connects functionality of model to GUI of view
     * 
     * @param model - The model object to use
     * @param view  - The view object to use
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
     * Starts the program by initializing the model and
     * view and displaying the view
     */
    public void start() {
        // Show an empty board first after style selection
        view.displayStyleSelection();
        model.initializeBoard(0);
        view.updateBoard(model);
        view.setVisible(true);

        int stones = view.displayStoneCount();

        model.initializeBoard(stones);
        view.updateBoard(model);
    }

    /**
     * Handles the Undo button action
     */
    public void undo() {
        if (!model.canUndo()) {
            view.displayNotif("Undo not allowed (either already undone, limit reached, or no saved state)");
            return;
        }
        model.undo();
        view.updateBoard(model);
    }

    /**
     * Handles when a pit button is clicked
     * 
     * @param pitIndex - The index of the pit that was clicked
     */
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
