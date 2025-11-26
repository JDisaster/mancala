/**
 * @author Jacob Thomas
 * @version 1.0
 */

package style;

import view.BoardView;

/**
 * Defines the contract for visual styles applied to the Mancala Board
 * Each style uses this contract and must apply it to work with BoardView
 */

public interface BoardStyle {
    void initView(BoardView view); // the way in which the view is initialized
}