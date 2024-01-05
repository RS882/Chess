package ChessBoard;

import Piece.Piece;

// Interface defining actions related to moving pieces on the chessboard
public interface ChessBoardMove {

    // Moves the specified piece to the given end coordinates
    String movePiece(Piece piece, int[] end);
}
