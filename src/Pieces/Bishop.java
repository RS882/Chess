package Pieces;

import Piece.Piece;
import Piece.PieceTypes;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(boolean color, int[] position) {
        super(PieceTypes.BISHOP, color, position);
    }

    @Override
    public ArrayList<int[]> getAvailableMoves() {
        ArrayList<int[]> res = new ArrayList<>();

        int[][] posMoves = {
                {-1, -1},
                {-1, 1},
                {1, -1},
                {1, 1},
        };
        for (int[] elem : posMoves) {
            int xNew = this.getPosition()[0] + elem[0];
            int yNew = this.getPosition()[1] + elem[1];
            while (isInBoard(new int[]{xNew, yNew})) {
                res.add(new int[]{xNew,yNew});
                xNew += elem[0];
                yNew += elem[1];
            }
        }
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
