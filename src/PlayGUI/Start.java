package PlayGUI;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ChessBoard.ChessBoard;
import Piece.*;

import java.awt.*;
import java.util.ArrayList;

public class Start extends JFrame {
    public Start() {
        super("Chess");
        //        setSize(400, 400);
        setBounds(600, 100, 600,400 );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Container container = super.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

//        JPanel moves = new JPanel();
//        moves.setLayout(new BoxLayout(moves, BoxLayout.Y_AXIS));

        ChessBoard board = new ChessBoard();
        DisplayBoard chess = new DisplayBoard(board.getBoard());
        //JOptionPane.showMessageDialog(null, "Chess game started!");
        container.add(Box.createRigidArea(new Dimension(0,20)));
        container.add(getTitle(board));
        container.add(Box.createRigidArea(new Dimension(0,20)));

        container.add(getRButtonGroup(board));
        container.add(Box.createRigidArea(new Dimension(0,20)));
        container.add(getInputField(board));
        container.add(Box.createRigidArea(new Dimension(0,20)));



        revalidate();
        repaint();
           //pack();
//        setLocationRelativeTo(null);
    }

    private static JLabel getTitle(ChessBoard board) {
        String movTitle = String.format("Move of the <%s> pieces. Choose piece:",
                (board.getColorOfMove()) ? "black" : "white");

        JLabel mov = new JLabel(movTitle);
        mov.setFont(new Font("Arial", Font.PLAIN, 20));
        mov.setAlignmentX(Component.CENTER_ALIGNMENT);
        return mov;
    }

    private static JPanel getRButtonGroup(ChessBoard board) {
        JPanel bGroup = new JPanel();
        bGroup.setLayout(new GridLayout(4, 4));
        bGroup.setAlignmentX(Component.CENTER_ALIGNMENT);

        ButtonGroup group = new ButtonGroup();
        ArrayList<Piece> pieces = board.getPieces(board.getColorOfMove());
        for (Piece pi : pieces) {
            String rbText = String.format("%s<%s>",
                    PieceTypes.getChar(pi.getType(), pi.getColor()),
                    board.coverNumToCnessCord(pi.getPosition()));
            JRadioButton rb = new JRadioButton(rbText);
            rb.setActionCommand("" + pi.getIdOfPieceThisType());
            rb.setFont(new Font("Serif", Font.PLAIN, 15));

            group.add(rb);
            bGroup.add(rb);
        }
        return bGroup;
    }

    private static JPanel getInputField(ChessBoard board) {
        JPanel inputGroup = new JPanel();
        inputGroup.setAlignmentX(Component.RIGHT_ALIGNMENT);
        inputGroup.setLayout(new BoxLayout(inputGroup, BoxLayout.X_AXIS));
        JLabel title = new JLabel("Enter your move(exam : d2) : ");
        title.setFont(new Font("Arial", Font.PLAIN, 15));
        JTextField move = new JTextField();
        move.setMaximumSize(new Dimension(100, 30));
        inputGroup.add(title);
        inputGroup.add(move);
        return inputGroup;
    }

}
