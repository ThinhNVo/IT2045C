import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ShortLister {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        String rec = "";
        ArrayList<String> lines = new ArrayList<>();
        ShortWordFilter filter = new ShortWordFilter();

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
                    String[] fields = l.split(" ");
                        for (String f : fields) {
                            System.out.println(f + " is under 5 characters: " + filter.accept(f));
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