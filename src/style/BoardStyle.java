package style;

import view.BoardView;

/**
 * Defines the contract for visual styles applied to the Mancala Board
 * Each style uses this contract and must apply it to work with BoardView
 * 
 * Author: Jacob Thomas
 */

public interface BoardStyle {
    void initView(BoardView view);
}