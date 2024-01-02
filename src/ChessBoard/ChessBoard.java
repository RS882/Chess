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



    public ChessBoard() {
        this.board = new Piece[8][8];
        initialazePieces();


    }
    private void initialazePieces() {
        initialazePieces(this.board);
    }
    private void initialazePieces(Piece[][] board) {

//        this.board[5][2] = new King(true, new int[]{5, 2}, 0);
//        this.board[7][2] = new King(false, new int[]{7, 2}, 0);
//        this.board[5][4] = new Knight(true, new int[]{5, 4}, 2);
//        this.board[5][3] = new Bishop(true, new int[]{5, 3}, 1);
//
        for (int i = 0; i < 8; i++) {
            this.board[1][i] = new Pawn(true, new int[]{1, i});
            this.board[6][i] = new Pawn(false, new int[]{6, i});
        }
//        this.board[7][1] = new Pawn(true, new int[]{7, 1}, 2 + 1);
        this.board[0][3] = new Rook(true, new int[]{0, 3});
        this.board[0][5] = new Rook(true, new int[]{0, 5});
//        this.board[3][4] = new Rook(false, new int[]{3, 4}, 1);
//        this.board[4][2] = new Rook(false, new int[]{4, 2}, 2);

        this.board[5][2] = new Knight(true, new int[]{5, 2});
   //      this.board[7][3] = new Knight(true, new int[]{7, 3}, 2);
        this.board[2][5] = new Knight(false, new int[]{2, 5});
        this.board[0][0] = new Knight(false, new int[]{0, 0});

        this.board[0][2] = new Bishop(true, new int[]{0, 2});
        this.board[2][2] = new Bishop(true, new int[]{2, 2});
        this.board[6][6] = new Bishop(false, new int[]{6, 6});
        this.board[1][5] = new Bishop(false, new int[]{1, 5});

        this.board[5][3] = new Queen(true, new int[]{5, 3});
        this.board[2][6] = new Queen(false, new int[]{2, 6});

        this.board[0][4] = new King(true, new int[]{0, 4});
        this.board[7][4] = new King(false, new int[]{7, 4});

        this.board[7][0] = new Rook(false, new int[]{7, 0});
        this.board[7][7] = new Rook(false, new int[]{7, 7});

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
        return isMoveValid(piece, end, this.board);
    }


    private boolean isMoveValid(Piece piece, int[] end, Piece[][] board) {

        if (!piece.isValidMove(end)) return false;

        ArrayList<int[]> otherPieces = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    otherPieces.add(board[i][j].getPosition());
                }
            }
        }
        for (int[] elem : otherPieces) {
            if (end[0] == elem[0] && end[1] == elem[1]) {
                if (piece.getColor() == board[end[0]][end[1]].getColor()) return false;
            }
        }

        int y = piece.getPosition()[0];
        int x = piece.getPosition()[1];

        if (piece instanceof Knight) {
            return true;
        } else if (piece instanceof King) {

            Piece king2 = getPiece(PieceTypes.KING, !piece.getColor()).get(0);
            if (king2.isValidMove(end)) return false;

            return !isCheck(piece.getColor(), end);
        } else if (piece instanceof Pawn) {

            Predicate<int[]> isPawnRL = arr -> end[0] == y + arr[0] && end[1] == x + arr[1]
                    && board[y + arr[0]][x + arr[1]] == null;

            Predicate<Integer> isPawn1 = dy -> end[0] == y + dy && end[1] == x && board[y + dy][x] != null;

            Predicate<Integer> isPawn2 = dy -> end[0] == y + dy * 2 && end[1] == x && (board[y + dy][x] != null
                    || board[y + dy * 2][x] != null);

            Predicate<int[]> isPawnEnPas = arr ->
                    y == arr[1]
                            && ((isPawnRL.test(new int[]{arr[0], 1})
                            && board[y][x + 1].isEnPassant()
                            && lastMove.equals(board[y][x + 1]))
                            || (isPawnRL.test(new int[]{arr[0], -1})
                            && board[y][x - 1].isEnPassant())
                            && lastMove.equals(board[y][x - 1]));

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
        return piece.isValidMove(end);
    }

//    public boolean isMoveValid() {
//        return isMoveValid(
//                new King(true, new int[]{2, 3},
//                        0), new int[]{3, 4});
//    }

    private void movePiece(Piece piece, int[] end, boolean isSout, Piece[][] board) {
        if (isSout) this.lastMove = piece;

        if (isMoveValid(piece, end)) {
            int x = end[1];
            int y = end[0];
            if (isSout) System.out.printf("%s moves %s -> %s.%n",
                    piece.getType(),
                    coverNumToCnessCord(piece.getPosition()),
                    coverNumToCnessCord(end));
            if (board[y][x] != null && isSout) capturing(piece, board[y][x]);

            board[piece.getPosition()[0]][piece.getPosition()[1]] = null;
            board[y][x] = piece;
            piece.setPosition(new int[]{y, x});
            this.colorOfMove = !this.colorOfMove;

        } else {
            if (isSout) System.out.printf(" %s moves %s -> %s not possible!%n",
                    piece.getType(),
                    coverNumToCnessCord(piece.getPosition()),
                    coverNumToCnessCord(end));
        }
    }

    private void movePiece(Piece piece, int[] end, boolean isSout) {
        movePiece(piece, end, isSout, this.board);

    }

    @Override
    public void movePiece(Piece piece, int[] end) {
        movePiece(piece, end, true);
    }

//    public void movePiece() {
//        movePiece(
//                new Rook(false, new int[]{3, 3}, 1),
//                new int[]{5, 3});
//    }


    @Override
    public void capturing(Piece move, Piece take) {
        System.out.printf("%s takes %s on %s.%n",
                move.getType(),
                take.getType(),
                coverNumToCnessCord(take.getPosition()));
    }

    public  String coverNumToCnessCord(int[] arr) {
        char[] xArr = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        char[] yArr = {'8', '7', '6', '5', '4', '3', '2', '1'};
        return "" + xArr[arr[1]] + yArr[arr[0]];
    }

    @Override
    public void promotingOfPawn(Pawn proPawn) {
        Scanner sc = new Scanner(System.in);
        boolean color = proPawn.getColor();
        int[] pos = proPawn.getPosition();
        System.out.printf("The pawn <%s> can be promotion.%n",
                coverNumToCnessCord(proPawn.getPosition()));
        char num;
        try {
            System.out.println("To select a piece enter a number:");
            System.out.printf("%s - enter 1%n", PieceTypes.getChar(PieceTypes.QUEEN, color));
            System.out.printf("%s - enter 2%n", PieceTypes.getChar(PieceTypes.ROOK, color));
            System.out.printf("%s - enter 3%n", PieceTypes.getChar(PieceTypes.KNIGHT, color));
            System.out.printf("%s - enter 4%n", PieceTypes.getChar(PieceTypes.BISHOP, color));

            num = sc.nextLine().charAt(0);
        } catch (Exception ex) {
            num = 1;
        }
        Piece newPiece;
        switch (num) {
            case '1':
                newPiece = new Queen(color, pos);
                break;
            case '2':
                newPiece = new Rook(color, pos);
                break;
            case '3':
                newPiece = new Knight(color, pos);
                break;
            case '4':
                newPiece = new Bishop(color, pos);
                break;
            default:
                System.out.println("Piece is unselected!");
                System.out.println("Atomically selected QUEEN");
                newPiece = new Queen(color, pos);
        }
        this.board[pos[0]][pos[1]] = newPiece;

        System.out.printf("Pawn was promotion to %s <%s>.%n",
                newPiece.getType(), coverNumToCnessCord(newPiece.getPosition()));
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
                if(isCastImp.test(new int[]{3,x})) return;
                makeCast.accept(new int[]{6,5});
            } else if (dY > 0 && xR == 0) {
                if(isCastImp.test(new int[]{x,0})) return;
                makeCast.accept(new int[]{2,3});
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

