package Pieces;

import Piece.Piece;
import Piece.PieceTypes;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(boolean color, int[] position, int idOfPieceThisType) {
        super(PieceTypes.QUEEN, color, position, idOfPieceThisType);
    }

    @Override
    public ArrayList<int[]> getAvailableMoves() {
        Bishop bishop = new Bishop(this.getColor(), this.getPosition(), this.getIdOfPieceThisType());
        Rook rook = new Rook(this.getColor(), this.getPosition(), this.getIdOfPieceThisType());
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
