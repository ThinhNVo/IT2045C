import com.sun.source.doctree.EntityTree;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SortedListGUI extends JFrame {
    private JPanel mainPnl;
    private JPanel selectPnl;
    private JPanel displayPnl;
    private JPanel controlPnl;

    private TitledBorder insertBorder;
    private TitledBorder searchBorder;
    private JTextField searchField;
    private JTextField insertField;
    private JTextArea textArea;

    private JButton insertBtn;
    private JButton searchBtn;
    private JButton quitBtn;
    private SortedList sortedList = new SortedList();

    public SortedListGUI() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        createSelectPanel();
        createDisplayPanel();
        createControlPanel();

        setTitle("Personal sorted list");
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
        JPanel insertPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        selectPnl.setLayout(new BorderLayout());
        insertPanel.setLayout(new BorderLayout());
        searchPanel.setLayout(new BorderLayout());

        insertField = new JTextField();
        searchField = new JTextField();

        insertBtn = new JButton("Insert");
        insertBtn.addActionListener((ActionEvent ae) -> {
            sortedList.add(insertField.getText().toLowerCase());
            textArea.setText(sortedList.getListItems());
        });

        searchBtn = new JButton("Search");
        searchBtn.addActionListener((ActionEvent ae) -> {
            if (!inputValidation()) {return;};
            textArea.setText(sortedList.get(sortedList.search(searchField.getText().toLowerCase())));
        });

        insertBorder = new TitledBorder("Enter data");
        searchBorder = new TitledBorder("Search data");

        insertPanel.add(insertField, BorderLayout.CENTER);
        insertPanel.add(insertBtn, BorderLayout.EAST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchBtn, BorderLayout.EAST);

        insertPanel.setBorder(insertBorder);
        searchPanel.setBorder(searchBorder);
        selectPnl.add(insertPanel, BorderLayout.NORTH);
        selectPnl.add(searchPanel, BorderLayout.CENTER);
    }

    public void createDisplayPanel() {
        displayPnl = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        displayPnl.add(textArea, BorderLayout.CENTER);
    }

    public void createControlPanel() {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 2, 50, 20));

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });

        controlPnl.add(quitBtn);
    }

    public boolean inputValidation() {
        if (searchField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search term");
            return false;
        } else if (sortedList.size() == 0) {
            JOptionPane.showMessageDialog(this, "Please enter data");
            return false;
        }
        return true;
    }
}
