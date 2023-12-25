package ChessBoard;

import Piece.Piece;
import Pieces.*;

import java.util.Arrays;

public class ChessBoard implements ChessBoardMove{

    private Piece[][] board;


    public ChessBoard() {
        this.board = new Piece[8][8];
        initialazePieces();

    }

    private void initialazePieces() {
        for (int i = 0; i < 8; i++) {
            this.board[1][i] = new Pawn(true, new int[]{1, i}, i + 1);
            this.board[6][i] = new Pawn(false, new int[]{6, i}, i + 1);
        }
        this.board[0][0] = new Rook(true, new int[]{0, 0}, 1);
        this.board[0][7] = new Rook(true, new int[]{0, 7}, 2);
        this.board[7][0] = new Rook(false, new int[]{7, 0}, 1);
        this.board[7][7] = new Rook(false, new int[]{7, 7}, 2);

        this.board[0][1] = new Knight(true, new int[]{0, 1}, 1);
        this.board[0][6] = new Knight(true, new int[]{0, 6}, 2);
        this.board[7][1] = new Knight(false, new int[]{7, 1}, 1);
        this.board[7][6] = new Knight(false, new int[]{7, 6}, 2);

        this.board[0][2] = new Bishop(true, new int[]{0, 2}, 1);
        this.board[0][5] = new Bishop(true, new int[]{0, 5}, 2);
        this.board[7][2] = new Bishop(false, new int[]{7, 2}, 1);
        this.board[7][5] = new Bishop(false, new int[]{7, 5}, 2);

        this.board[0][3] = new Queen(true, new int[]{0, 2}, 0);
        this.board[7][3] = new Queen(false, new int[]{7, 3}, 0);

        this.board[0][4] = new King(true, new int[]{0, 4}, 0);
        this.board[7][4] = new King(false, new int[]{7, 4}, 0);

    }


    public Piece[][] getBoard() {
        return this.board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public void displayBoard() {

        // Displaying the chess board
        System.out.println("   a  b  c d  e f  g  h");
        for (int i = 0; i < 8; i++) {
            System.out.print(8 - i + " ");
            for (int j = 0; j < 8; j++) {
                if (this.board[i][j] != null) System.out.print("|" + this.board[i][j]);
                else System.out.print("|\u2003");
            }
            System.out.println("| " + (8 - i));
        }
        System.out.println("   a  b  c d  e f  g  h");


    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("ChessBoard{");
        res.append(String.format("%nboard:%n}%n"));
        for (Piece[] arr : this.board) {

            if (arr == null) res.append("1");
            else res.append(Arrays.toString(arr));
            res.append(String.format("%n"));
        }
        res.append(String.format("}%n"));
        return String.valueOf(res);
    }

    @Override
    public boolean isMoveValid(Piece piece, int[] end) {
        if(piece.isValidMove(end)){

        }
        return false;
    }

    @Override
    public void movePiece(Piece piece, int[] end) {

    }
}
