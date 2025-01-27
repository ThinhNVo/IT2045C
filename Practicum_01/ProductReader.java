//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Component;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class ProductReader {
    public ProductReader() {
    }

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        String rec = "";
        ArrayList<String> lines = new ArrayList();
        int FIELDS_LENGTH = 5;

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            if (chooser.showOpenDialog((Component)null) == 0) {
                File selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file, StandardOpenOption.CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                int line = 0;

                while(reader.ready()) {
                    rec = reader.readLine();
                    lines.add(rec);
                    ++line;
                    System.out.printf("\nLine %4d %-60s ", line, rec);
                }

                reader.close();
                System.out.println("\n\nData file read!");

                for(String l : lines) {
                    String[] fields = l.split(",");
                    if (fields.length == 4) {
                        String id = fields[0].trim();
                        String Name = fields[1].trim();
                        String Description = fields[2].trim();
                        Double Cost = Double.parseDouble(fields[3].trim());
                        System.out.printf("\n%-8s%-25s%-25s%6f", id, Name, Description, Cost);
                    } else {
                        System.out.println("Found a record that may be corrupt: ");
                        System.out.println(l);
                    }
                }
            } else {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
