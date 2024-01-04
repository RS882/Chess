package PlayGUI;

import javax.swing.*;


import ChessBoard.ChessBoard;
import Piece.*;
import Pieces.Pawn;

import java.awt.*;

import java.util.ArrayList;

public class Start extends JFrame {
    private ButtonGroup group;
    private JTextField move;

    private DisplayBoard chess;

    private ChessBoard board;
    private Container container;

    private JPanel pieceChoise;

    public Start() {
        super("Chess");
        //        setSize(400, 400);
        setBounds(600, 100, 600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.container = super.getContentPane();
        this.container.setLayout(new BoxLayout(this.container, BoxLayout.Y_AXIS));

        this.board = new ChessBoard();

        this.chess = new DisplayBoard(this.board.getBoard());
        JOptionPane.showMessageDialog(null, "Chess game started!");

        this.pieceChoise = new JPanel();
        this.pieceChoise.setLayout(new BoxLayout(this.pieceChoise, BoxLayout.Y_AXIS));
        this.pieceChoise.add(getMoveTitle());
        this.pieceChoise.add(getRButtonGroup());

        this.container.add(this.pieceChoise);
        this.container.add(getInputField());

        revalidate();
        repaint();
        pack();
//        setLocationRelativeTo(null);
    }


    private JPanel makePanel(int top) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(top, 20, 20, 20));
        return panel;
    }

    private JPanel getMoveTitle() {
        return getMoveTitle("");
    }

    private JPanel getMoveTitle(String addMess) {
        JPanel panel = makePanel(20);

        String movTitle = String.format("%sMove of the <%s> pieces. Choose piece:", addMess,
                (this.board.getColorOfMove()) ? "black" : "white");
        JLabel mov = new JLabel(movTitle);
        mov.setFont(new Font("Arial", Font.PLAIN, 20));

        panel.add(mov);
        return panel;
    }

    private JPanel getRButtonGroup() {
        JPanel panel = makePanel(0);
        panel.setLayout(new GridLayout(4, 4));

        this.group = new ButtonGroup();
        ArrayList<Piece> pieces = this.board.getPieces(this.board.getColorOfMove());
        for (Piece pi : pieces) {
            String rbText = String.format("%s<%s>",
                    PieceTypes.getChar(pi.getType(), pi.getColor()),
                    ChessBoard.coverNumToCnessCord(pi.getPosition()));
            JRadioButton rb = new JRadioButton(rbText);
            rb.setActionCommand("" + pi.getIdOfPieceThisType());
            rb.setFont(new Font("Serif", Font.PLAIN, 15));

            this.group.add(rb);
            panel.add(rb);
        }

        return panel;
    }

    private JPanel getInputField() {
        JPanel panel = makePanel(0);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JLabel title = new JLabel("Enter your move(exam : d2) : ");
        title.setFont(new Font("Arial", Font.PLAIN, 15));

        this.move = new JTextField();
        this.move.setMaximumSize(new Dimension(100, 30));
        this.move.setPreferredSize(new Dimension(100, 30));

        panel.add(title);
        panel.add(this.move);
        panel.add(new JLabel("  "));
        panel.add(getBtn());
        return panel;
    }

    private JPanel getActionTitle(String actionNow) {
        JPanel panel = makePanel(20);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        JLabel check = new JLabel(actionNow);
        check.setFont(new Font("Arial", Font.PLAIN, 20));
        check.setForeground(Color.RED);

        panel.add(check);
        return panel;
    }

    private JButton getBtn() {
        JButton btn = new JButton(" Move ");
        btn.setMaximumSize(new Dimension(100, 30));
        btn.setPreferredSize(new Dimension(100, 30));

        btn.addActionListener(e -> {
            try {
                String rbDate = this.group.getSelection().getActionCommand();

                Piece movingPiece = this.board.getPieceById(Integer.parseInt(rbDate));

                String moveCord = this.move.getText();
                String moveMessage = this.board.movePiece(movingPiece,
                        convertChessCordToInt(moveCord));

                JOptionPane.showMessageDialog(null, moveMessage);

                if (movingPiece.isPromotion()) showPromotion(movingPiece);
                boolean color = this.board.getColorOfMove();

                String actionNow = "";

                if (this.board.isStalemate(color)) {
                    actionNow = String.format("Game over.%n Is stalemate.%n");
                    JOptionPane.showMessageDialog(null, actionNow);
                    btn.setEnabled(false);
                    this.move.setEnabled(false);
                } else if (this.board.isCheckmate(color)) {
                    actionNow = String.format("Game over.%n Is Checkmate.%n <%s> win",
                            !color ? "Black" : "White");
                    JOptionPane.showMessageDialog(null, actionNow);
                    btn.setEnabled(false);
                    this.move.setEnabled(false);
                } else if (this.board.isCheck(color)) {
                    actionNow = String.format("Check to <%s>! ", color ? "black" : "white");
                    JOptionPane.showMessageDialog(null, actionNow);
                }

                this.chess.dispose();
                this.chess = new DisplayBoard(this.board.getBoard());

                this.pieceChoise.removeAll();

                if (!actionNow.equals("")) this.pieceChoise.add(getActionTitle(actionNow));

                this.pieceChoise.add(getMoveTitle());
                this.pieceChoise.add(getRButtonGroup());
                this.move.setText("");

                revalidate();
                repaint();
                pack();

            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Choose a piece!");
            } catch (IncorrectValueOfPieceMove ex) {
                JOptionPane.showMessageDialog(null, "Enter correct move! (exam : d2)");
            }

        });
        return btn;

    }

    private void showPromotion(Piece pawn) {
        if (!(pawn instanceof Pawn)) return;
        PawnPromotion promo = new PawnPromotion((Pawn) pawn, this.board, this.chess);

    }

    private int[] convertChessCordToInt(String str) throws IncorrectValueOfPieceMove {
        int[] res = new int[2];
        char[] chArr = str.toLowerCase().toCharArray();
        if (chArr.length >= 2 &&
                chArr[0] >= 'a' && chArr[0] <= 'h' &&
                chArr[1] >= '1' && chArr[1] <= '8') {
            res[0] = 8 - (chArr[1] - '0');
            res[1] = chArr[0] - 'a';

        } else throw new IncorrectValueOfPieceMove();
        return res;
    }
}

class IncorrectValueOfPieceMove extends Exception {

}