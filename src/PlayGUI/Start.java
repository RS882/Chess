package PlayGUI;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ChessBoard.ChessBoard;
import Piece.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Start extends JFrame {
    private ButtonGroup group;
    private JTextField move;

    public Start() {
        super("Chess");
        //        setSize(400, 400);
        setBounds(600, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Container container = super.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));


        ChessBoard board = new ChessBoard();
        DisplayBoard chess = new DisplayBoard(board.getBoard());
        //JOptionPane.showMessageDialog(null, "Chess game started!");
        container.add(Box.createRigidArea(new Dimension(0, 20)));

        container.add(getTitle(board));
        container.add(Box.createRigidArea(new Dimension(0, 20)));

        container.add(getRButtonGroup(board));
        container.add(Box.createRigidArea(new Dimension(0, 20)));

        container.add(getInputField());
        container.add(Box.createRigidArea(new Dimension(0, 20)));

        revalidate();
        repaint();
        pack();
//        setLocationRelativeTo(null);
    }

    private JLabel getTitle(ChessBoard board) {
        String movTitle = String.format("Move of the <%s> pieces. Choose piece:",
                (board.getColorOfMove()) ? "black" : "white");

        JLabel mov = new JLabel(movTitle);
        mov.setFont(new Font("Arial", Font.PLAIN, 20));
        mov.setAlignmentX(Component.RIGHT_ALIGNMENT);
        return mov;
    }

    private JPanel getRButtonGroup(ChessBoard board) {
        JPanel bGroup = new JPanel();
        bGroup.setLayout(new GridLayout(4, 4));
        bGroup.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.group = new ButtonGroup();
        ArrayList<Piece> pieces = board.getPieces(board.getColorOfMove());
        for (Piece pi : pieces) {
            String rbText = String.format("%s<%s>",
                    PieceTypes.getChar(pi.getType(), pi.getColor()),
                    board.coverNumToCnessCord(pi.getPosition()));
            JRadioButton rb = new JRadioButton(rbText);
            rb.setActionCommand("" + pi.getIdOfPieceThisType());
            rb.setFont(new Font("Serif", Font.PLAIN, 15));

            this.group.add(rb);
            bGroup.add(rb);
        }
        bGroup.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        return bGroup;
    }

    private JPanel getInputField() {
        JPanel inputGroup = new JPanel();
        inputGroup.setAlignmentX(Component.RIGHT_ALIGNMENT);
        inputGroup.setLayout(new BoxLayout(inputGroup, BoxLayout.X_AXIS));

        JLabel title = new JLabel("Enter your move(exam : d2) : ");
        title.setFont(new Font("Arial", Font.PLAIN, 15));

        this.move = new JTextField();
        this.move.setMaximumSize(new Dimension(100, 30));
        this.move.setPreferredSize(new Dimension(100, 30));


        inputGroup.add(title);
        inputGroup.add(this.move);
        inputGroup.add(new JLabel("  "));
        inputGroup.add(getBtn());

        inputGroup.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        return inputGroup;
    }

    private JButton getBtn() {
        JButton btn = new JButton(" Move ");
        btn.setMaximumSize(new Dimension(100, 30));
        btn.setPreferredSize(new Dimension(100, 30));

        btn.addActionListener(e -> {
            try {
                String rbDate = group.getSelection().getActionCommand();
                String moveCord = move.getText();

                convertChessCordToNub(moveCord);

//                System.out.println(moveCord);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Choose a piece!");
            } catch (IncorrectValueOfPieceMove ex) {
                JOptionPane.showMessageDialog(null, "Enter correct move! (exam : d2)");
            }


        });
        return btn;

    }

    private int[] convertChessCordToNub(String str) throws IncorrectValueOfPieceMove {
        int[] res = new int[2];
        char[] chArr = str.toLowerCase().toCharArray();
        if (chArr.length >= 2 &&
                chArr[0] >= 'a' && chArr[0] <= 'h' &&
                chArr[1] >= '1' && chArr[1] <= '8') {
            res[0] = 8 - (chArr[1] - '0');
            res[1] = chArr[0] - 'a';
            System.out.println(Arrays.toString(res));
        } else throw new IncorrectValueOfPieceMove();
        System.out.println(Arrays.toString(res));
        return new int[]{};

    }

}
class IncorrectValueOfPieceMove extends Exception{
    public IncorrectValueOfPieceMove() {

    }
}