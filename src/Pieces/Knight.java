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


        int x =this.getPosition()[0];
        int y =this.getPosition()[1];

        int[] end = new int[]{ x - 2, y + 1};
        if (this.isInBoard(end)) res.add(end);

         end = new int[]{ x - 1, y + 2};
        if (this.isInBoard(end)) res.add(end);

         end = new int[]{ x + 1, y + 2};
        if (this.isInBoard(end)) res.add(end);

        end = new int[]{ x + 2, y + 1};
        if (this.isInBoard(end)) res.add(end);

        end = new int[]{ x + 2, y - 1};
        if (this.isInBoard(end)) res.add(end);

        end = new int[]{ x + 1, y - 2};
        if (this.isInBoard(end)) res.add(end);

        end = new int[]{ x - 2, y - 1};
        if (this.isInBoard(end)) res.add(end);

        end = new int[]{ x - 1, y - 2};
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
