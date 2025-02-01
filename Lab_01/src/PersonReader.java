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

public class PersonReader {
    public PersonReader() {
    }

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<Person> persons = new ArrayList<>();
        int FIELDS_LENGTH = 5;

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            if (chooser.showOpenDialog((Component) null) == 0) {
                File selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file, StandardOpenOption.CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                int line = 0;

                while (reader.ready()) {
                    rec = reader.readLine();
                    lines.add(rec);
                    ++line;
                    System.out.printf("\nLine %4d %-60s ", line, rec);
                }

                reader.close();
                System.out.println("\n\nData file read!");

                for (String l : lines) {
                    String[] fields = l.split(",");
                    if (fields.length == FIELDS_LENGTH) {
                        // Extract data from fields
                        String id = fields[0].trim();
                        String firstName = fields[1].trim();
                        String lastName = fields[2].trim();
                        String title = fields[3].trim();
                        int yob = Integer.parseInt(fields[4].trim());

                        // Create a Person object and add to the ArrayList
                        Person personAdded = new Person(id, firstName, lastName, title, yob);
                        persons.add(personAdded);

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

        // Print the data from Person objects in a formatted
        for (Person person : persons) {
            System.out.printf("\n%-8s%-25s%-25s%-6s%6d",
                    person.getID(),
                    person.getFirstName(),
                    person.getLastName(),
                    person.getTitle(),
                    person.getYearOfBirth());
        }
    }
}
