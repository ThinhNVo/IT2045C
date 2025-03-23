import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeTileBoard {
    private static TicTacToeTile[][] tttbtn = new TicTacToeTile[3][3];
    private List<TicTacToeTile> tiles = new ArrayList<>();

    public List<TicTacToeTile> createBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                tttbtn[r][c] = new TicTacToeTile(r, c);
                tiles.add(tttbtn[r][c]);
            }
        }
        return tiles;
    }


    public void resetGame() {
        int response = JOptionPane.showConfirmDialog(
                null, "Do you want to play another game?", "Game Over", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            clearBoard();
        } else {
            System.exit(0);
        }
    }

    private void clearBoard(){
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                tttbtn[r][c].setText("");
                tttbtn[r][c].setEnabled(true);
            }
        }
    }

    public TicTacToeTile[][] getButtons () {
        return tttbtn;
    }

}
