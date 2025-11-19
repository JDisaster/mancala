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
        //Show in empty board first after syle selection
        view.displayStyleSelection();
        model.initializeBoard(0);
        view.updateBoard(model);
        view.setVisible(true);

        JDialog selectDialog = new JDialog((Frame) null, "Select Stones per Pit", true);
        selectDialog.setLayout(new FlowLayout());
        selectDialog.setSize(300, 120);
        selectDialog.setLocationRelativeTo(null);

        JLabel label = new JLabel("Choose the number of stones per pit:");
        JButton threeBtn = new JButton("3 Stones");
        JButton fourBtn = new JButton("4 Stones");
        JButton cancelBtn = new JButton("Cancel");

        final int[] stones = {4}; 

        threeBtn.addActionListener(e -> {
            stones[0] = 3;
            selectDialog.dispose();
        });

        fourBtn.addActionListener(e -> {
            stones[0] = 4;
            selectDialog.dispose();
        });

        cancelBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    null, "Exit game?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        });

        selectDialog.add(label);
        selectDialog.add(threeBtn);
        selectDialog.add(fourBtn);
        selectDialog.add(cancelBtn);
        selectDialog.setVisible(true);

     
        model.initializeBoard(stones[0]);
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
