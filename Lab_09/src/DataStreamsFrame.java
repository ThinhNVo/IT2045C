import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;


public class DataStreamsFrame extends JFrame {
    private JPanel mainPnl;
    private JPanel selectPnl;// Top
    private JPanel displayPnl; // Center
    private JPanel controlPnl; // Bottom

    private TitledBorder fileBorder;
    private JTextArea textB;
    private JTextArea searchB;
    private JScrollPane textScroll;
    private JScrollPane searchScroll;
    private JTextField fileField;
    private JLabel fileLabel;

    private JButton selectFileBtn;
    private JButton searchBtn;
    private JButton quitBtn;

    private FileHandler selectedFile = new FileHandler();

    public DataStreamsFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());// Set up UI
        createSelectPanel();
        createDisplayPanel();
        createControlPanel();

        setTitle("Personal Tags Extractor");
        mainPnl.add(selectPnl, BorderLayout.NORTH);
        mainPnl.add(displayPnl, BorderLayout.CENTER);
        mainPnl.add(controlPnl, BorderLayout.SOUTH);

        add(mainPnl);


        // Set up frame size and position
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * 0.75);
        int frameHeight = 600;
        setSize(frameWidth, frameHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createSelectPanel() {
        selectPnl = new JPanel();
        selectPnl.setLayout(new BorderLayout());
        fileField = new JTextField();
        fileLabel = new JLabel();
        fileLabel.setHorizontalAlignment(SwingConstants.CENTER);

        selectFileBtn = new JButton("Select a file");
        selectFileBtn.addActionListener(((ActionEvent ae) -> {
            fileLabel.setText(System.getProperty("user.dir") + "\\src\\" + selectedFile.chooseFile());
        }));

        fileBorder = new TitledBorder("Select File");

        selectPnl.setBorder(fileBorder);
        selectPnl.add(selectFileBtn, BorderLayout.EAST);
        selectPnl.add(fileField, BorderLayout.CENTER);
        selectPnl.add(fileLabel, BorderLayout.SOUTH);
    }

    private void createDisplayPanel() {
        displayPnl = new JPanel(new GridLayout(1, 2));
        textB = new JTextArea(21, 40);
        textB.setEditable(false);
        textScroll = new JScrollPane(textB);
        searchB = new JTextArea(21, 40);
        searchB.setEditable(false);
        searchScroll = new JScrollPane(searchB);
        displayPnl.add(textScroll, BorderLayout.WEST);
        displayPnl.add(searchScroll, BorderLayout.EAST);
    }

    private void createControlPanel() {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 2, 50, 20));

        searchBtn = new JButton("Search");
        searchBtn.addActionListener((ActionEvent ae) -> {
            if (selectedFile.fileSelected()) {
                JOptionPane.showMessageDialog(null, "Please select a file first.");
                return;
            }
            if (fileField.getText().trim().isEmpty() ) {
                JOptionPane.showMessageDialog(null, "Please enter a word to search for!");
                return;
            }
            GUIUpdate(selectedFile.scanFile(), selectedFile.searchFile(fileField.getText()));
        });

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });

        controlPnl.add(searchBtn);
        controlPnl.add(quitBtn);
    }

    public void GUIUpdate(List<String> orgFile, List<String> searchSentences) {
        textB.setText("");
        searchB.setText("");
        int searchResult = 0;
        for (String items : orgFile) {
            textB.append(items + "\n");
        }
        for (String items : searchSentences) {
            searchResult++;
            searchB.append(searchResult + ": " + items + "\n");
        }

    }
}

