import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BattleShipBoardSetUp {
    private List<BattleShipTile> battleShipBoard;
    private BattleShipTile[][] btsBtn;

    public BattleShipBoardSetUp() {
        this.btsBtn = new BattleShipTile[10][10];
    }

    public static class BattleShipTile extends JButton {
        private int col;
        private int row;
        private Ship ship;
        private boolean strikedShip = false;
        public BattleShipTile(int row, int col) {
            super();
            this.col = col;
            this.row = row;
        }
        public int getCol() {
            return col;
        }
        public int getRow() {
            return row;
        }
        public boolean hasShip() {
            return ship != null;
        }
        public void createShip(Ship ship) {
            this.ship = ship;
        }
        public void hasBeenShot() {
            this.strikedShip = true;
        }
        public boolean isStrikedShip() {
            return strikedShip;
        }
        public void clearShip() {
            this.ship = null;
        }
    }

    public List<BattleShipTile> createBoard(){
        battleShipBoard = new ArrayList<BattleShipTile>();
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                btsBtn[r][c] = new BattleShipTile(r, c);
                battleShipBoard.add(btsBtn[r][c]);
            }
        }
        return battleShipBoard;
    }

    public BattleShipTile[][] getBtsBtn() {
        return btsBtn;
    }
}
