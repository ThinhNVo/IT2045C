import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleShipGame {
    private BattleShipBoardSetUp.BattleShipTile[][] btsBtn;

    private final JLabel misslbl;
    private final JLabel missCount;
    private final JLabel strikelbl;
    private final JLabel strikeCount;

    public static int currMissCount;
    public static int currStrikeCount;
    public static int totalMissCount;
    public static int totalStrikeCount;

    private List<BattleShipBoardSetUp.BattleShipTile> fiveTilesShip = new ArrayList<>();
    private List<BattleShipBoardSetUp.BattleShipTile> fourTilesShip = new ArrayList<>();
    private List<BattleShipBoardSetUp.BattleShipTile> firstThreeTilesShip = new ArrayList<>();
    private List<BattleShipBoardSetUp.BattleShipTile> secondThreeTilesShip = new ArrayList<>();
    private List<BattleShipBoardSetUp.BattleShipTile> twoTilesShip = new ArrayList<>();
    private boolean sunk5TilesShip = false;
    private boolean sunk4TilesShip = false;
    private boolean sunk1st3TilesShip = false;
    private boolean sunk2st3TilesShip = false;
    private boolean sunk2TilesShip = false;

    private Random rand = new Random();
    private int[] shipCount;
    private Ship[] ships;


    public BattleShipGame(BattleShipBoardSetUp.BattleShipTile[][] btsBtn, JLabel strikelbl, JLabel misslbl, JLabel strikeCount, JLabel missCount) {
        this.btsBtn = btsBtn;
        this.strikelbl = strikelbl;
        this.misslbl = misslbl;
        this.strikeCount = strikeCount;
        this.missCount = missCount;
    }

    public void startGame() {
        createShips();
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                int finalRow = r;
                int finalCol = c;
                btsBtn[finalRow][finalCol].addActionListener((ActionEvent ae) -> {
                    btsBtn[finalRow][finalCol].setEnabled(false);
                    if (isHitOrMiss(finalRow, finalCol)) {
                        JOptionPane.showMessageDialog(strikelbl, "You win!");
                        restartGame();
                    }
                    misslbl.setText("The MISS counter [0-5]: " + currMissCount);
                    strikelbl.setText("The STRIKE counter [0-3]: " + currStrikeCount);
                    missCount.setText("TOTAL MISS counter [0-83]: " + totalMissCount);
                    strikeCount.setText("TOTAL HIT counter [0-17]: " + totalStrikeCount);
                });
            }

        }
    }

    private boolean isHitOrMiss(int r, int c) {
        if (btsBtn[r][c].hasShip()) {
            totalStrikeCount++;
            currMissCount = 0;
            btsBtn[r][c].setText("x");
            btsBtn[r][c].hasBeenShot();
            return checkAllShips();
        } else if (currMissCount == 5) {
            currMissCount = 0;
            currStrikeCount++;
            btsBtn[r][c].setText("M");
        } else {
            currMissCount++;
            totalMissCount++;
            btsBtn[r][c].setText("M");
        }

        if (currStrikeCount == 3) {
            JOptionPane.showMessageDialog(null, "You loose!");
            restartGame();
        }

        return false;
    }

    private boolean checkAllShips() {
        if (isShipSunk(fiveTilesShip) && !sunk5TilesShip) {
            JOptionPane.showMessageDialog(null, "You sank a 5-tile ship!");
            sunk5TilesShip = true;
        }
        if (isShipSunk(fourTilesShip) && !sunk4TilesShip) {
            JOptionPane.showMessageDialog(null, "You sank a 4-tile ship!");
            sunk4TilesShip = true;
        }
        if (isShipSunk(firstThreeTilesShip) && !sunk1st3TilesShip) {
            JOptionPane.showMessageDialog(null, "You sank a 3-tile ship!");
            sunk1st3TilesShip = true;
        }
        if (isShipSunk(secondThreeTilesShip) && !sunk2st3TilesShip) {
            JOptionPane.showMessageDialog(null, "You sank a 3-tile ship!");
            sunk2st3TilesShip = true;
        }
        if (isShipSunk(twoTilesShip) && !sunk2TilesShip) {
            JOptionPane.showMessageDialog(null, "You sank a 2-tile ship!");
            sunk2TilesShip = true;
        }
        return isShipSunk(fiveTilesShip)
                && isShipSunk(fourTilesShip)
                && isShipSunk(firstThreeTilesShip)
                && isShipSunk(secondThreeTilesShip)
                && isShipSunk(twoTilesShip);
    }

    private boolean isShipSunk(List<BattleShipBoardSetUp.BattleShipTile> ships) {

        for (BattleShipBoardSetUp.BattleShipTile tile : ships) {
            if (!tile.isStrikedShip()) {
                return false;
            }
        }
        return true;
    }

    private void createShips() {
        shipCount = new int[]{5, 4, 2, 3, 3};
        ships = new Ship[shipCount.length];
        boolean created = false;

        for (int i = 0; i < shipCount.length; i++) {
            ships[i] = new Ship(shipCount[i]);
            created = false;
            while(!created) {
                int r = rand.nextInt(10) ;
                int c = rand.nextInt(10);
                boolean vertical = rand.nextBoolean();

                if (isAvailable(r, c, shipCount[i], vertical))
                {
                    placeShip(r, c, ships[i], shipCount[i], vertical);
                    created = true;
                }
            }

        }
    }

    private boolean isAvailable(int r, int c, int size, boolean vertical) {
        if (vertical  && size+r > 10) return false;
        if (!vertical && size+c > 10) return false;

        if (vertical) {
            for (int i = 0; i < size; i++)
            {
                if (btsBtn[r+i][c].hasShip()) return false;
            }
        } else
        {
            for (int i = 0; i < size; i++)
            {
                if (btsBtn[r][c+i].hasShip()) return false;

            }
        }
        return true;
    }

    private void placeShip(int r, int c, Ship ship, int size, boolean vertical) {
        if (vertical)
        {
            for (int i = 0; i < size; i++)
            {
                btsBtn[r+i][c].createShip(ship);

                if (firstThreeTilesShip.size() <= 2) {
                    switch (size) {
                        case 5: fiveTilesShip.add(btsBtn[r + i][c]);break;
                        case 4: fourTilesShip.add(btsBtn[r + i][c]);break;
                        case 3: firstThreeTilesShip.add(btsBtn[r + i][c]);break;
                        case 2: twoTilesShip.add(btsBtn[r + i][c]);break;
                    }
                } else {
                    secondThreeTilesShip.add(btsBtn[r + i][c]);
                }
            }
        } else {
            for (int i = 0; i < size; i++)
            {
                btsBtn[r][c+i].createShip(ship);

                if (firstThreeTilesShip.size() <= 2) {
                    switch (size) {
                        case 5: fiveTilesShip.add(btsBtn[r][c+i]);break;
                        case 4: fourTilesShip.add(btsBtn[r][c+i]);break;
                        case 3: firstThreeTilesShip.add(btsBtn[r][c+i]);break;
                        case 2: twoTilesShip.add(btsBtn[r][c+i]);break;
                    }
                } else {
                    secondThreeTilesShip.add(btsBtn[r][c+i]);
                }
            }
        }
    }

    public void restartGame() {
        int response = JOptionPane.showConfirmDialog(
                null, "Do you want to start a new game?", "Restart Game", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            clearBoard();
            misslbl.setText("The MISS counter [0-5]: " + currMissCount);
            strikelbl.setText("The STRIKE counter [0-3]: " + currStrikeCount);
            missCount.setText("TOTAL MISS counter [0-83]: " + totalMissCount);
            strikeCount.setText("TOTAL HIT counter [0-17]: " + totalStrikeCount);
            startGame();
        } else {
            System.exit(0);
        }
    }

    private void clearBoard() {
        currMissCount = 0; currStrikeCount = 0; totalMissCount = 0; totalStrikeCount = 0;
        fiveTilesShip.clear(); fourTilesShip.clear(); firstThreeTilesShip.clear(); secondThreeTilesShip.clear(); twoTilesShip.clear();
        sunk5TilesShip = false; sunk4TilesShip = false; sunk1st3TilesShip = false; sunk2st3TilesShip = false; sunk2TilesShip = false;
        ships = null;
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                btsBtn[r][c].setText("");
                btsBtn[r][c].clearShip();
                btsBtn[r][c].setEnabled(true);
                for (ActionListener al : btsBtn[r][c].getActionListeners()) {
                    btsBtn[r][c].removeActionListener(al);
                }
            }
        }

    }
}
