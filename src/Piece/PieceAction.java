package Piece;

import ChessBoard.ChessBoard;

public interface PieceAction {

    boolean isValidMove(char[] start, char[] end);

    char[][] getAvailableMoves(char[] start, ChessBoard board);
}
//        Метод isValidMove для каждой фигуры:
//        Проверка корректности хода с учетом их правил.
//        Методы для определения возможных ходов:
//        getAvailableMoves
//        Методы для специфических действий:
//        isEnPassant
//        isCastling
//        isPromotion