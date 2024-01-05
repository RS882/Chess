package ChessBoard;

// Interface defining actions related to checking the state of the chessboard
public interface ChessBoardIsAction {

    // Checks if the specified color is in check
    boolean isCheck(boolean color);

    // Checks if the specified color is in checkmate
    // Throws CloneNotSupportedException if cloning fails
    boolean isCheckmate(boolean color) throws CloneNotSupportedException;

    // Checks if the specified color is in stalemate
    boolean isStalemate(boolean color);
}

