package Piece;

public enum PieceTypes {
    PAWN("\u265F","\u2659"),
    ROOK("\u265C","\u2656"),
    KNIGHT("\u265E","\u2658"),
    BISHOP("\u265D","\u2657"),
    QUEEN("\u265B","\u2655"),
    KING("\u265A","\u2654") ;

   private String  charForBlack;

    private String  charForWhite;

    PieceTypes(String charForBlack, String charForWhite) {
        this.charForBlack = charForBlack;
        this.charForWhite = charForWhite;
    }

    public static String getChar(PieceTypes piece, boolean color) {
        return (color)? piece.charForBlack: piece.charForWhite;
    }


}
