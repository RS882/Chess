package PlayGUI;

import javax.swing.*;


import ChessBoard.ChessBoard;
import LogWriter.FileWriter;
import Piece.*;
import Pieces.Pawn;
import Pieces.Rook;

import java.awt.*;

import java.util.ArrayList;

public class Start extends JFrame {

    private ButtonGroup group; // Button group for piece selection
    private JTextField move; // Text field for move input
    private DisplayBoard chess; // DisplayBoard instance
    private ChessBoard board; // ChessBoard instance
    private Container container; // Container for components
    private JPanel pieceChoice; // Panel for piece selection


    public Start() {
        // Constructor for Start class
        super("Chess");
        FileWriter.writeToFileStartText();
        try {
            // Setting up JFrame
            setBounds(600, 100, 600, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

            this.container = super.getContentPane();
            this.container.setLayout(new BoxLayout(this.container, BoxLayout.Y_AXIS));
// Initializing chess board and display
            this.board = new ChessBoard();

            this.chess = new DisplayBoard(this.board.getBoard());
            JOptionPane.showMessageDialog(null, "Chess game started!");
            // Creating panels and components
            this.pieceChoice = new JPanel();
            this.pieceChoice.setLayout(new BoxLayout(this.pieceChoice, BoxLayout.Y_AXIS));

            this.pieceChoice.add(getMoveTitle());
            this.pieceChoice.add(getRButtonGroup());
            this.container.add(this.pieceChoice);
            this.container.add(getCastlingBtn());
            this.container.add(getInputField());

            revalidate();
            repaint();
            pack();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong...");
        }


    }


    // Method to create a JPanel with specific top margin
    public static JPanel makePanel(int top) {
        // Creates a JPanel with specific top margin
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(top, 20, 20, 20));
        return panel;
    }

    // Method to get the move title panel
    private JPanel getMoveTitle() {
        return getMoveTitle("");
    }

    // Method to get the move title panel with additional message
    private JPanel getMoveTitle(String addMess) {
        JPanel panel = makePanel(20);

        String movTitle = String.format("%sMove of the <%s> pieces. Choose piece:", addMess,
                (this.board.getColorOfMove()) ? "black" : "white");
        JLabel mov = new JLabel(movTitle);
        mov.setFont(new Font("Arial", Font.PLAIN, 20));

        panel.add(mov);
        return panel;
    }

    // Method to get the radio button group panel for piece selection
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

    // Method to get the input field panel
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

    // Method to get the action title panel with a specified action message
    private JPanel getActionTitle(String actionNow) {
        JPanel panel = makePanel(20);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        JLabel check = new JLabel(actionNow);
        check.setFont(new Font("Arial", Font.PLAIN, 20));
        check.setForeground(Color.RED);

        panel.add(check);
        return panel;
    }

    // Method to get the "Move" button
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
                FileWriter.writeRecordToFile(moveMessage);

                if (movingPiece.isPromotion()) {
                    showPromotion(movingPiece);
                }

                boolean color = this.board.getColorOfMove();

                String actionNow = "";

                if (this.board.isStalemate(color)) {
                    actionNow = String.format("Game over.%n Is stalemate.%n");
                    JOptionPane.showMessageDialog(null, actionNow);
                    FileWriter.writeRecordToFile(actionNow);
                    btn.setEnabled(false);
                    this.move.setEnabled(false);
                } else if (this.board.isCheckmate(color)) {
                    actionNow = String.format("Game over.%n Is Checkmate.%n <%s> win",
                            !color ? "Black" : "White");
                    JOptionPane.showMessageDialog(null, actionNow);
                    FileWriter.writeRecordToFile(actionNow);
                    btn.setEnabled(false);
                    this.move.setEnabled(false);
                } else if (this.board.isCheck(color)) {
                    actionNow = String.format("Check to <%s>! ", color ? "black" : "white");
                    JOptionPane.showMessageDialog(null, actionNow);
                    FileWriter.writeRecordToFile(actionNow);
                }

                refreshPieceChoise(actionNow);

            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Choose a piece!");

            } catch (IncorrectValueOfPieceMove ex) {
                JOptionPane.showMessageDialog(null, "Enter correct move! (exam : d2)");
            }

        });
        return btn;

    }

    private void refreshPieceChoise(String actionNow) {
        this.chess.dispose();
        this.chess = new DisplayBoard(this.board.getBoard());

        this.pieceChoice.removeAll();

        if (!actionNow.equals("")) this.pieceChoice.add(getActionTitle(actionNow));

        this.pieceChoice.add(getMoveTitle());
        this.pieceChoice.add(getRButtonGroup());
        this.move.setText("");


        revalidate();
        repaint();
        pack();
    }

    private JPanel getCastlingBtn() {
        JPanel panel = makePanel(0);
        JButton btn = new JButton("Casting");
        btn.addActionListener(e -> {

            ArrayList<Piece> rooks = this.board.getPiece(PieceTypes.ROOK, this.board.getColorOfMove());
            if (rooks.size() == 0) btn.setEnabled(false);

            JDialog container = new JDialog(this, "Castling", true);
            container.setLayout(new GridLayout(3, 1));
            container.setBounds(600, 100, 600, 500);
            container.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            container.add(getTitleCast());
            container.add(getRBGroupCast(rooks));
            container.add(getBtnCast());

            container.pack();
            container.setVisible(true);
        });

        panel.add(btn);
        return panel;
    }

    private JPanel getTitleCast() {
        JPanel panel = Start.makePanel(20);

        JLabel title = new JLabel(String.format("Choose a ROOK to castling"));
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        return panel;
    }

    private JPanel getRBGroupCast(ArrayList<Piece> rooks) {
        JPanel panel = Start.makePanel(0);

        this.group = new ButtonGroup();

        for (Piece rook : rooks) {
            String rbText = String.format("%s<%s>",
                    PieceTypes.getChar(PieceTypes.ROOK, this.board.getColorOfMove()),
                    ChessBoard.coverNumToCnessCord(rook.getPosition()));
            JRadioButton rb = new JRadioButton(rbText);
            rb.setActionCommand(rook.getIdOfPieceThisType() + "");
            rb.setFont(new Font("Serif", Font.PLAIN, 15));
            this.group.add(rb);
            panel.add(rb);
        }
        return panel;
    }

    private JPanel getBtnCast() {

        JPanel panel = Start.makePanel(0);
        JButton btn = new JButton(" Confirm  ");
        btn.setMaximumSize(new Dimension(100, 30));
        btn.setPreferredSize(new Dimension(100, 30));
        btn.addActionListener(e -> {

            try {
                String type = this.group.getSelection().getActionCommand();

                Piece rook = this.board.getPieceById(Integer.parseInt(type));

                String mess = this.board.castling(this.board.getColorOfMove(), (Rook) rook);

                JOptionPane.showMessageDialog(null, mess);
                FileWriter.writeRecordToFile(mess);
                refreshPieceChoise("");


                JButton butt = (JButton) e.getSource();
                SwingUtilities.getWindowAncestor(butt).dispose();

            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Choose a piece!");
            }

        });
        panel.add(btn);
        return panel;

    }

    private void showPromotion(Piece pawn) {
        if (!(pawn instanceof Pawn)) return;
        PawnPromotion promo = new PawnPromotion((Pawn) pawn, this.board, this.chess, this);

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

// Custom exception class for incorrect piece move value
class IncorrectValueOfPieceMove extends Exception {

}