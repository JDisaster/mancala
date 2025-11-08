package controller;

import view.BoardView;
import model.BoardModel;

public class BoardController {

    BoardModel model;
    BoardView view;

    public BoardController(BoardModel model, BoardView view) {
        this.model = model;
        this.view = view;

        //add action listeners here
    }

    public void start() {
        view.setVisible(true); //put this here for stricter mvc style, can be in view constructor as well
    }

}
