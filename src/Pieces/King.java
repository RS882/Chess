package Pieces;

import Piece.Piece;
import Piece.PieceTypes;

import java.util.ArrayList;
import java.util.Arrays;

public class King extends Piece {

    private boolean check;


    public King(boolean color, int[] position) {
        super(PieceTypes.KING, color, position);
        this.check = true;

    }

    @Override
    public ArrayList<int[]> getAvailableMoves() {
        ArrayList<int[]> res = new ArrayList<>();

        int x = this.getPosition()[0];
        int y = this.getPosition()[1];

        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                int[] end = new int[]{j, i};
                if (!Arrays.equals(end, this.getPosition()) && this.isInBoard(end)) {
                    //System.out.println(Arrays.toString(end));
                    res.add(end);
                }
            }
        }

        return res;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean getCheck() {
        return this.check;
    }

    @Override
    public boolean isEnPassant() {
        return false;
    }

    @Override
    public boolean isCastling() {
        return getCountOfMove() == 1 && !this.check;
    }

    @Override
    public boolean isPromotion() {
        return false;
    }
}
