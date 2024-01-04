package ChessBoard;

import Piece.*;
import Pieces.*;


import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ChessBoard implements ChessBoardMove, ChessBoardAddAction, ChessBoardIsAction {

    private Piece[][] board;

    private Piece lastMove;

    private boolean colorOfMove;

    private Pawn pawnEnPas;


    public ChessBoard() {
        this.board = new Piece[8][8];
        initialazePieces();


    }

    private void initialazePieces() {
        initialazePieces(this.board);
    }

    private void initialazePieces(Piece[][] board) {

//        this.board[5][2] = new King(true, new int[]{5, 2});
//        this.board[7][2] = new King(false, new int[]{7, 2});
//        this.board[5][4] = new Knight(true, new int[]{5, 4});
//        this.board[4][2] = new Bishop(true, new int[]{4, 2});
//
//        for (int i = 0; i < 8; i++) {
//            this.board[1][i] = new Pawn(true, new int[]{1, i});
//            this.board[6][i] = new Pawn(false, new int[]{6, i});
//        }
//
//        this.board[0][3] = new Rook(true, new int[]{0, 3});
//        this.board[0][7] = new Rook(true, new int[]{0, 7});
//
//
//        this.board[5][2] = new Knight(true, new int[]{5, 2});
//        //      this.board[7][3] = new Knight(true, new int[]{7, 3}, 2);
//        this.board[2][5] = new Knight(false, new int[]{2, 5});
//        this.board[0][0] = new Knight(false, new int[]{0, 0});
//
//        this.board[0][2] = new Bishop(true, new int[]{0, 2});
//        this.board[2][2] = new Bishop(true, new int[]{2, 2});
//        this.board[6][6] = new Bishop(false, new int[]{6, 6});
//        this.board[1][5] = new Bishop(false, new int[]{1, 5});
//
//        this.board[5][3] = new Queen(true, new int[]{5, 3});
//        this.board[2][6] = new Queen(false, new int[]{2, 6});
//
//        this.board[0][4] = new King(true, new int[]{0, 4});
//        this.board[7][4] = new King(false, new int[]{7, 4});
//
//        this.board[7][0] = new Rook(false, new int[]{7, 0});
//        this.board[7][7] = new Rook(false, new int[]{7, 7});

//        for (int i = 0; i < 8; i++) {
//            board[1][i] = new Pawn(true, new int[]{1, i});
//            board[6][i] = new Pawn(false, new int[]{6, i});
//        }
        board[3][3] = new Pawn(true, new int[]{3, 3});

        board[3][4] = new Pawn(false, new int[]{3, 4});

        board[0][0] = new Rook(true, new int[]{0, 0});
        board[0][7] = new Rook(true, new int[]{0, 7});
        board[7][0] = new Rook(false, new int[]{7, 0});
        board[7][7] = new Rook(false, new int[]{7, 7});

        board[0][1] = new Knight(true, new int[]{0, 1});
        board[0][6] = new Knight(true, new int[]{0, 6});
        board[7][1] = new Knight(false, new int[]{7, 1});
        board[7][6] = new Knight(false, new int[]{7, 6});

        board[0][2] = new Bishop(true, new int[]{0, 2});
        // board[0][5] = new Bishop(true, new int[]{0, 5});
        board[7][2] = new Bishop(false, new int[]{7, 2});
        board[7][5] = new Bishop(false, new int[]{7, 5});

        board[1][5] = new Pawn(false, new int[]{1, 5});

        board[0][3] = new Queen(true, new int[]{0, 2});
        board[7][3] = new Queen(false, new int[]{7, 3});

        board[0][4] = new King(true, new int[]{0, 4});
        board[7][4] = new King(false, new int[]{7, 4});
//----------------------------------------------------
//        for (int i = 0; i < 8; i++) {
//            board[1][i] = new Pawn(true, new int[]{1, i});
//           board[6][i] = new Pawn(false, new int[]{6, i});
//        }
//        board[0][0] = new Rook(true, new int[]{0, 0});
//        board[0][7] = new Rook(true, new int[]{0, 7});
//        board[7][0] = new Rook(false, new int[]{7, 0});
//        board[7][7] = new Rook(false, new int[]{7, 7});
//
//        board[0][1] = new Knight(true, new int[]{0, 1});
//        board[0][6] = new Knight(true, new int[]{0, 6});
//        board[7][1] = new Knight(false, new int[]{7, 1});
//        board[7][6] = new Knight(false, new int[]{7, 6});
//
//        board[0][2] = new Bishop(true, new int[]{0, 2});
//        board[0][5] = new Bishop(true, new int[]{0, 5});
//        board[7][2] = new Bishop(false, new int[]{7, 2});
//        board[7][5] = new Bishop(false, new int[]{7, 5});
//
//        board[0][3] = new Queen(true, new int[]{0, 2});
//        board[7][3] = new Queen(false, new int[]{7, 3});
//
//        board[0][4] = new King(true, new int[]{0, 4});
//        board[7][4] = new King(false, new int[]{7, 4});

    }

    public boolean getColorOfMove() {
        return this.colorOfMove;
    }

    public void displayBoard() {
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

    public Piece[][] getBoard() {
        return this.board;
    }


    private boolean isMoveValid(Piece piece, int[] end) {


        if (!piece.isValidMove(end)) return false;

        ArrayList<int[]> otherPieces = new ArrayList<>();

        for (Piece[] pieces : this.board) {
            for (int j = 0; j < pieces.length; j++) {
                if (pieces[j] != null && !pieces[j].equals(piece)) {
                    otherPieces.add(pieces[j].getPosition());
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
        boolean isEnPas = false;
        if (piece instanceof Knight) {
//            return true;
        } else if (piece instanceof King) {

            Piece king2 = getPiece(PieceTypes.KING, !piece.getColor()).get(0);
            if (king2.isValidMove(end)) return false;

            return !isCheck(piece.getColor(), end);
        } else if (piece instanceof Pawn) {

            Predicate<int[]> isPawnRL = arr -> end[0] == y + arr[0] && end[1] == x + arr[1]
                    && this.board[y + arr[0]][x + arr[1]] == null;

            Predicate<Integer> isPawn1 = dy -> end[0] == y + dy && end[1] == x && this.board[y + dy][x] != null;

            Predicate<Integer> isPawn2 = dy -> end[0] == y + dy * 2 && end[1] == x && (this.board[y + dy][x] != null
                    || this.board[y + dy * 2][x] != null);

//            Predicate<Integer> isPawnEnPas = num ->
//
//                    ((isPawnRL.test(new int[]{num, 1})
//                            && (this.board[y][x + 1] != null
//                            && this.board[y][x + 1].isEnPassant())
//                            && lastMove.equals(this.board[y][x + 1]))
//
//                            || (isPawnRL.test(new int[]{num, -1})
//                            && (this.board[y][x - 1] != null
//                            && this.board[y][x - 1].isEnPassant())
//                            && lastMove.equals(this.board[y][x - 1])));

            Predicate<Integer> isPawnEnPas = num ->

                    (((this.board[y][x + 1] != null
                            && this.board[y][x + 1].isEnPassant())
                            && this.lastMove.equals(this.board[y][x + 1]))

                            || ((this.board[y][x - 1] != null
                            && this.board[y][x - 1].isEnPassant())
                            && this.lastMove.equals(this.board[y][x - 1])))

                            && Arrays.equals(this.lastMove.getPosition(), new int[]{end[0] - num, end[1]});

            int step = (piece.getColor()) ? 1 : -1;

            if (isPawn1.test(step) || isPawn2.test(step)) return false;
            else if (isPawnRL.test(new int[]{step, 1}) || isPawnRL.test(new int[]{step, -1})) {

                if (!isPawnEnPas.test(step)) return false;
                else {
                    this.pawnEnPas = (this.board[y][x - 1] != null) ? (Pawn) this.board[y][x - 1] : (Pawn) this.board[y][x + 1];
                    System.out.println(this.pawnEnPas);
                }
            }


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
            int xV = x + dx;
            int yV = y + dy;
            while (xV != end[1] && yV != end[0]) {

                for (int[] otherPiece : otherPieces) {
                    if (xV == otherPiece[1] && yV == otherPiece[0]) return false;
                }
                xV += dx;
                yV += dy;
            }
        }

        Piece king = getPiece(PieceTypes.KING, piece.getColor()).get(0);
        if (((King) king).getCheck()) {
            Piece[][] newBoard = cloneBoard(this.board);
            movePieceOneTest(piece, end, false, this.board);

            if (isCheck(piece.getColor())) {
                this.board = cloneBoard(newBoard);

                return false;
            }
            this.board = cloneBoard(newBoard);
        }
        return true;
    }


//    public boolean isMoveValid() {
//        return isMoveValid(
//                new King(true, new int[]{2, 3},
//                        0), new int[]{3, 4});
//    }

    private String movePiece(Piece piece, int[] end, boolean isSout, Piece[][] board) {

        String res = "";
        if (isMoveValid(piece, end)) {
            res = movePieceOneTest(piece, end, isSout, board);

        } else {

            if (isSout) {
                res = String.format(" %s moves to <%s> not possible!%n",
                        piece.getType(),
                        coverNumToCnessCord(end));
                Piece king = getPiece(PieceTypes.KING, piece.getColor()).get(0);
                if (((King) king).getCheck()) res += String.format("Is check!");

            }
        }
        return res;
    }

    private String movePieceOneTest(Piece piece, int[] end, boolean isSout, Piece[][] board) {
        String res = "";
        int x = end[1];
        int y = end[0];
        if (isSout) res = String.format("%s moves <%s> => <%s>.%n",
                piece.getType(),
                coverNumToCnessCord(piece.getPosition()),
                coverNumToCnessCord(end));
        if (board[y][x] != null && isSout) res += capturing(piece, board[y][x]);

        board[piece.getPosition()[0]][piece.getPosition()[1]] = null;
        board[y][x] = piece;
        piece.setPosition(new int[]{y, x});
        if (this.pawnEnPas != null) {
            int xP = this.pawnEnPas.getPosition()[1];
            int yP = this.pawnEnPas.getPosition()[0];
            res += String.format("En passant! PAWN <%s> took.",
                    coverNumToCnessCord(this.pawnEnPas.getPosition()));
            board[yP][xP] = null;
            this.pawnEnPas = null;

        }

        if (isSout) {
            this.lastMove = piece;
            this.colorOfMove = !this.colorOfMove;
        }
        return res;
    }

    private String movePiece(Piece piece, int[] end, boolean isSout) {
        return movePiece(piece, end, isSout, this.board);

    }

    @Override
    public String movePiece(Piece piece, int[] end) {
        return movePiece(piece, end, true);
    }

//    public void movePiece() {
//        movePiece(
//                new Rook(false, new int[]{3, 3}, 1),
//                new int[]{5, 3});
//    }


    @Override
    public String capturing(Piece move, Piece take) {
        return String.format("%s takes %s on <%s>.%n",
                move.getType(),
                take.getType(),
                coverNumToCnessCord(take.getPosition()));
    }

    public static String coverNumToCnessCord(int[] arr) {
        char[] xArr = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        char[] yArr = {'8', '7', '6', '5', '4', '3', '2', '1'};
        return "" + xArr[arr[1]] + yArr[arr[0]];
    }

    @Override
    public void promotingOfPawn(Pawn proPawn, String type) {

        int[] pos = proPawn.getPosition();
        boolean color = proPawn.getColor();

        Piece newPiece;
        switch (type) {
            case "ROOK":
                newPiece = new Rook(color, pos);
                break;
            case "KNIGHT":
                newPiece = new Knight(color, pos);
                break;
            case "BISHOP":
                newPiece = new Bishop(color, pos);
                break;
            default:
                newPiece = new Queen(color, pos);
        }
        this.board[pos[0]][pos[1]] = newPiece;

    }

    @Override
    public void castling(boolean color, Rook rook) {
        Piece king = getPiece(PieceTypes.KING, color).get(0);

        if (!rook.isCastling() || !king.isCastling() || ((King) king).getCheck()) {
            System.out.println("Castling is impossible!");
        } else {
            int x = king.getPosition()[1];
            int y = king.getPosition()[0];
            int xR = rook.getPosition()[1];
            int dY = king.getPosition()[1] - rook.getPosition()[1];

            Predicate<int[]> isCastImp = nums -> {
                for (int i = 1; i < nums[0]; i++) {
                    if (this.board[y][nums[1] + i] != null) {
                        System.out.println("Castling is impossible!");
                        return true;
                    }
                }
                return false;
            };

            Consumer<int[]> makeCast = nums -> {
                this.board[y][nums[0]] = king;
                king.setPosition(new int[]{y, nums[0]});
                this.board[y][nums[1]] = rook;
                rook.setPosition(new int[]{y, nums[1]});
                this.board[y][x] = null;
                this.board[y][xR] = null;
            };

            if (dY < 0 && xR == 7) {
                if (isCastImp.test(new int[]{3, x})) return;
                makeCast.accept(new int[]{6, 5});
            } else if (dY > 0 && xR == 0) {
                if (isCastImp.test(new int[]{x, 0})) return;
                makeCast.accept(new int[]{2, 3});
            }
        }

    }

    @Override
    public boolean isCheck(boolean color) {
        Piece king = getPiece(PieceTypes.KING, color).get(0);
        boolean res = isCheck(color, king.getPosition());
        ((King) king).setCheck(res);
        return res;
    }

    private boolean isCheck(boolean color, int[] pos) {
        ArrayList<Piece> arr = getPieces(!color);
        boolean res = false;
        for (Piece el : arr) {
            if (el.getType() != PieceTypes.KING && isMoveValid(el, pos)) {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public boolean isCheckmate(boolean color) {
        Piece[][] newBoard = cloneBoard();

        for (Piece elem : getPieces(color)) {
            for (int[] move : elem.getAvailableMoves()) {
                movePiece(elem, move, false);
                if (!isCheck(color)) {
                    this.board = cloneBoard(newBoard);
                    return false;
                }
                this.board = cloneBoard(newBoard);
            }
        }
        this.board = cloneBoard(newBoard);
        return true;
    }

    private Piece[][] cloneBoard() {
        return cloneBoard(this.board);
    }

    private static Piece[][] cloneBoard(Piece[][] board) {
        Piece[][] newBoard = new Piece[8][8];
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[i].length; j++) {
                if (board[i][j] != null) newBoard[i][j] = board[i][j].clone();
            }
        }
        return newBoard;
    }

    @Override
    public boolean isStalemate(boolean color) {
        ArrayList<Piece> pieces = getPieces(color);

        for (Piece pi : pieces) {
            for (int[] move : pi.getAvailableMoves()) {
                if (pi != null && isMoveValid(pi, move)) return false;
            }
        }
        return true;
    }

    private ArrayList<Piece> getPieces() {
        ArrayList<Piece> arr = new ArrayList<>();
        for (int i = 0; i < this.board[0].length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                if (this.board[i][j] != null) {
                    arr.add(this.board[i][j]);
                }
            }
        }
        return arr;
    }

    public ArrayList<Piece> getPieces(boolean color) {
        ArrayList<Piece> arrC = getPieces();
        ArrayList<Piece> res = new ArrayList<>();
        for (Piece elem : arrC) {
            if (elem.getColor() == color) res.add(elem);
        }
        return res;
    }

    private ArrayList<Piece> getPiece(PieceTypes type, boolean color) {
        ArrayList<Piece> arrC = getPieces(color);
        ArrayList<Piece> res = new ArrayList<>();
        for (Piece elem : arrC) {
            if (elem.getType() == type) res.add(elem);
        }
        return res;
    }

    public Piece getPieceById(int id) {
        ArrayList<Piece> arrC = getPieces();
        for (Piece elem : arrC) {
            if (elem.getIdOfPieceThisType() == id) return elem;
        }
        return null;
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
}

