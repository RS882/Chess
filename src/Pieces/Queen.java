package Pieces;

import Piece.Piece;
import Piece.PieceTypes;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(boolean color, int[] position) {
        super(PieceTypes.QUEEN, color, position);
    }

    @Override
    public ArrayList<int[]> getAvailableMoves() {
        Bishop bishop = new Bishop(this.getColor(), this.getPosition());
        Rook rook = new Rook(this.getColor(), this.getPosition());
        ArrayList<int[]> res = bishop.getAvailableMoves();
        res.addAll(rook.getAvailableMoves());

        return res;
    }


    @Override
    public boolean isEnPassant() {
        return false;
    }

    @Override
    public boolean isCastling() {
        return false;
    }

    @Override
    public boolean isPromotion() {
        return false;
    }
}
