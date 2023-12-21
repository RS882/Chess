package Piece;

abstract public class Piece {
    private PieceTypes type;
    private boolean color;
    // white -true, black -false

    private char[] position ;
    // [x y]

    public Piece(PieceTypes type, boolean color, char[] position) {
        this.type = type;
        this.color = color;
        this.position = position;
    }

    public char[] getPosition() {
        return position;
    }

    public void setPosition(char[] position) {
        this.position = position;
    }
}
//        Создание классов фигур (Piece):
//
//        Создать общий абстрактный класс Piece, который будет являться
//        базовым для всех фигур.
//        Реализовать классы для каждой фигуры (Pawn, Rook, Knight,
//        Bishop, Queen, King).
//        Определить поля:
//        Тип фигуры
//        Цвет
//        Текущая позиция

//        Метод isValidMove для каждой фигуры:
//        Проверка корректности хода с учетом их правил.
//        Методы для определения возможных ходов:
//        getAvailableMoves
//        Методы для специфических действий:
//        isEnPassant
//        isCastling
//        isPromotion

//    VERTICAL('8','7','6','5','4','3','2','1'),
//
//    HORISONTAL('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h') ;