package Piece;

// Interface for special actions related to chess pieces
public interface PieceSpecialAction {

    // Checks if the move is an en passant
    boolean isEnPassant();

    // Checks if the move is castling
    boolean isCastling();

    // Checks if the move leads to a pawn promotion
    boolean isPromotion();
}

