package PlayGUI;

import ChessBoard.ChessBoard;
import Piece.PieceTypes;
import Pieces.Pawn;

import javax.swing.*;
import java.awt.*;

public class PawnPromotion extends JDialog {

    private Pawn pawn;
    private String pawnPos;
    private ButtonGroup group;
    private ChessBoard board;
    private DisplayBoard chess;

    public PawnPromotion(Pawn pawn, ChessBoard board,DisplayBoard chess,Frame parentContainer) {

        this.pawn = pawn;
        this.board = board;
        this.chess=chess;
        this.pawnPos = ChessBoard.coverNumToCnessCord(this.pawn.getPosition());

        JDialog container =new JDialog( parentContainer,"Pawn`s promotion", true);
        container.setLayout(new GridLayout(3, 1));
        container.setBounds(600, 100, 600, 500);
        container.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        container.add(getTitleProm());
        container.add(getbGroup());
        container.add(getBtn());

        container.pack();
        container.setVisible(true);
    }


    private JPanel getBtn() {
        JPanel panel = Start.makePanel(0);
        JButton btn = new JButton(" Confirm  ");
        btn.setMaximumSize(new Dimension(100, 30));
        btn.setPreferredSize(new Dimension(100, 30));
        btn.addActionListener(e -> {
            String type = this.group.getSelection().getActionCommand();

            this.board.promotingOfPawn(this.pawn, type);

            this.chess.dispose();
            this.chess = new DisplayBoard(this.board.getBoard());
            String mess = String.format("PAWN was promotion to %s <%s>.%n",
                    type,this.pawnPos);
            JOptionPane.showMessageDialog(null, mess);

            JButton butt = (JButton) e.getSource();
            SwingUtilities.getWindowAncestor(butt).dispose();
        });
        panel.add(btn);
        return panel;
    }

    private JPanel getTitleProm() {
        JPanel panel = Start.makePanel(20);
        JLabel title = new JLabel(String.format("PAWN <%s> can be promotion.%n",
                this.pawnPos));
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        return panel;
    }

    private JPanel getbGroup() {
        JPanel panel = Start.makePanel(0);
        this.group = new ButtonGroup();
        for (PieceTypes el : PieceTypes.values()) {
            if (el == PieceTypes.KING || el == PieceTypes.PAWN) continue;
            String rbText = "" + PieceTypes.getChar(el, this.pawn.getColor());
            JRadioButton rb = new JRadioButton(rbText);
            if (el == PieceTypes.QUEEN) rb.setSelected(true);
            rb.setActionCommand(el + "");
            rb.setFont(new Font("Serif", Font.PLAIN, 15));

            this.group.add(rb);

            panel.add(rb);
        }
        return panel;
    }
}
