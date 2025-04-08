import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class TagExtractorFrame extends JFrame {
    private JPanel mainPnl;
    private JPanel selectPnl;// Top
    private JPanel displayPnl; // Center
    private JPanel controlPnl; // Bottom

    private TitledBorder fileBorder;
    private JTextArea tagB;
    private JScrollPane scroller;
    private JTextField fileField;

    private JButton selectFileBtn;
    private JButton saveBtn;
    private JButton clearBtn;

    private FileHandler selectedFile = new FileHandler();

    public TagExtractorFrame() {
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

        selectFileBtn = new JButton("Select");
        selectFileBtn.addActionListener(((ActionEvent ae) -> {
            fileField.setText(selectedFile.chooseFile());
            GUIUpdate(selectedFile.scanFile());
        }));

        fileBorder = new TitledBorder("Select File");

        selectPnl.setBorder(fileBorder);
        selectPnl.add(selectFileBtn, BorderLayout.WEST);
        selectPnl.add(fileField, BorderLayout.CENTER);
    }

    private void createDisplayPanel() {
        displayPnl = new JPanel();
        tagB = new JTextArea(21,45);
        tagB.setEditable(false);
        scroller = new JScrollPane(tagB);
        displayPnl.add(scroller);
    }
    private void createControlPanel() {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 2, 50, 20));

        saveBtn = new JButton("Save");
        saveBtn.addActionListener((ActionEvent ae) -> {
            selectedFile.saveFile();
        });

        clearBtn = new JButton("Clear");
        clearBtn.addActionListener((ActionEvent ae) -> {
            selectedFile.clearFile();
            tagB.setText("");
            fileField.setText("");
        });

        controlPnl.add(saveBtn);
        controlPnl.add(clearBtn);
    }

    public void GUIUpdate(Map<String,Integer> wordMap) {
        selectedFile.clearFile();
        tagB.setText("");
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            tagB.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }
    }



}

