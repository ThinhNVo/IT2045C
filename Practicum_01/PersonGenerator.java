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

public class PersonGenerator {
    public PersonGenerator() {
    }

    public static void main(String[] args) {
        ArrayList<String> people = new ArrayList();
        Scanner in = new Scanner(System.in);
        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        String rec = "";
        int YearOfBirth = 0;
        boolean doneInput = false;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter your ID [000001]");
            firstName = SafeInput.getNonZeroLenString(in, "Enter your first name ");
            lastName = SafeInput.getNonZeroLenString(in, "Enter your last name ");
            title = SafeInput.getNonZeroLenString(in, "Enter your title ");
            YearOfBirth = SafeInput.getRangedInt(in, "Enter your YOB ", 1000, 9999);
            rec = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YearOfBirth;
            people.add(rec);
            doneInput = SafeInput.getYNConfirm(in, "Are you done? [Y/N]");
        } while(!doneInput);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, StandardOpenOption.CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for(String person : people) {
                writer.write(person, 0, person.length());
                writer.newLine();
            }

            writer.close();
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
