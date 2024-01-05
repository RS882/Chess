package Piece;

import java.util.ArrayList;

// Interface defining common actions for chess pieces
public interface PieceAction {

    // Checks if the move to the specified position is valid for the piece
    boolean isValidMove(int[] end);

    // Gets a list of available moves for the piece
    ArrayList<int[]> getAvailableMoves();
}
