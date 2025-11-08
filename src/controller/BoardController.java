package controller;

import view.BoardView;
import model.BoardModel;

public class BoardController {

    BoardModel model; //model instance in controller
    BoardView view; //view instance in controller

    /**
     * Creates a controller with model and view components and
     * connects functionality of model to GUI of view
     * 
     * @param model - model of the program
     * @param view - view (GUI) of the program
     */
    public BoardController(BoardModel model, BoardView view) {
        this.model = model;
        this.view = view;

        view.undoButton.addActionListener(e -> undo());
    }

    /**
     * "Starts" the program by setting the view visible
     */
    public void start() {
        view.setVisible(true);
    }

    /**
     * Undos the last move made by the current player; fails if the current player's
     * last move was an undo, if the current player has already undone actions three times,
     * or if there is no previous state to revert back to 
     */
    public void undo() {
        if (model.getUndoLastMove()) {
            view.displayNotif("Undo not allowed twice in a row");
            return;
        }
        if (model.getCurrentPlayer().getUndoCount() >= 3) {
            view.displayNotif("Undo limit reached for " + model.getCurrentPlayer().getName());
            return;
        }
        if (model.getPreviousBoard() != null) {
            model.setBoard(model.getPreviousBoard());
            model.getCurrentPlayer().incUndoCount();
            model.setUndoLastMove(true);
        } else {
            view.displayNotif("No undo possible");
        }
    }
}
