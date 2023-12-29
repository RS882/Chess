package Pieces;

import Piece.Piece;
import Piece.PieceTypes;


import java.util.ArrayList;

public class Pawn extends Piece {


    public Pawn(boolean color, int[] position, int idOfPieceThisType) {
        super(PieceTypes.PAWN, color, position, idOfPieceThisType);
    }


    @Override
    public ArrayList<int[]> getAvailableMoves() {
        ArrayList<int[]> res = new ArrayList<>();

        int x = this.getPosition()[0];
        int y = this.getPosition()[1];

        if (this.getColor()) {
            if (x == 1) {
                int[] end = new int[]{x + 2, y};
                if (this.isInBoard(end)) res.add(end);
            }
            int[] end = new int[]{x + 1, y};
            if (this.isInBoard(end)) res.add(end);
            end = new int[]{x + 1, y + 1};
            if (this.isInBoard(end)) res.add(end);
            end = new int[]{x + 1, y - 1};
            if (this.isInBoard(end)) res.add(end);

        } else {
            if (x == 6) {
                int[] end = new int[]{x - 2, y};
                if (this.isInBoard(end)) res.add(end);
            }
            int[] end = new int[]{x - 1, y};
            if (this.isInBoard(end)) res.add(end);
            end = new int[]{x - 1, y + 1};
            if (this.isInBoard(end)) res.add(end);
            end = new int[]{x - 1, y - 1};
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
