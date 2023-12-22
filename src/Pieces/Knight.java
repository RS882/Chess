package Pieces;

import Piece.Piece;
import Piece.PieceTypes;
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight( boolean color, int[] position) {
        super(PieceTypes.KNIGHT, color, position);
    }

    @Override
    public ArrayList<int[]> getAvailableMoves() {
        ArrayList<int[]> res = new ArrayList<>();
        int[] start = this.getPosition();

        int[] end = new int[]{start[0] - 2, start[1] + 1};
        if (this.isInBoard(end)) res.add(end);

         end = new int[]{start[0] - 1, start[1] + 2};
        if (this.isInBoard(end)) res.add(end);

         end = new int[]{start[0] + 1, start[1] + 2};
        if (this.isInBoard(end)) res.add(end);

        end = new int[]{start[0] + 2, start[1] + 1};
        if (this.isInBoard(end)) res.add(end);

        end = new int[]{start[0] + 2, start[1] - 1};
        if (this.isInBoard(end)) res.add(end);

        end = new int[]{start[0] + 1, start[1] - 2};
        if (this.isInBoard(end)) res.add(end);

        end = new int[]{start[0] - 2, start[1] - 1};
        if (this.isInBoard(end)) res.add(end);

        end = new int[]{start[0] - 1, start[1] - 2};
        if (this.isInBoard(end)) res.add(end);

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
