package Pieces;

import Piece.Piece;
import Piece.PieceTypes;

import java.util.ArrayList;
import java.util.Arrays;

public class Rook extends Piece {
    public Rook(boolean color, int[] position) {
        super(PieceTypes.ROOK, color, position);
    }

    @Override
    public ArrayList<int[]> getAvailableMoves() {
        ArrayList<int[]> res = new ArrayList<>();
        int x = this.getPosition()[0];
        int y = this.getPosition()[1];

        for (int i = 0; i < 8; i++) {
            int[] end = new int[]{i, y};
            if (!Arrays.equals(end, this.getPosition()) && this.isInBoard(end)) res.add(end);
            end = new int[]{x, i};
            if (!Arrays.equals(end, this.getPosition()) && this.isInBoard(end)) res.add(end);
        }
        return res;
    }

    @Override
    public boolean isEnPassant() {
        return false;
    }

    @Override
    public boolean isCastling() {
        return getCountOfMove() == 1;
    }

    @Override
    public boolean isPromotion() {
        return false;
    }
}
