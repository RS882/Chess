package PlayGUI;

import ChessBoard.ChessBoard;
import Piece.PieceTypes;
import Pieces.Pawn;

import javax.swing.*;
import java.awt.*;

public class PawnPromotion extends JDialog {

    private Pawn pawn; // Pawn being promoted
    private String pawnPos; // Pawn's position
    private ButtonGroup group; // Button group for piece selection
    private ChessBoard board; // Chessboard reference
    private DisplayBoard chess; // DisplayBoard reference

    public PawnPromotion(Pawn pawn, ChessBoard board, DisplayBoard chess, Frame parentContainer) {
        // Constructor for PawnPromotion dialog
        this.pawn = pawn; // Set the pawn being promoted
        this.board = board; // Set the chessboard reference
        this.chess = chess; // Set the DisplayBoard reference
        this.pawnPos = ChessBoard.coverNumToCnessCord(this.pawn.getPosition()); // Get pawn's position in chess notation

        // Initialize JDialog container
        JDialog container = new JDialog(parentContainer, "Pawn`s promotion", true);
        container.setLayout(new GridLayout(3, 1)); // Set layout for container
        container.setBounds(600, 100, 600, 500); // Set size and position
        container.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Set close operation

        // Add components to the container
        container.add(getTitlePanel());
        container.add(getButtonGroupPanel());
        container.add(getConfirmButton());

        container.pack(); // Pack components
        container.setVisible(true); // Set container visible
    }

    // Get the panel containing the confirm button
    private JPanel getConfirmButton() {
        JPanel panel = Start.makePanel(0); // Create panel
        JButton btn = new JButton(" Confirm  "); // Create Confirm button
        btn.setMaximumSize(new Dimension(100, 30)); // Set button size
        btn.setPreferredSize(new Dimension(100, 30)); // Set button size

        // Add ActionListener to the button
        btn.addActionListener(e -> {
            String type = this.group.getSelection().getActionCommand(); // Get selected piece type

            this.board.promotingOfPawn(this.pawn, type); // Promote the pawn on the chessboard

            this.chess.dispose(); // Dispose the current chess display
            this.chess = new DisplayBoard(this.board.getBoard()); // Create a new chess display with the updated board

            // Show promotion message
            String mess = String.format("PAWN was promoted to %s <%s>.%n", type, this.pawnPos);
            JOptionPane.showMessageDialog(null, mess);

            JButton butt = (JButton) e.getSource();
            SwingUtilities.getWindowAncestor(butt).dispose(); // Close the dialog window
        });
        panel.add(btn); // Add button to the panel
        return panel; // Return the panel
    }

    // Get the panel containing the title for pawn promotion
    private JPanel getTitlePanel() {
        JPanel panel = Start.makePanel(20); // Create panel
        JLabel title = new JLabel(String.format("PAWN <%s> can be promoted.%n", this.pawnPos)); // Create title label
        title.setFont(new Font("Arial", Font.PLAIN, 20)); // Set font for the title
        title.setAlignmentX(Component.CENTER_ALIGNMENT); // Align title to center

        panel.add(title); // Add title to the panel
        return panel; // Return the panel
    }

    // Get the panel containing the button group for piece selection
    private JPanel getButtonGroupPanel() {
        JPanel panel = Start.makePanel(0); // Create panel
        this.group = new ButtonGroup(); // Initialize button group

        // Create radio buttons for available piece types
        for (PieceTypes el : PieceTypes.values()) {
            if (el == PieceTypes.KING || el == PieceTypes.PAWN) continue; // Skip King and Pawn types
            String rbText = "" + PieceTypes.getChar(el, this.pawn.getColor()); // Get piece symbol
            JRadioButton rb = new JRadioButton(rbText); // Create radio button
            if (el == PieceTypes.QUEEN) rb.setSelected(true); // Set Queen as default selection
            rb.setActionCommand(el + ""); // Set action command for the radio button
            rb.setFont(new Font("Serif", Font.PLAIN, 15)); // Set font for the radio button

            this.group.add(rb); // Add radio button to the group
            panel.add(rb); // Add radio button to the panel
        }
        return panel; // Return the panel
    }
}
