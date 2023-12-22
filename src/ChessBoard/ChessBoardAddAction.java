package ChessBoard;

public interface ChessBoardAddAction {

    String displayBoard();
    void capturing();

    void promotingOfPawn();

    void  castling();
}
//        Метод displayBoard:

//        Отображение текущей позиции на доске в текстовом виде.

//        Дополнительные методы для других действий в игре:
//        Взятие фигуры
//        Превращение пешки
//        Рокировка