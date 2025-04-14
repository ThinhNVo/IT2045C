import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class RandProductSearch extends JFrame {
    private JPanel mainPnl;
    private JPanel selectPnl;
    private JPanel displayPnl;
    private JPanel controlPnl;

    private TitledBorder fileBorder;
    private JTextField searchField;
    private JTextArea foundArea;
    private JLabel matchFound;

    private JButton searchBtn;
    private JButton quitBtn;

    private Product product;
    private int position = 0;
    private int recordsFound = 0;
    ArrayList<Product> products;

    public RandProductSearch() {
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

        searchField = new JTextField();

        matchFound = new JLabel( "Match Found: 0");
        matchFound.setHorizontalAlignment(SwingConstants.CENTER);

        String filePath = System.getProperty("user.dir") + "\\src\\randFiles.txt";
        searchBtn = new JButton("Search");
        searchBtn.addActionListener((ActionEvent ae) -> {
            recordsFound = 0;
            foundArea.setText("");
            readFromFile(filePath, position);

        });

        fileBorder = new TitledBorder("Enter product data");
        selectPnl.setBorder(fileBorder);
        selectPnl.add(searchField, BorderLayout.CENTER);
        selectPnl.add(searchBtn, BorderLayout.EAST);
        selectPnl.add(matchFound, BorderLayout.SOUTH);
    }

    private void createDisplayPanel() {
        displayPnl = new JPanel(new BorderLayout());
        foundArea = new JTextArea();
        foundArea.setEditable(false);
        displayPnl.add(foundArea, BorderLayout.CENTER);
    }

    private void createControlPanel() {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 2, 50, 20));

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });

        controlPnl.add(quitBtn);
    }


    private void readFromFile(String filePath, int position) {
        products = new ArrayList<>();
        try(RandomAccessFile file = new RandomAccessFile(filePath, "r");) {

            while (file.getFilePointer() + 124 < file.length()) {
                file.seek(position);

                byte[] nameBytes = new byte[35];
                byte[] descBytes = new byte[75];
                byte[] idBytes = new byte[6];


                file.read(nameBytes);
                file.read(descBytes);
                file.read(idBytes);
                double price = file.readDouble();

                product = new Product(
                        new String(nameBytes, StandardCharsets.UTF_8).trim(),
                        new String(descBytes, StandardCharsets.UTF_8).trim(),
                        new String(idBytes, StandardCharsets.UTF_8).trim(),
                        price
                );
                products.add(product);
                position += 124;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Product p : products) {
            if (p.getName().toLowerCase().contains(searchField.getText().toLowerCase())) {
                foundArea.append(p.toCSV()+"\n");
                recordsFound++;

            }
        }
        matchFound.setText("Match Found: " + recordsFound);
    }


}

