package Piece;

import java.util.Arrays;
import java.util.Objects;

abstract public class Piece implements PieceAction, PieceSpecialAction, Cloneable {
    final private PieceTypes type; // The type of the piece (queen, king, etc.)
    final private boolean color; // The color of the piece (black = true or white = false)

    final private int idOfPieceThisType; // Unique identifier for each piece
    private int[] position = null; // The current position of the piece [y, x]
    private int countOfMove; // The number of moves made by the piece
    private static int countOfPieces = 1; // Counter for pieces created

    // Constructor
    public Piece(PieceTypes type, boolean color, int[] position) {
        this.type = type;
        this.color = color;
        this.idOfPieceThisType = countOfPieces++;
        this.countOfMove = 0;
        setPosition(position);
    }

    // Getters and setters
    public int[] getPosition() {
        return this.position;
    }

    public void setPosition(int[] position) {
        this.countOfMove++;
        if (position.length == 2 && isInBoard(position)) {
            this.position = position;
        }
    }

    public PieceTypes getType() {
        return type;
    }

    public boolean getColor() {
        return color;
    }

    public int getIdOfPieceThisType() {
        return idOfPieceThisType;
    }

    public int getCountOfMove() {
        return countOfMove;
    }

    // Check if the given position is within the board
    protected boolean isInBoard(int[] arr) {
        return arr[0] >= 0 && arr[0] <= 7 && arr[1] >= 0 && arr[1] <= 7;
    }

    // Check if the move to the given position is valid
    @Override
    public boolean isValidMove(int[] end) {
        if (!isInBoard(end)) return false;
        for (int[] el : getAvailableMoves()) {
            if (Arrays.equals(el, end)) return true;
        }
        return false;
    }

    // Represent the piece by its symbol (Unicode character)
    @Override
    public String toString() {
        return PieceTypes.getChar(this.getType(), this.getColor());
    }

    // Check equality of pieces based on type, color, and ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return this.color == piece.color && this.idOfPieceThisType == piece.idOfPieceThisType && this.type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color, idOfPieceThisType);
    }

    // Clone the piece object
    @Override
    public Piece clone() {
        try {
            return (Piece) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
