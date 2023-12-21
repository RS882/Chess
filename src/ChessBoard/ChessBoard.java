package ChessBoard;

import Piece.Piece;

import java.util.Arrays;

public class ChessBoard {

    private Piece[][] board;

    public ChessBoard() {
        this.board = new Piece[8][8];
    }
    public Piece[][] getBoard() {
        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("ChessBoard{");
        res.append(String.format("%nboard:%n}%n"));
        for (Piece [] arr: this.board ) {
            res.append(Arrays.toString(arr));
            res.append(String.format("%n"));
        }
        res.append(String.format("}%n"));
        return String.valueOf(res);
    }
}
