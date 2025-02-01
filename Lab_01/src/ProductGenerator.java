//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductGenerator {
    public ProductGenerator() {
    }

    public static void main(String[] args) {
        ArrayList<String> product = new ArrayList();
        Scanner in = new Scanner(System.in);
        SafeInputOBJ SafeInput = new SafeInputOBJ(in);
        String ID = "";
        String Name = "";
        String Description = "";
        double Cost = 0;
        String rec = "";
        boolean doneInput = false;

        do {
            ID = SafeInput.getNonZeroLenString("Enter product ID [000001]");
            Name = SafeInput.getNonZeroLenString("Enter product Name ");
            Description = SafeInput.getNonZeroLenString("Enter product Description ");
            Cost = SafeInput.getRangedDouble("Enter product Cost ", 0,9999);
            Product productAdded = new Product(ID, Name, Description, Cost);
            product.add(productAdded.toCSV());
            doneInput = SafeInput.getYNConfirm("Are you done? [Y/N]");
        } while(!doneInput);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, StandardOpenOption.CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for(String Product : product) {
                writer.write(Product, 0, Product.length());
                writer.newLine();
            }

            writer.close();
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
