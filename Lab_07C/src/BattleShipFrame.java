import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class BattleShipFrame extends JFrame {
    private JPanel scorePnl;
    private JPanel displayPnl;
    private JPanel controlPnl;

    private JLabel strikelbl;
    private JLabel misslbl;
    private JLabel strikeCount;
    private JLabel missCount;
    private JButton restartBtn;
    private JButton quitBtn;

    private static BattleShipBoardSetUp.BattleShipTile[][] btsBtn;
    private BattleShipBoardSetUp boardSetup;
    private BattleShipGame game;

    public BattleShipFrame() {
        // Set up UI
        createScorePanel();
        createDisplayPanel();
        createControlPanel();

        setTitle("Personal Battle Ship");
        setLayout(new BorderLayout());
        add(scorePnl, BorderLayout.NORTH);
        add(displayPnl, BorderLayout.CENTER);
        add(controlPnl, BorderLayout.SOUTH);

        game = new BattleShipGame(boardSetup.getBtsBtn(), strikelbl , misslbl,strikeCount,missCount);
        game.startGame();

        // Set up frame size and position
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * 0.75);
        int frameHeight = 600;
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createScorePanel() {
        scorePnl = new JPanel();
        scorePnl.setLayout(new GridLayout(1, 4));
        misslbl = new JLabel("The MISS counter [0-5]: " + BattleShipGame.currMissCount);
        strikelbl = new JLabel("The STRIKE counter [0-3]: " + BattleShipGame.currStrikeCount);
        missCount = new JLabel("TOTAL MISS counter [0-83]: " + BattleShipGame.totalMissCount);
        strikeCount = new JLabel("TOTAL HIT counter [0-17]: " + BattleShipGame.totalStrikeCount);

        scorePnl.add(misslbl);
        scorePnl.add(strikelbl);
        scorePnl.add(missCount);
        scorePnl.add(strikeCount);
    }

    public void createDisplayPanel() {
        displayPnl = new JPanel(new GridLayout(10, 10));
        boardSetup = new BattleShipBoardSetUp();
        List<BattleShipBoardSetUp.BattleShipTile> buttonList = boardSetup.createBoard();

        for (BattleShipBoardSetUp.BattleShipTile button : buttonList) {
            displayPnl.add(button);
        }
    }

    public void createControlPanel() {
        controlPnl = new JPanel(new GridLayout(1,2));
        restartBtn = new JButton("Restart");
        restartBtn.addActionListener(ae -> game.restartGame());
        quitBtn = new JButton("Quit!");
        quitBtn.addActionListener(ae -> System.exit(0));
        controlPnl.add(restartBtn);
        controlPnl.add(quitBtn);
    }
}
