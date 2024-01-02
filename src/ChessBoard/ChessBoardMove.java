package ChessBoard;

import Piece.Piece;

public interface ChessBoardMove {



    String movePiece(Piece piece, int[] end);


}

//        Проверка возможности хода.
//        Метод isMoveValid:
//        Проверка правильности хода с учетом правил каждой фигуры.

//        Методы для проверки игровой ситуации:
//        isCheck
//        isCheckmate
//        isStalemate

//        Метод displayBoard:

//        Отображение текущей позиции на доске в текстовом виде.

//        Дополнительные методы для других действий в игре:
//        Взятие фигуры
//        Превращение пешки
//        Рокировка

//        isValidMove в ChessBoard:
//
//        Этот метод обычно проверяет допустимость хода на доске. Он анализирует
//        ход, который игрок хочет сделать, и проверяет, действителен ли этот ход
//        в рамках правил игры и конкретной ситуации на доске. В этом методе
//        проверяется, свободны ли клетки, не происходит ли взятие фигуры, является
//        ли этот ход валидным по правилам шахмат.