package PlayGUI;

import Piece.*;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class DisplayBoard extends JFrame {
    Piece[][] board;
    public DisplayBoard(Piece[][] board) {
        super("Chess");

        setBounds(100, 100, 500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        Container container = super.getContentPane();
        container.setLayout(new GridLayout(10, 10));

        Color light = new Color(255, 206, 158);
        Color dark = new Color(209, 139, 71);

        // Function to create JLabel with a specific font size
        Function<Integer, JLabel> getSymbol = (size) -> {
            JLabel piece = new JLabel();
            piece.setFont(new Font("Serif", Font.PLAIN, size));
            return piece;
        };

        // Supplier to get a JPanel cell
        Supplier<JPanel> getCell = () -> {
            JPanel cell = new JPanel();
            cell.setPreferredSize(new Dimension(50, 50));
            return cell;
        };

        // Suppliers to get JLabels for characters and chess pieces
        Supplier<JLabel> getChar = () -> getSymbol.apply(25);
        Supplier<JLabel> getPiece = () -> getSymbol.apply(30);

        // Function to get a cell with a JLabel
        Function<JLabel, JPanel> getCellWithPi = (pi) -> {
            JPanel cell = getCell.get();
            cell.add(pi);
            return cell;
        };

        // Runnable to display letters from A to H
        Runnable getAtoH = () -> {
            for (int i = 0; i < 10; i++) {
                JLabel piece = getChar.get();
                if (i == 0 || i == 9) piece.setText(" ");
                else piece.setText("" + (char) ('a' + i - 1));
                container.add(getCellWithPi.apply(piece));
            }
        };

        // Consumer to display numbers from 1 to 8
        Consumer<Integer> get1to8 = (num) -> {
            JLabel piece = getChar.get();
            piece.setText("" + (8 - num));
            container.add(getCellWithPi.apply(piece));
        };

        getAtoH.run();

        // Loop to create the chessboard GUI
        for (int i = 0; i < 8; i++) {
            get1to8.accept(i);
            for (int j = 0; j < 8; j++) {
                JPanel cell = getCell.get();
                cell.setBackground((i + j) % 2 == 0 ? light : dark);
                if (board[i][j] != null) {
                    Piece pieceObj = board[i][j];
                    JLabel piece = getPiece.get();
                    piece.setText(PieceTypes.getChar(pieceObj.getType(), pieceObj.getColor()));
                    cell.add(piece);
                }
                container.add(cell);
            }
            get1to8.accept(i);
        }
        getAtoH.run();

        revalidate();
        repaint();
    }
}
