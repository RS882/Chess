package PlayGUI;

import ChessBoard.ChessBoard;
import Piece.PieceTypes;
import Pieces.Pawn;

import javax.swing.*;
import java.awt.*;

public class PawnPromotion extends JFrame {

    private Pawn pawn;

    private ButtonGroup group;

    private ChessBoard board;

    public PawnPromotion(Pawn pawn, ChessBoard board) {
        super("Chess");
        this.pawn = pawn;
        this.board = board;
        setBounds(600, 100, 600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        Container container = super.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(getTitleProm());

        container.add(getbGroup());

        container.add(getBtn());

        revalidate();
        repaint();
        pack();
    }

    private JPanel makePanel(int top) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(top, 20, 20, 20));
        return panel;
    }

    private JPanel getBtn() {
        JPanel panel = makePanel(0);
        JButton btn = new JButton(" Confirm  ");
        btn.setMaximumSize(new Dimension(100, 30));
        btn.setPreferredSize(new Dimension(100, 30));
        btn.addActionListener(e -> {
            String type = this.group.getSelection().getActionCommand();
            this.board.promotingOfPawn(this.pawn, type);
            JButton butt = (JButton) e.getSource();
            SwingUtilities.getWindowAncestor(butt).dispose();
        });
        panel.add(btn);
        return panel;
    }

    private JPanel getTitleProm() {
        JPanel panel = makePanel(20);
        JLabel title = new JLabel(String.format("PAWN <%s> can be promotion.%n",
                ChessBoard.coverNumToCnessCord(this.pawn.getPosition())));
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        return panel;
    }

    private JPanel getbGroup() {
        JPanel panel = makePanel(0);
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
