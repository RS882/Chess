package Piece;

// Enum defining types of chess pieces along with their Unicode representations for black and white colors
public enum PieceTypes {
    QUEEN("\u265B", "\u2655"),
    KING("\u265A", "\u2654"),
    PAWN("\u265F", "\u2659"),
    ROOK("\u265C", "\u2656"),
    KNIGHT("\u265E", "\u2658"),
    BISHOP("\u265D", "\u2657");

    private String charForBlack; // Unicode representation for black pieces
    private String charForWhite; // Unicode representation for white pieces

    // Constructor for PieceTypes enum
    PieceTypes(String charForBlack, String charForWhite) {
        this.charForBlack = charForBlack;
        this.charForWhite = charForWhite;
    }

    // Returns the Unicode character representation of the piece based on its color
    public static String getChar(PieceTypes piece, boolean color) {
        return (color) ? piece.charForBlack : piece.charForWhite;
    }
}
