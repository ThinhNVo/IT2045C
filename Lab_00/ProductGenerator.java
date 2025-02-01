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
        ArrayList<String> people = new ArrayList();
        Scanner in = new Scanner(System.in);
        String ID = "";
        String Name = "";
        String Description = "";
        double Cost = 0;
        String rec = "";
        boolean doneInput = false;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter product ID [000001]");
            Name = SafeInput.getNonZeroLenString(in, "Enter product Name ");
            Description = SafeInput.getNonZeroLenString(in, "Enter product Description ");
            Cost = SafeInput.getRangedDouble(in, "Enter product Cost ", 0,9999);
            rec = ID + ", " + Name + ", " + Description + ", " + Cost;
            people.add(rec);
            doneInput = SafeInput.getYNConfirm(in, "Are you done? [Y/N]");
        } while(!doneInput);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, StandardOpenOption.CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for(String Product : people) {
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
