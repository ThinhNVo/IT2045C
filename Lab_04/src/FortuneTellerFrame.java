import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class FortuneTellerFrame extends JFrame {
    JPanel mainPnl;
    JPanel iconPnl;  // Top
    JPanel displayPnl; // Center
    JPanel controlPnl; // Bottom

    JTextArea displayTA;
    JScrollPane scroller;

    JLabel titleLbl;
    ImageIcon icon;
    Toolkit kit;

    JButton fortuneBtn;
    JButton quitBtn;

    int curr = 0;
    Random rnd = new Random();

    public FortuneTellerFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        mainPnl.setBackground(new Color(1, 6, 13));

        createIconPanel();
        mainPnl.add(iconPnl, BorderLayout.NORTH);
        iconPnl.setBackground(new Color(1, 6, 13));

        createDisplayPanel();
        mainPnl.add(displayPnl, BorderLayout.CENTER);
        displayPnl.setBackground(new Color(1, 6, 13));

        createControlPanel();
        mainPnl.add(controlPnl, BorderLayout.SOUTH);
        controlPnl.setBackground(new Color(1, 6, 13));

        add(mainPnl);
        setTitle("Your Fortunate Telling");

        // Get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int frameWidth = (int) (screenWidth * 0.75);
        int frameHeight = 600;
        int xPosition = (screenWidth - frameWidth) / 2;
        int yPosition = (screenHeight - frameHeight) / 2;
        setSize(frameWidth, frameHeight);
        setLocation(xPosition, yPosition); // Center it
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createIconPanel() {
        iconPnl = new JPanel();
        icon = new ImageIcon("src/pills.jpg");
        titleLbl = new JLabel("Choose your fate today!", icon, JLabel.CENTER);

        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setFont(new Font("Britannic BOLD", Font.PLAIN, 36));
        titleLbl.setForeground(Color.WHITE);
        iconPnl.add(titleLbl);
    }

    private void createDisplayPanel() {
        displayPnl = new JPanel();
        displayTA = new JTextArea(9, 40);
        displayTA.setEditable(false);
        displayTA.setFont(new Font("Cascadia Mono", Font.PLAIN, 15));
        scroller = new JScrollPane(displayTA);
        displayPnl.add(scroller);
    }

    private void createControlPanel() {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1,4));

        fortuneBtn = new JButton("Get Fortune!");
        fortuneBtn.setFont(new Font("ALGERIAN", Font.PLAIN, 20));
        fortuneBtn.addActionListener((ActionEvent ae) ->
                {
                    String[] fortunes = {
                            "You will find your dream internship... at least in your dream.",
                            "Someone is at your window.",
                            "An adventure is waiting for you... that's all in this fortune.",
                            "A pleasant surprise awaits you.",
                            "Tomorrow will be a great day! For someone else, probably.",
                            "A surprise awaits you… it's disappointment, but at least it’s unexpected.",
                            "The universe has a plan for you. It’s mostly laughing at your life choices.",
                            "Your kindness will bring unexpected rewards.",
                            "Your future is full of opportunity. Too bad you’ll sleep through it.",
                            "Trust yourself, and success will follow.",
                            "Your future looks bright... too bad you're staring at a train light.",
                            "You will soon find true love... or a new way to embarrass yourself.",
                            "You are in luck for some fun... with tax season.",
                    };

                    int rndFortune = rnd.nextInt(fortunes.length);

                    if (curr != rndFortune) {
                        displayTA.append(fortunes[rndFortune] + "\n");
                    } else {
                        do {
                            rndFortune = rnd.nextInt(fortunes.length);
                        } while (rndFortune == curr);
                        displayTA.append(fortunes[rndFortune] + "\n");
                    }
                    curr = rndFortune;
                }
        );

        quitBtn = new JButton("Quit!");
        quitBtn.setFont(new Font("ALGERIAN", Font.PLAIN, 20));
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        controlPnl.add(fortuneBtn);
        controlPnl.add(quitBtn);

    }
}
