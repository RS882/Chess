package Piece;


import java.util.Arrays;
import java.util.Objects;
import java.util.SimpleTimeZone;

abstract public class Piece implements PieceAction, PieceSpecialAction,Cloneable {
    final private PieceTypes type;
    final private boolean color;
    // black -true, white -false

    final private int idOfPieceThisType;
    private int[] position = null;
    // [y x]
    private int countOfMove;

    public Piece(PieceTypes type, boolean color, int[] position, int idOfPieceThisType) {
        this.type = type;
        this.color = color;
        this.idOfPieceThisType = idOfPieceThisType;
        this.countOfMove = 0;
        setPosition(position);
    }



    public int[] getPosition() {
        return this.position;
    }

    public void setPosition(int[] position) {
        this.countOfMove++;
        if (position.length == 2 && isInBoard(position)) this.position = position;

    }

    public PieceTypes getType() {
        return type;
    }

    public boolean getColor() {
        return color;
    }

    public int getIdOfPieceThisType() {
        return idOfPieceThisType;
    }

    public int getCountOfMove() {
        return countOfMove;
    }

    protected boolean isInBoard(int[] arr) {
        return  arr[0] >= 0 && arr[0] <= 7 && arr[1] >= 0 && arr[1] <= 7;
    }

    @Override
    public boolean isValidMove(int[] end) {
        if (!isInBoard(end)) return false;
        for (int[] el : getAvailableMoves()) {
            if (Arrays.equals(el, end)) return true;
        }
        return false;
    }

    @Override
    public String toString() {

        return  PieceTypes.getChar(this.getType(), this.getColor()) ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return this.color == piece.color && this.idOfPieceThisType == piece.idOfPieceThisType && this.type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color, idOfPieceThisType);
    }

    @Override
    public Piece clone()  {
        try{
            return (Piece) super.clone();
        }catch (CloneNotSupportedException e){
            return  null;
        }

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

//    getAvailableMoves в классе Piece - это метод, который определяет и
//    возвращает все возможные ходы для данной шахматной фигуры с учетом
//    текущего расположения фигуры на доске и правил ее движения.
//
//        Этот метод анализирует расположение фигуры на доске, ее тип и
//        возможные позиции, куда фигура может сходить на пустой доске.
//        Он учитывает возможные ходы для конкретной фигуры в соответствии
//        с правилами шахмат.
//
//        Внутри метода getAvailableMoves для каждой фигуры (пешка, конь,
//        слон, ладья, ферзь или король) определяются все клетки на доске,
//        куда эта фигура может переместиться. Эти ходы могут быть ограничены
//        действиями других фигур, границами доски и специфическими правилами
//        хода для каждой фигуры.
//
//        Для примера:
//
//        Пешка может двигаться вперед на одну клетку или на две клетки из
//        начальной позиции, а также может взять фигуру по диагонали.
//        Конь может сделать ход "буквой Г" - два шага по одной оси и один
//        шаг по другой (например, два вверх и один влево).
//        Слон может двигаться по диагонали на любое количество клеток.
//        Ладья может двигаться по вертикали или горизонтали на любое
//        количество клеток.
//        Ферзь может двигаться как слон и ладья в любом направлении.
//        Король может двигаться на одну клетку в любом направлении.
//        getAvailableMoves генерирует список или массив возможных ходов для
//        этой фигуры, которые затем используются для анализа возможных ходов
//        игрока или оценки состояния игры.

//        isValidMove в Piece:
//
//        Этот метод принадлежит конкретной шахматной фигуре (пешка, конь,
//        слон и т. д.) и определяет, является ли этот ход действительным для
//        этой фигуры в контексте ее собственных правил движения. Он проверяет,
//        соответствует ли переданный ход правилам движения конкретной фигуры:
//        может ли она сделать такой ход на пустой доске, игнорируя другие фигуры
//        и правила игры. Этот метод может быть использован для проверки, допустимо
//        ли перемещение в соответствии с возможностями конкретной фигуры.
//        В целом, isValidMove в ChessBoard работает на уровне доски и правил игры,
//        тогда как isValidMove в Piece описывает возможные ходы для конкретной фигуры.

//isEnPassant:
//
//        Определяет, произошло ли взятие на проходе. В шахматах, когда пешка
//        преодолевает две клетки из начальной позиции, оставляя соперника
//        возможность взять её, как если бы она остановилась на одной клетке,
//        путем взятия пешки на проходе.
//   isCastling:
//
//        Проверяет, произошло ли рокировка. Рокировка - это особый ход, при
//        котором король и одна из ладей двигаются в одном ходе. Рокировка может
//        быть короткой (с короткой ладьей) или длинной (с длинной ладьей).
//   isPromotion:
//
//        Определяет, произошло ли превращение пешки. Когда пешка достигает
//        противоположного конца доски, она может быть превращена в любую другую
//        фигуру (обычно в ферзя, ладью, слона или коня). Это называется превращением
//        или продвижением пешки.
