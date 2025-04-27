import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.io.File;


public class RecursiveListerGUI extends JFrame {
    private JPanel mainPnl;
    private JPanel selectPnl;
    private JPanel displayPnl;
    private JPanel controlPnl;

    private TitledBorder fileBorder;
    private JTextField searchField;
    private JTextArea textArea;
    private JScrollPane scroller;
    private JLabel tittle;

    private JButton startBtn;
    private JButton quitBtn;

    private File filesFound;

    public RecursiveListerGUI() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        createSelectPanel();
        createDisplayPanel();
        createControlPanel();

        setTitle("Personal Recursive Lister");
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

    public void createSelectPanel() {
        selectPnl = new JPanel();
        selectPnl.setLayout(new BorderLayout());

        searchField = new JTextField();

        tittle = new JLabel( "Recursive Lister" );
        tittle.setHorizontalAlignment(SwingConstants.CENTER);


        startBtn = new JButton("Start");
        startBtn.addActionListener(((ActionEvent ae) -> {
            filesFound = chooseFile();
            searchField.setText(filesFound.getAbsolutePath());
            textArea.setText(filesFound.getName() + "\n");
            recursor(filesFound.listFiles(), 1);
        }));

        fileBorder = new TitledBorder("Search a directory");
        selectPnl.setBorder(fileBorder);

        selectPnl.add(tittle, BorderLayout.NORTH);
        selectPnl.add(searchField, BorderLayout.CENTER);
        selectPnl.add(startBtn, BorderLayout.EAST);

    }

    public void createDisplayPanel() {
        displayPnl = new JPanel();
        textArea = new JTextArea(30,75);
        scroller = new JScrollPane(textArea);
        displayPnl.add(scroller);
    }

    public void createControlPanel() {
        controlPnl = new JPanel();
        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });
        controlPnl.add(quitBtn);
    }

    public File chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(null);
        while (result != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "Please select a directory");
            result = fileChooser.showOpenDialog(null);
        }
        return fileChooser.getSelectedFile();
    }

    public void recursor(File[] files, int depth) {
        for (File file : files) {
            String indent = "    ".repeat(depth);
            try {
                if (file.isDirectory()) {
                    textArea.append(indent + "|----- [Folder] " + file.getName() + "\n");
                    recursor(file.listFiles(), depth + 1);
                } else {
                    textArea.append(indent + "|----- " + file.getName() + "\n");
                }
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Something went wrong. Try again.", "Invalid Operation", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}
