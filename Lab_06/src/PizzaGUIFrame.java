import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class PizzaGUIFrame extends JFrame {
    private JPanel mainPnl;
    private JPanel selectPnl;// Top
    private JPanel displayPnl; // Center
    private JPanel controlPnl;// Bottom

    private JRadioButton thin;
    private JRadioButton regular;
    private JRadioButton deepdish;
    private JComboBox<String> sizeType;
    private String[] sizeP;
    private JCheckBox top1, top2, top3, top4,top5, top6;

    private TitledBorder CrustBorder;
    private TitledBorder SizeBorder;
    private TitledBorder ToppingBorder;
    private JScrollPane scroller;

    private JTextArea orderB;
    private JButton orderaddBtn;
    private JButton clearBtn;
    private JButton quitBtn;

    public PizzaGUIFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        // Set up UI
        createSelectPanel();
        createDisplayPanel();
        createControlPanel();

        setTitle("Five Nights at Freddys");
        mainPnl.setLayout(new BorderLayout());
        mainPnl.add(selectPnl, BorderLayout.NORTH);
        mainPnl.add(displayPnl, BorderLayout.CENTER);
        mainPnl.add(controlPnl, BorderLayout.SOUTH);

        add(mainPnl);
        orderLogic();

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

        // for titled border
        JPanel crust = new JPanel(); JPanel size = new JPanel(); JPanel topping = new JPanel();

        //Crust
        ButtonGroup group = new ButtonGroup();
        thin = new JRadioButton("Thin");
        regular = new JRadioButton("Regular");
        deepdish = new JRadioButton("Deepdish");
        regular.setSelected(true);
        group.add(thin);
        group.add(regular);
        group.add(deepdish);

        //Size
        sizeP = new String[]{"Small/$8.00    ", "Medium/$12.00", "Large/$16.00   ", "Super/$20.00   "};
        sizeType = new JComboBox<>(sizeP);

        //Toppings
        top1 = new JCheckBox("BlueCheese/$1.00 ");
        top2 = new JCheckBox("Pepperoni/$1.00     ");
        top3 = new JCheckBox("HoneyDrizzle/$1.00");
        top4 = new JCheckBox("Olives/$1.00            ");
        top5 = new JCheckBox("Sausages/$1.00     ");
        top6 = new JCheckBox("Ground Beef/$1.00 ");

        //tittled border for all of the elements
        CrustBorder = BorderFactory.createTitledBorder("Crust type:");
        SizeBorder = BorderFactory.createTitledBorder("Size type:");
        ToppingBorder = BorderFactory.createTitledBorder("Topping:");

        crust.setBorder(CrustBorder);
        crust.add(thin); crust.add(regular); crust.add(deepdish);
        size.setBorder(SizeBorder);
        size.add(sizeType);
        topping.setBorder(ToppingBorder);
        topping.add(top1); topping.add(top2); topping.add(top3); topping.add(top4); topping.add(top5); topping.add(top6);

        selectPnl.add(crust, BorderLayout.NORTH);
        selectPnl.add(size, BorderLayout.CENTER);
        selectPnl.add(topping, BorderLayout.SOUTH);
    }

    public void createDisplayPanel() {
        displayPnl = new JPanel();
        orderB = new JTextArea(21,45);
        orderB.setEditable(false);
        scroller = new JScrollPane(orderB);
        displayPnl.add(scroller);
    }

    public void createControlPanel() {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 3, 50, 20));

        orderaddBtn = new JButton("Confirm Order");
        clearBtn = new JButton("Clear Order");
        clearBtn.addActionListener(((ActionEvent ae) -> orderB.setText("")));
        quitBtn = new JButton("Quit!");
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        controlPnl.add(orderaddBtn);
        controlPnl.add(clearBtn);
        controlPnl.add(quitBtn);
    }

    public void orderLogic() {
        orderaddBtn.addActionListener(((ActionEvent ae) -> {
            String size; double price = 0; String crust; double crustP;  ArrayList<String> toppings = new ArrayList<>(); DecimalFormat df = new DecimalFormat("0.00");

            if (thin.isSelected()) { crust = thin.getText();}
            else if (regular.isSelected()) { crust = regular.getText();}
            else { crust = deepdish.getText();}

            size = sizeP[sizeType.getSelectedIndex()];
            if (sizeType.getSelectedIndex() == 0) {crustP = 8; price = crustP;}
            else if (sizeType.getSelectedIndex() == 1) {crustP = 12;price = crustP;}
            else if (sizeType.getSelectedIndex() == 2) {crustP = 16;price = crustP;}
            else {crustP = 20;price = crustP;}

            if (top1.isSelected()) {price++; toppings.add(top1.getText());}
            if (top2.isSelected()) {price++; toppings.add(top2.getText());}
            if (top3.isSelected()) {price++; toppings.add(top3.getText());}
            if (top4.isSelected()) {price++; toppings.add(top4.getText());}
            if (top5.isSelected()) {price++; toppings.add(top5.getText());}
            if (top6.isSelected()) {price++; toppings.add(top6.getText());}

            orderB.append("==============================================================\n");
            orderB.append(crust + " crust & " + size + " size                                      $ " + crustP + "\n");
            for (String topping : toppings) {
                orderB.append(topping + "                                                                  $ 1.0\n");
            }

            orderB.append("\n" + "\n" + "\n" +"Sub-Total:                                                                                 $ " + price + "\n");
            orderB.append("Tax:                                                                                          $ " + df.format(price * 0.07) + "\n");
            orderB.append("---------------------------------------------------------------------------------------------------------------\n");
            orderB.append("Total:                                                                                       $ " + (price += price * 0.07) + "\n");
            orderB.append("==============================================================\n");
        }));
    }

}
