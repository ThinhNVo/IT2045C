import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TicTacToeFrame extends JFrame {
    private JPanel tittlePnl; // Top
    private static JPanel displayPnl; // Center
    private JPanel controlPnl; // Bottom
    private JLabel tittleLbl;
    private JButton quitBtn;
    private static TicTacToeTile[][] tttbtn;
    private TicTacToeGame TicTacToe;

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

        //start tictactoe game;
        TicTacToe.startGame();

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
        tittleLbl = new JLabel("Enter move for X", JLabel.CENTER);
        tittleLbl.setFont(new Font("Arial", Font.BOLD, 20));
        tittlePnl.add(tittleLbl);
    }

    private void createDisplayPanel() {
        displayPnl = new JPanel(new GridLayout(3, 3));
        TicTacToe = new TicTacToeGame(tittleLbl);
        List<TicTacToeTile> buttonList = TicTacToe.createBoard();

        for (TicTacToeTile button : buttonList) {
            displayPnl.add(button);
        }
    }

    private void createControlPanel() {
        controlPnl = new JPanel();
        quitBtn = new JButton("Quit!");
        quitBtn.setFont(new Font("ALGERIAN", Font.PLAIN, 20));
        quitBtn.addActionListener(ae -> System.exit(0));
        controlPnl.add(quitBtn);
    }
}

