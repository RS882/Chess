package Pieces;

import Piece.Piece;
import Piece.PieceTypes;


import java.util.ArrayList;

public class Pawn extends Piece {


    public Pawn(boolean color, int[] position) {
        super(PieceTypes.PAWN, color, position);
    }

    @Override
    public ArrayList<int[]> getAvailableMoves() {
        ArrayList<int[]> res = new ArrayList<>();
        int[] start = this.getPosition();

        if (this.getColor()) {
            if (start[0] == 1) {
                int[] end = new int[]{start[0] + 2, start[1]};
                if (this.isInBoard(end)) res.add(end);
            }
            int[] end = new int[]{start[0] + 1, start[1]};
            if (this.isInBoard(end)) res.add(end);

        } else {
            if (start[0] == 6) {
                int[] end = new int[]{start[0] - 2, start[1]};
                if (this.isInBoard(end)) res.add(end);
            }
            int[] end = new int[]{start[0] - 1, start[1]};
            if (this.isInBoard(end)) res.add(end);
        }
        return res;
    }

    @Override
    public boolean isEnPassant() {
        if (getCountOfMove() == 2) {
            if ((getColor() && getPosition()[0] == 3) || (!getColor() && getPosition()[0] == 4)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isCastling() {
        return false;
    }

    @Override
    public boolean isPromotion() {
        if ((getColor() && getPosition()[0] == 7) || (!getColor() && getPosition()[0] == 0)) {
            return true;
        }
        return false;
    }
}
