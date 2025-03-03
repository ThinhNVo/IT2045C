import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame implements Strategy {
    private JPanel mainPnl;
    private JPanel boxPnl;// Top
    private JPanel displayPnl; // Center
    private JPanel controlPnl;// Bottom

    private JButton Rock;
    private JButton Paper;
    private JButton Scissors;

    private ImageIcon scissorimg;
    private ImageIcon rockimg;
    private ImageIcon paperimg;
    private JButton quitBtn;

    private ImageIcon[] imgs;
    private int currImg = 0;
    private JLabel lblImg;
    private JTextArea scoreB;
    private JLabel lblScore;
    private JScrollPane scroller;

    private int turn;
    Random rnd = new Random();
    private ArrayList<Integer> mostUsed;
    private int computerS;
    private int humanS;
    private int ties;


    public RockPaperScissorsFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        // Set up UI
        createBoxPanel();
        createScorePanel();
        createDisplayPanel();
        createControlPanel();

        setTitle("Can you beat the machine?");
        mainPnl.setLayout(new BorderLayout());
        mainPnl.add(boxPnl, BorderLayout.NORTH);
        mainPnl.add(displayPnl, BorderLayout.CENTER);
        mainPnl.add(controlPnl, BorderLayout.SOUTH);

        add(mainPnl);
        gameLogic();

        // Set up frame size and position
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * 0.75);
        int frameHeight = 600;
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createBoxPanel() {
        boxPnl = new JPanel();
        boxPnl.setLayout(new BorderLayout());

        imgs = new ImageIcon[4];
        imgs[0] = new ImageIcon("src/computer.jpg");
        imgs[1] = new ImageIcon("src/rock.jpg");
        imgs[2] = new ImageIcon("src/scissors.jpg");
        imgs[3] = new ImageIcon("src/paper.jpg");

        String title = "Can you beat the machine?";
        lblImg = new JLabel(title, imgs[currImg], JLabel.CENTER);
        lblImg.setVerticalTextPosition(JLabel.BOTTOM);
        lblImg.setHorizontalTextPosition(JLabel.CENTER);

        boxPnl.add(lblImg, BorderLayout.NORTH);

    }

    private void createScorePanel() {
        scoreB = new JTextArea(7, 30);
        scoreB.setEditable(false);
        scoreB.setFont(new Font("Cascadia Mono", Font.PLAIN, 15));
        scroller = new JScrollPane(scoreB);
        boxPnl.add(scroller, BorderLayout.CENTER);
    }

    private void createDisplayPanel() {
        displayPnl = new JPanel(new GridLayout(1, 3, 30, 80));

        rockimg = new ImageIcon("src/humanrock.jpg");
        scissorimg = new ImageIcon("src/humanscissor.jpg");
        paperimg = new ImageIcon("src/humanpaper.jpg");

        Rock = new JButton(rockimg);
        Scissors = new JButton(scissorimg);
        Paper = new JButton(paperimg);

        displayPnl.add(Rock);
        displayPnl.add(Scissors);
        displayPnl.add(Paper);
    }

    private void createControlPanel() {
        controlPnl = new JPanel();
        quitBtn = new JButton("Quit!");
        quitBtn.setFont(new Font("ALGERIAN", Font.PLAIN, 20));
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        controlPnl.add(quitBtn);
    }

    private void gameLogic() {
        mostUsed = new ArrayList<Integer>();
        turn = 1;
        Rock.addActionListener((ActionEvent ae) -> {
            mostUsed.add(1);
            int decision = determinedMove(turn);
            if (decision == 1) {
                ties++;
                lblImg.setText("<html>It's a tie!<br>Computer Score: " + computerS +
                        " Human Score: " + humanS +
                        " Ties: " + ties + "</html>");
                scoreB.append("Rock meets Rock (Everyone ties)" + "\n");
            } else if (decision == 2) {
                humanS++;
                lblImg.setText("<html>Player wins<br>Computer Score: " + computerS +
                        " Human Score: " + humanS +
                        " Ties: " + ties + "</html>");
                scoreB.append("Rock breaks scissors (Player wins)" + "\n");
            } else {
                computerS++;
                lblImg.setText("<html>Computer wins<br>Computer Score: " + computerS +
                        " Human Score: " + humanS +
                        " Ties: " + ties + "</html>");
                scoreB.append("Paper covers Rock (Computer wins)" + "\n");
            }
            turn++;
            lblImg.setIcon(imgs[decision]);
        });
        Scissors.addActionListener((ActionEvent ae) -> {
            mostUsed.add(2);
            int decision = determinedMove(turn);
            if (decision == 1) {
                computerS++;
                lblImg.setText("<html>Computer wins<br>Computer Score: " + computerS +
                        " Human Score: " + humanS +
                        " Ties: " + ties + "</html>");
                scoreB.append("Rock breaks scissors (Computer wins)" + "\n");
            } else if (decision == 2) {
                ties++;
                lblImg.setText("<html>It's a tie!<br>Computer Score: " + computerS +
                        " Human Score: " + humanS +
                        " Ties: " + ties + "</html>");
                scoreB.append("Scissors meet scissors (Everyone ties)" + "\n");
            } else {
                humanS++;
                lblImg.setText("<html>Player wins<br>Computer Score: " + computerS +
                        " Human Score: " + humanS +
                        " Ties: " + ties + "</html>");
                scoreB.append("Scissors cut paper (Player wins)" + "\n");
            }
            turn++;
            lblImg.setIcon(imgs[decision]);
        });
        Paper.addActionListener((ActionEvent ae) -> {
            mostUsed.add(3);
            int decision = determinedMove(turn);
            if (decision == 1) {
                lblImg.setText("<html>Player wins<br>Computer Score: " + computerS +
                        " Human Score: " + humanS +
                        " Ties: " + ties + "</html>");
                scoreB.append("Paper covers Rock (Player wins)" + "\n");
            } else if (decision == 2) {
                lblImg.setText("<html>Computer wins<br>Computer Score: " + computerS +
                        " Human Score: " + humanS +
                        " Ties: " + ties + "</html>");
                scoreB.append("Scissors cut paper (Computer wins)" + "\n");
            } else {
                lblImg.setText("<html>It's a tie!<br>Computer Score: " + computerS +
                        " Human Score: " + humanS +
                        " Ties: " + ties + "</html>");
                scoreB.append("Paper meets paper (Everyone ties)" + "\n");
            }
            turn++;
            lblImg.setIcon(imgs[decision]);
        });
    }


    @Override
    public int determinedMove(int move) {
        int rndNum = rnd.nextInt(5) + 1;
        System.out.println(rndNum);
        int trueGame = rnd.nextInt(3) + 1;
        if (rndNum == 1) {
            return findLeastFrequent(mostUsed);
        } else if (rndNum == 2) {
            return findMostFrequent(mostUsed);
        } else if (rndNum == 3) {
            return move == 1 ? trueGame : mostUsed.get(mostUsed.size()-2);
        } else if (rndNum == 5) {
            if (mostUsed.getLast() == 1) {
                return 3;
            } else if (mostUsed.getLast() == 2) {
                return 1;
            } else {
                return 2;
            }
        }
        return trueGame;
    }

    public static int findMostFrequent(ArrayList<Integer> list) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int mostFrequent = -1, maxCount = 0;
        for (int num : list) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            if (countMap.get(num) > maxCount) {
                maxCount = countMap.get(num);
                mostFrequent = num;
            }
        }
        if (mostFrequent == 1) {
            return 3;
        } else if (mostFrequent == 2) {
            return 1;
        } else {
            return 2;
        }
    }

    public static int findLeastFrequent(ArrayList<Integer> list) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int leastFrequent = -1, minCount = Integer.MAX_VALUE;
        for (int num : list) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() < minCount) {
                minCount = entry.getValue();
                leastFrequent = entry.getKey();
            }
        }
        if (leastFrequent == 1) {
            return 3;
        } else if (leastFrequent == 2) {
            return 1;
        } else {
            return 2;
        }
    }
}


