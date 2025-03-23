import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends TicTacToeTileBoard{
    private static int COL = 3;
    private static int ROW = 3;
    private int moveCount = 0;
    private String player;
    private TicTacToeTileBoard tttBoard;
    private static TicTacToeTile[][] tttbtn;
    private JLabel tittleLabel;

    public TicTacToeGame(JLabel tittleLabel) {
        this.tittleLabel = tittleLabel;
        tttbtn = super.getButtons();
    }

    public void startGame() {
        moveCount = 0;
        player = "X";
        tttBoard = new TicTacToeTileBoard();

        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                int finalRow = r;
                int finalCol = c;

                // Remove any previous listeners to prevent duplication
                if (tttbtn[r][c] != null) {
                    for (ActionListener al : tttbtn[r][c].getActionListeners()) {
                        tttbtn[r][c].removeActionListener(al);
                    }
                }

                tttbtn[r][c].addActionListener((ActionEvent ae) -> {
                    if (!isValidMove(finalRow, finalCol)) {
                        JOptionPane.showMessageDialog(null, "Invalid move. Try again.");
                        return; // Exit if invalid
                    }

                    tttbtn[finalRow][finalCol].setText(player);
                    tttbtn[finalRow][finalCol].setFont(new Font("Arial", Font.BOLD, 40));
                    moveCount++;

                    if (moveCount >= 5 && isWin(player)) {
                        JOptionPane.showMessageDialog(null, "Player " + player + " wins!");

                        tttBoard.resetGame();
                        return;
                    }

                    if (moveCount >= 7 && isTie()) {
                        JOptionPane.showMessageDialog(null, "It's a Tie!");
                        tttBoard.resetGame();
                        return;
                    }

                    // Switch player
                    player = player.equals("X") ? "O" : "X";
                    tittleLabel.setText("Enter move for " + player);
                });
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        if (tttbtn[row][col].getText().isEmpty()) {
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
