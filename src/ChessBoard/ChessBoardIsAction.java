package ChessBoard;

public interface ChessBoardIsAction {

    boolean isCheck(boolean color);
    boolean isCheckmate(boolean color);

    boolean isStalemate();

}
//        Методы для проверки игровой ситуации:
//        isCheck
//        isCheckmate
//        isStalemate