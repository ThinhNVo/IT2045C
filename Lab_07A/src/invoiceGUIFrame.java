import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.TitledBorder;

public class invoiceGUIFrame extends JFrame {
    private JPanel mainPnl;
    private JPanel inputPnl;
    private JPanel displayPnl; // Center
    private JPanel controlPnl;

    private JTextField orgName;
    private JTextField addressTF;
    private JTextField productTF;
    private JTextField quantityTF;
    private JTextField priceTF;

    private JButton addBtn;
    private JTextArea invoiceArea;
    private JScrollPane scroller;
    private JButton printBtn;
    private JButton clearBtn;
    private JButton quitBtn;

    private List<LineItem> lineItems = new ArrayList<>();


    public invoiceGUIFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        setTitle("Invoice Generator");

        // Set up UI
        createDisplayPanel();
        createInputPanel();
        createControlPanel();

        mainPnl.add(inputPnl, BorderLayout.NORTH);
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

    public void createInputPanel() {
        inputPnl = new JPanel(new BorderLayout());

        //user adress input box
        JPanel userBox = new JPanel(new GridLayout(2, 2, 5, 5));
        userBox.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Enter your address",
                TitledBorder.LEADING,
                TitledBorder.TOP
        ));

        orgName = new JTextField(10);
        userBox.add(new JLabel("Organization Name:"));
        userBox.add(orgName);

        addressTF = new JTextField(10);
        userBox.add(new JLabel("Organization Address:"));
        userBox.add(addressTF);

        //product input box
        JPanel inputBox = new JPanel(new GridLayout(3, 2, 5, 5));
        inputBox.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Enter your product details",
                TitledBorder.LEADING,
                TitledBorder.TOP
        ));

        productTF = new JTextField(10);
        inputBox.add(new JLabel("Product name:"));
        inputBox.add(productTF);

        quantityTF = new JTextField(10);
        inputBox.add(new JLabel("Quantity:"));
        inputBox.add(quantityTF);

        priceTF = new JTextField(10);
        inputBox.add(new JLabel("Price:"));
        inputBox.add(priceTF);

        // add button for product
        JPanel btnBox = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnBox.setPreferredSize(new Dimension(10, 50));
        addBtn = new JButton("Add record");
        invoiceArea.append("Item Checklist:\n");
        addBtn.addActionListener((ActionEvent ae) ->
            {
                String productName = productTF.getText();
                double price = Double.parseDouble(priceTF.getText());
                int quantity = Integer.parseInt(quantityTF.getText());

                Product product = new Product(productName, price);
                LineItem item = new LineItem(product, quantity);
                lineItems.add(item);
                invoiceArea.append(item.toString() + "\n");
            }
        );

        btnBox.add(addBtn);
        inputPnl.add(userBox, BorderLayout.NORTH);
        inputPnl.add(inputBox, BorderLayout.CENTER);
        inputPnl.add(btnBox, BorderLayout.SOUTH);
    }

    public void createDisplayPanel() {
        displayPnl = new JPanel();
        invoiceArea = new JTextArea(18,30);
        invoiceArea.setEditable(false);
        scroller = new JScrollPane(invoiceArea);
        displayPnl.add(scroller, BorderLayout.CENTER);
    }

    public void createControlPanel() {
        controlPnl = new JPanel(new GridLayout(1, 3, 50, 10));

        printBtn = new JButton("Print");
        printBtn.addActionListener((ActionEvent ae) ->
                {
                    double totalPrice = 0;
                    invoiceArea.setText("");
                    invoiceArea.append("Invoice\n");
                    invoiceArea.append(orgName.getText() + "\n");
                    invoiceArea.append(addressTF.getText() + "\n");
                    invoiceArea.append("=======================================\n");
                    for (LineItem item : lineItems) {
                        invoiceArea.append(item.toString() + "\n");
                        totalPrice += item.getItemTotal();
                    }
                    invoiceArea.append("=======================================\n");
                    invoiceArea.append("Total Price: " + totalPrice + "\n");

                }
        );

        clearBtn = new JButton("Clear invoice");
        clearBtn.addActionListener(((ActionEvent ae) -> invoiceArea.setText("")));

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(controlPnl, "Are you sure you want to quit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        controlPnl.add(printBtn);
        controlPnl.add(clearBtn);
        controlPnl.add(quitBtn);
    }
}
