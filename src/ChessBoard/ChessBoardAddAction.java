package ChessBoard;

import Piece.Piece;
import Pieces.Pawn;

public interface ChessBoardAddAction {


    void capturing(Piece move, Piece take);

    void promotingOfPawn(Pawn proPawn);

    void  castling();
}
//        Метод displayBoard:

//        Отображение текущей позиции на доске в текстовом виде.

//        Дополнительные методы для других действий в игре:
//        Взятие фигуры
//        Превращение пешки
//        Рокировка