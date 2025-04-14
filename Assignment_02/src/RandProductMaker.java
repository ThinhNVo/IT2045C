import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;


public class RandProductMaker extends JFrame {
    private JPanel mainPnl;
    private JPanel selectPnl;// Top
    private JPanel controlPnl; // Bottom

    private TitledBorder fileBorder;
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField IDField;
    private JTextField costField;
    private JLabel recordLabel;

    private JButton saveBtn;
    private JButton quitBtn;

    private Product product;
    private int position = 0;
    private int record = 0;

    public RandProductMaker() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());// Set up UI
        createSelectPanel();
        createControlPanel();

        setTitle("Personal Tags Extractor");
        mainPnl.add(selectPnl, BorderLayout.CENTER);
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

        nameField = new JTextField();
        descriptionField = new JTextField();
        IDField = new JTextField();
        costField = new JTextField();

        recordLabel = new JLabel( "Records Entered: 0");
        recordLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));

        fieldsPanel.add(new JLabel("Product Name:"));
        fieldsPanel.add(nameField);
        fieldsPanel.add(new JLabel("Product Description:"));
        fieldsPanel.add(descriptionField);
        fieldsPanel.add(new JLabel("Product ID:"));
        fieldsPanel.add(IDField);
        fieldsPanel.add(new JLabel("Product Cost:"));
        fieldsPanel.add(costField);

        fileBorder = new TitledBorder("Enter product data");
        fieldsPanel.setBorder(fileBorder);

        selectPnl.add(fieldsPanel, BorderLayout.CENTER);
        selectPnl.add(recordLabel, BorderLayout.SOUTH);
    }


    private void createControlPanel() {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 2, 50, 20));
        String filePath = System.getProperty("user.dir") + "\\src\\randFiles.txt";

        saveBtn = new JButton("Save");
        saveBtn.addActionListener((ActionEvent ae) -> {
            if (!inputValid()) {
                return;
            }
            product = new Product(nameField.getText(), descriptionField.getText(), IDField.getText(), Double.parseDouble(costField.getText()));
            writeToFile(filePath, position);
            record++;
            recordLabel.setText("Records Entered: " + record);
            position = record*124;
            nameField.setText("");descriptionField.setText("");IDField.setText("");costField.setText("");
        });

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });

        controlPnl.add(saveBtn);
        controlPnl.add(quitBtn);
    }

    private boolean inputValid() {
        if (nameField.getText().isEmpty() || descriptionField.getText().isEmpty() || IDField.getText().isEmpty() || costField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter product name/description/id/cost");
            return false;
        } else if (nameField.getText().length() > 35 || descriptionField.getText().length() > 75 || IDField.getText().length() > 6) {
            JOptionPane.showMessageDialog(null, "Please enter the correct length");
            return false;
        }

        if (!nameField.getText().matches("[a-zA-Z0-9.' ]+")
                || !descriptionField.getText().matches("[a-zA-Z0-9.' ]+"))
        {
            JOptionPane.showMessageDialog(null, "Please enter alphabetical or numerical values for name, ID, and description");
            return false;
        } else if (!IDField.getText().matches("[0-9]+") || !costField.getText().matches("[0-9. ]+")) {
            JOptionPane.showMessageDialog(null, "Please enter numeric values for product ID and cost");
            return false;
        }
        return true;
    }

    private void writeToFile(String filePath, int position) {
        try(RandomAccessFile file = new RandomAccessFile(filePath, "rw");) {
            file.seek(position);
            file.write(String.format("%35s",product.getName()).getBytes(StandardCharsets.UTF_8));
            file.write(String.format("%75s",product.getDescription()).getBytes(StandardCharsets.UTF_8));
            file.write(String.format("%6s",product.getID()).getBytes(StandardCharsets.UTF_8));
            file.writeDouble(product.getCost());
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
