package ChessBoard;

import Piece.Piece;
import Pieces.*;

import java.util.Arrays;

public class ChessBoard {

    private Piece[][] board;
    private Piece[] pieces;

    public ChessBoard() {
        this.board = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(true, new int[]{1, i}, i + 1);
            board[6][i] = new Pawn(false, new int[]{6, i}, i + 1);
        }
        board[0][0] = new Rook(true,new int[]{0,0},1 );
        board[0][7] = new Rook(true,new int[]{0,7},2 );
        board[7][0] = new Rook(false,new int[]{7,0},1 );
        board[7][7] = new Rook(false,new int[]{7,7},2 );

        board[0][1] = new Knight(true,new int[]{0,1},1 );
        board[0][6] = new Knight(true,new int[]{0,6},2 );
        board[7][1] = new Knight(false,new int[]{7,1},1 );
        board[7][6] = new Knight(false,new int[]{7,6},2 );

        board[0][2] = new Bishop(true,new int[]{0,2},1 );
        board[0][5] = new Bishop(true,new int[]{0,5},2 );
        board[7][2] = new Bishop(false,new int[]{7,2},1 );
        board[7][5] = new Bishop(false,new int[]{7,5},2 );

        board[0][3] = new Queen(true,new int[]{0,2},0 );
        board[7][3] = new Queen(false,new int[]{7,3},0 );

        board[0][4] = new King(true,new int[]{0,4},0 );
        board[7][4] = new King(false,new int[]{7,4},0 );
    }

    public Piece[][] getBoard() {
        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public String displayBoard() {
        StringBuilder res = new StringBuilder();
        for (char i = 'a'; i <='h' ; i++) {
            System.out.println(i+ " ");
        }
        return "displayBoard";
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("ChessBoard{");
        res.append(String.format("%nboard:%n}%n"));
        for (Piece[] arr : this.board) {
            res.append(Arrays.toString(arr));
            res.append(String.format("%n"));
        }
        res.append(String.format("}%n"));
        return String.valueOf(res);
    }
}
