package ChessBoard;

import Piece.*;
import Pieces.Pawn;
import Pieces.Rook;

public interface ChessBoardAddAction {


    String capturing(Piece move, Piece take);

    void promotingOfPawn(Pawn proPawn, String type);

    void  castling(boolean color, Rook rook);
}
//        Метод displayBoard:

//        Отображение текущей позиции на доске в текстовом виде.

//        Дополнительные методы для других действий в игре:
//        Взятие фигуры
//        Превращение пешки
//        Рокировка