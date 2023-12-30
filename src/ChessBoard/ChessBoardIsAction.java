package ChessBoard;

public interface ChessBoardIsAction {

    boolean isCheck(boolean color);
    boolean isCheckmate(boolean color) throws CloneNotSupportedException;

    boolean isStalemate();

}
//        Методы для проверки игровой ситуации:
//        isCheck
//        isCheckmate
//        isStalemate