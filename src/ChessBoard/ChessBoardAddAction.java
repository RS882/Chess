package ChessBoard;

import Piece.*;
import Pieces.Pawn;
import Pieces.Rook;

// Interface defining additional actions that can be performed on a chessboard
public interface ChessBoardAddAction {

    // Captures a piece on the chessboard
    String capturing(Piece move, Piece take);

    // Promotes a pawn on the chessboard to a specified piece type
    void promotingOfPawn(Pawn proPawn, String type);

    // Performs castling on the chessboard for a specified color and rook
    String castling(boolean color, Rook rook);
}
