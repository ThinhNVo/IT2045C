import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class TicTacToeFrame extends JFrame {
    private JPanel tittlePnl; // Top
    private static JPanel displayPnl; // Center
    private JPanel controlPnl; // Bottom
    private static TicTacToeTile[][] tttbtn;
    private JLabel tittleLbl;
    private JButton quitBtn;
    private int moveCount;
    private static int ROW = 3;
    private static int COL = 3;
    private String player = "X"; // Current player
    private int turn = 0;

    public TicTacToeFrame() {
        // Set up UI
        createTitlePanel();
        createDisplayPanel();
        createControlPanel();

        setTitle("Personal TicTacToe");
        setLayout(new BorderLayout());
        add(tittlePnl, BorderLayout.NORTH);
        add(displayPnl, BorderLayout.CENTER);
        add(controlPnl, BorderLayout.SOUTH);

        createGameLogic();

        // Set up frame size and position
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * 0.75);
        int frameHeight = 600;
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTitlePanel() {
        tittlePnl = new JPanel();
        tittleLbl = new JLabel("Enter move for " + player, JLabel.CENTER);
        tittleLbl.setFont(new Font("Arial", Font.BOLD, 20));
        tittlePnl.add(tittleLbl);
    }

    private void createDisplayPanel() {
        displayPnl = new JPanel(new GridLayout(3, 3));
        tttbtn = new TicTacToeTile[3][3];

        for (int r = turn; r < ROW; r++) {
            for (int c = turn; c < COL; c++) {
                tttbtn[r][c] = new TicTacToeTile(r, c);
                displayPnl.add(tttbtn[r][c]);

            }
        }
    }

    private void createControlPanel() {
        controlPnl = new JPanel();
        quitBtn = new JButton("Quit!");
        quitBtn.setFont(new Font("ALGERIAN", Font.PLAIN, 20));
        quitBtn.addActionListener(ae -> System.exit(0));
        controlPnl.add(quitBtn);
    }

    private void createGameLogic() {
        moveCount = 0;
        player = "X";
        tittleLbl.setText("Enter move for " + player);

        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                int finalRow = r;
                int finalCol = c;

                tttbtn[r][c].setText("");
                tttbtn[r][c].setEnabled(true);

                tttbtn[r][c].addActionListener((ActionEvent ae) -> {
                    if (!isValidMove(finalRow, finalCol)) {
                        JOptionPane.showMessageDialog(this, "Invalid move. Try again.");
                        return;
                    }

                    tttbtn[finalRow][finalCol].setText(player);
                    tttbtn[finalRow][finalCol].setFont(new Font("Arial", Font.BOLD, 40));
                    moveCount++;

                    if (moveCount >= 5 && isWin(player)) {
                        JOptionPane.showMessageDialog(this, "Player " + player + " wins!");
                        resetGame();
                        return;
                    }

                    if (moveCount >= 7 && isTie()) {
                        JOptionPane.showMessageDialog(this, "It's a Tie!");
                        resetGame();
                        return;
                    }

                    player = player.equals("X") ? "O" : "X";
                    tittleLbl.setText("Enter move for " + player);
                });
            }
        }
    }

    private void resetGame() {
        int response = JOptionPane.showConfirmDialog(
                this, "Do you want to play another game?", "Game Over", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            clearBoard();
            createGameLogic();
        } else {
            System.exit(0);
        }
    }

    private void clearBoard(){
        for (int r = turn; r < ROW; r++) {
            for (int c = turn; c < COL; c++) {
                tttbtn[r][c].setText("");

            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        if (tttbtn[row][col].getText().equals("")) {
            return true;

        }
        return false;
    }

    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (tttbtn[0][col].getText().equals(player) &&
                    tttbtn[1][col].getText().equals(player) &&
                    tttbtn[2][col].getText().equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (tttbtn[row][0].getText().equals(player) &&
                    tttbtn[row][1].getText().equals(player) &&
                    tttbtn[row][2].getText().equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (tttbtn[0][0].getText().equals(player) &&
                tttbtn[1][1].getText().equals(player) &&
                tttbtn[2][2].getText().equals(player)) ||

                (tttbtn[0][2].getText().equals(player) &&
                        tttbtn[1][1].getText().equals(player) &&
                        tttbtn[2][0].getText().equals(player));
    }

    private static boolean isTie() {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties

        for(int row=0; row < ROW; row++)
        {
            if(tttbtn[row][0].getText().equals("X") ||
                    tttbtn[row][1].getText().equals("X") ||
                    tttbtn[row][2].getText().equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(tttbtn[row][0].getText().equals("O") ||
                    tttbtn[row][1].getText().equals("O") ||
                    tttbtn[row][2].getText().equals("O"))
            {
                oFlag = true; // there is an O in this row
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;

        }
        // Now scan the columns
        for(int col=0; col < COL; col++)
        {
            if(tttbtn[0][col].getText().equals("X") ||
                    tttbtn[1][col].getText().equals("X") ||
                    tttbtn[2][col].getText().equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(tttbtn[0][col].getText().equals("O") ||
                    tttbtn[1][col].getText().equals("O") ||
                    tttbtn[2][col].getText().equals("O"))
            {
                oFlag = true; // there is an O in this col
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }
        // Now check for the diagonals
        xFlag = oFlag = false;

        if(tttbtn[0][0].getText().equals("X") ||
                tttbtn[1][1].getText().equals("X") ||
                tttbtn[2][2].getText().equals("X") )
        {
            xFlag = true;
        }
        if(tttbtn[0][0].getText().equals("O") ||
                tttbtn[1][1].getText().equals("O") ||
                tttbtn[2][2].getText().equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(tttbtn[0][2].getText().equals("X") ||
                tttbtn[1][1].getText().equals("X") ||
                tttbtn[2][0].getText().equals("X") )
        {
            xFlag =  true;
        }
        if(tttbtn[0][2].getText().equals("O") ||
                tttbtn[1][1].getText().equals("O") ||
                tttbtn[2][0].getText().equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }

        // Checked every vector so I know I have a tie
        return true;
    }
}

