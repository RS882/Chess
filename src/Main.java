import ChessBoard.ChessBoard;
import Pieces.Pawn;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        ChessBoard board =new ChessBoard();
        System.out.println(board);

        Pawn pawn = new Pawn( true, new int[]{1,4,});
       System.out.println(Arrays.toString(pawn.getPosition()));
        pawn.setPosition(new int[]{2,2});
        System.out.println(Arrays.toString(pawn.getPosition()));
        pawn.setPosition(new int[]{7,2});
        System.out.println(Arrays.toString(pawn.getPosition()));
       System.out.println(pawn.isPromotion());
//        pawn.setPosition(new int[]{6,34});
//        System.out.println(Arrays.toString(pawn.getPosition()));
//        ArrayList<int[]> pos =pawn.getAvailableMoves();
//
//        for (int[] el: pos) {
//            System.out.println(Arrays.toString(el));
//        }
//        System.out.println(Arrays.toString(pawn.getPosition()));
//        System.out.println(pawn.isValidMove(new int[]{5,3}));
       System.out.println(pawn);

    }
}

//    Создание класса ChessBoard:
//
//        Определить поля:
//      +  Двумерный массив (8x8) для представления клеток доски.

//        Определить конструктор:
//        Создание начальной расстановки фигур на доске.

//        Метод movePiece:
//        Перемещение фигуры с одной клетки на другую.

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

//    isValidMove в классе ChessBoard и Piece различается в контексте, в котором
//    эти методы вызываются и что они делают:
//
//        isValidMove в ChessBoard:
//
//        Этот метод обычно проверяет допустимость хода на доске. Он анализирует
//        ход, который игрок хочет сделать, и проверяет, действителен ли этот ход
//        в рамках правил игры и конкретной ситуации на доске. В этом методе
//        проверяется, свободны ли клетки, не происходит ли взятие фигуры, является
//        ли этот ход валидным по правилам шахмат.

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

//        Метод isValidMove для каждой фигуры:
//        Проверка корректности хода с учетом их правил.
//        Методы для определения возможных ходов:
//        getAvailableMoves
//        Методы для специфических действий:
//        isEnPassant
//        isCastling
//        isPromotion

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


//        Взаимодействие между классами:
//
//        Создание и управление объектами фигур на доске через класс ChessBoard.
//        Использование методов класса Piece для проверки корректности хода и
//        выполнения специфических действий.
//        Тестирование:
//
//        Создание тестовых сценариев для проверки работы методов и классов.
//        Убедиться, что все ходы фигур соответствуют правилам шахмат.
//        Каждый из этих шагов представляет собой отдельные задачи, которые
//        необходимо реализовать для создания функциональной шахматной доски в программе.