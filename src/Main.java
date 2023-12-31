import ChessBoard.ChessBoard;
import Piece.Piece;
import Pieces.Bishop;
import Pieces.Pawn;
import Pieces.Queen;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        ChessBoard board =new ChessBoard();
//        System.out.println(board);
     board.displayBoard();



        for (Piece[] str:board.getBoard()) {
            for (Piece pi: str) {
                if(pi!=null && pi.isPromotion()) board.promotingOfPawn((Pawn) pi);
            }
        }
        board.displayBoard();
//        System.out.println(board.isStalemate(false));
//     System.out.println( board.isMoveValid());
//        board.movePiece();
//        board.displayBoard();
//        System.out.println(board.isCheck(false));
//System.out.println( board.isCheckmate(true));


//        System.out.println(board.coverNubToCnessCord(new int[]{3,3}));


//     //     System.out.println(Arrays.toString(pawn.getPosition()));
//                ArrayList<int[]> pos =pawn.getAvailableMoves();
//
//        for (int[] el: pos) {
//            System.out.println(Arrays.toString(el));
//        }

//        Test test = new Test();
//
//        test.displayBoard();


//        Knight knight = new Knight(false,new int[]{5,4},3);
//
  //     Bishop bishop = new Bishop(true, new int[]{3,4},2);
//
       //Queen queen = new Queen(true, new int[]{3,4},0);
//
//        King king =new King(false,new int[]{6,2},3);
//
//        System.out.println(PieceTypes.QUEEN.getChar(king.getType(),true));
//        System.out.println(PieceTypes.QUEEN.getChar(king.getType(),false));

       // Rook rook = new Rook(true, new int[]{1,4,});
        //System.out.println(Arrays.toString(bishop.getPosition()));
//                ArrayList<int[]> pos =bishop.getAvailableMoves();
//
//        for (int[] el: pos) {
//            System.out.println(Arrays.toString(el));
//        }
//        System.out.println(king.isCastling());
//       System.out.println(king);
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

//    Для запуска игры в шахматы из метода main вашего приложения нужно:
//
//        Создать объект класса, реализующего шахматную доску.
//        Инициализировать начальное расположение фигур на доске.
//        Иметь цикл, который будет управлять ходами игры (пока игра не закончится).
//        В цикле обрабатывать ввод пользователя (например, вводить начальную и конечную позиции фигур для хода).
//        После ввода проверять, является ли ход допустимым на текущей доске.
//        Если ход допустим, обновить состояние доски, иначе запросить повторный ввод хода.
//        После каждого хода проверять условия окончания игры (например, шах, мат или ничья).
//        Вывести сообщение о завершении игры и, если требуется, выйти из цикла.
//        Это базовая структура. Подробности реализации могут зависеть от способа представления игры и логики обработки ходов.





