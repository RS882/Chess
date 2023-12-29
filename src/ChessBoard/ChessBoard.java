package ChessBoard;

import Piece.Piece;
import Pieces.*;


import java.util.ArrayList;
import java.util.Arrays;

import java.util.function.Predicate;

public class ChessBoard implements ChessBoardMove, ChessBoardAddAction {

    private Piece[][] board;

    private Piece lastMove;


    public ChessBoard() {
        this.board = new Piece[8][8];
        initialazePieces();

    }

    private void initialazePieces() {
        for (int i = 0; i < 8; i++) {
            this.board[1][i] = new Pawn(true, new int[]{1, i}, i + 1);
            this.board[6][i] = new Pawn(false, new int[]{6, i}, i + 1);
        }
        this.board[1][3] = new Rook(true, new int[]{1, 3}, 1);
        this.board[0][5] = new Rook(true, new int[]{0, 5}, 2);
        this.board[3][4] = new Rook(false, new int[]{3, 4}, 1);
        this.board[4][2] = new Rook(false, new int[]{4, 2}, 2);

        this.board[5][2] = new Knight(true, new int[]{5, 2}, 1);
        this.board[7][3] = new Knight(true, new int[]{7, 3}, 2);
        this.board[2][5] = new Knight(false, new int[]{2, 5}, 1);
        this.board[0][0] = new Knight(false, new int[]{0, 0}, 2);

        this.board[0][2] = new Bishop(true, new int[]{0, 2}, 1);
        this.board[2][2] = new Bishop(true, new int[]{2, 2}, 2);
        this.board[6][6] = new Bishop(false, new int[]{6, 6}, 1);
        this.board[1][5] = new Bishop(false, new int[]{1, 5}, 2);

        this.board[5][3] = new Queen(true, new int[]{5, 3}, 0);
        this.board[2][6] = new Queen(false, new int[]{2, 6}, 0);

        this.board[0][4] = new King(true, new int[]{0, 4}, 0);
        this.board[7][4] = new King(false, new int[]{7, 4}, 0);


//        for (int i = 0; i < 8; i++) {
//            this.board[1][i] = new Pawn(true, new int[]{1, i}, i + 1);
//            this.board[6][i] = new Pawn(false, new int[]{6, i}, i + 1);
//        }
//        this.board[0][0] = new Rook(true, new int[]{0, 0}, 1);
//        this.board[0][7] = new Rook(true, new int[]{0, 7}, 2);
//        this.board[7][0] = new Rook(false, new int[]{7, 0}, 1);
//        this.board[7][7] = new Rook(false, new int[]{7, 7}, 2);
//
//        this.board[0][1] = new Knight(true, new int[]{0, 1}, 1);
//        this.board[0][6] = new Knight(true, new int[]{0, 6}, 2);
//        this.board[7][1] = new Knight(false, new int[]{7, 1}, 1);
//        this.board[7][6] = new Knight(false, new int[]{7, 6}, 2);
//
//        this.board[0][2] = new Bishop(true, new int[]{0, 2}, 1);
//        this.board[0][5] = new Bishop(true, new int[]{0, 5}, 2);
//        this.board[7][2] = new Bishop(false, new int[]{7, 2}, 1);
//        this.board[7][5] = new Bishop(false, new int[]{7, 5}, 2);
//
//        this.board[0][3] = new Queen(true, new int[]{0, 2}, 0);
//        this.board[7][3] = new Queen(false, new int[]{7, 3}, 0);
//
//        this.board[0][4] = new King(true, new int[]{0, 4}, 0);
//        this.board[7][4] = new King(false, new int[]{7, 4}, 0);

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

        if (!piece.isValidMove(end)) return false;

        ArrayList<int[]> otherPieces = new ArrayList<>();

        for (int i = 0; i < this.board[0].length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                if (this.board[i][j] != null) {
                    otherPieces.add(this.board[i][j].getPosition());
                }
            }
        }
        for (int[] elem : otherPieces) {
            if (end[0] == elem[0] && end[1] == elem[1]) {
                if (piece.getColor() == this.board[end[0]][end[1]].getColor()) return false;
            }
        }

        int y = piece.getPosition()[0];
        int x = piece.getPosition()[1];

        if (piece instanceof Knight || piece instanceof King) {
            return true;
        } else if (piece instanceof Pawn) {

            Predicate<int[]> isPawnRL = arr -> end[0] == y + arr[0] && end[1] == x + arr[1]
                    && this.board[y + arr[0]][x + arr[1]] == null;

            Predicate<Integer> isPawn1 = dy -> end[0] == y + dy && end[1] == x && this.board[y + dy][x] != null;

            Predicate<Integer> isPawn2 = dy -> end[0] == y + dy * 2 && end[1] == x && (this.board[y + dy][x] != null
                    || this.board[y + dy * 2][x] != null);

            Predicate<int[]> isPawnEnPas = arr ->
                    y == arr[1]
                            && ((isPawnRL.test(new int[]{arr[0], 1})
                            && this.board[y][x + 1].isEnPassant()
                            && lastMove.equals(this.board[y][x + 1]))
                            || (isPawnRL.test(new int[]{arr[0], -1})
                            && this.board[y][x - 1].isEnPassant())
                            && lastMove.equals(this.board[y][x - 1]));

            int step = (piece.getColor()) ? 1 : -1;

            if (isPawnEnPas.test(new int[]{step, 4})) return true;

            if (isPawn1.test(step) || isPawn2.test(step) ||
                    isPawnRL.test(new int[]{step, 1}) || isPawnRL.test(new int[]{step, -1})) return false;

        } else if (piece instanceof Rook ||
                (piece instanceof Queen && (y == end[0] || x == end[1]))) {

            for (int[] otherPiece : otherPieces) {

                int xP = otherPiece[1];
                int yP = otherPiece[0];

                if ((y == end[0] && y == yP && xP != end[1])
                        && ((xP - end[1] > 0) != (xP - x > 0))) return false;

                if ((x == end[1] && x == xP && yP != end[0])
                        && ((yP - end[0] > 0) != (yP - y > 0))) return false;
            }
        } else if (piece instanceof Bishop || piece instanceof Queen) {
            int dx = (x - end[1] > 0) ? -1 : 1;
            int dy = (y - end[0] > 0) ? -1 : 1;
            int xV = x;
            int yV = y;
            while (xV != end[1] && yV != end[0]) {
                for (int[] otherPiece : otherPieces) {
                    if (xV == otherPiece[1] && yV == otherPiece[0]) return false;
                }
                xV += dx;
                yV += dy;
            }

        }
        return piece.isValidMove(end);
    }

    public boolean isMoveValid() {
        return isMoveValid(
                new Queen(true, new int[]{3, 3},
                        0), new int[]{2, 3});
    }

    @Override
    public void movePiece(Piece piece, int[] end) {
        this.lastMove = piece;

        if (isMoveValid(piece, end)) {
            int x = end[1];
            int y = end[0];
            System.out.printf("%s moves %s -> %s.%n",
                    piece.getType(),
                    coverNubToCnessCord(piece.getPosition()),
                    coverNubToCnessCord(end));
            if (this.board[y][x] != null) capturing(piece, this.board[y][x]);

            this.board[piece.getPosition()[0]][piece.getPosition()[1]] = null;
            this.board[y][x] = piece;
            piece.setPosition(new int[]{y, x});


        }
    }
    public void movePiece() {
        movePiece(
        new Rook(false, new int[]{3, 3}, 1),
                new int[]{5, 3});
    }


    @Override
    public void capturing(Piece move, Piece take) {
        System.out.printf("%s takes %s on %s.%n",
                move.getType(),
                take.getType(),
                coverNubToCnessCord(take.getPosition()));
    }

    public static String coverNubToCnessCord(int[] arr) {
        char[] xArr = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        char[] yArr = {'8', '7', '6', '5', '4', '3', '2', '1'};
        return "" + xArr[arr[1]] + yArr[arr[0]];
    }

    @Override
    public void promotingOfPawn() {

    }

    @Override
    public void castling() {

    }
}

