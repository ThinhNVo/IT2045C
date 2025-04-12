import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileHandler {
    private File file;
    private JFileChooser fileChooser;
    private List<String> orgFile;
    private List<String> searchedSentences = new ArrayList<>();

    public String chooseFile() {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int result = fileChooser.showOpenDialog(null);
        while (result != JFileChooser.APPROVE_OPTION && file == null) {
            JOptionPane.showMessageDialog(null, "Please select a file");
            result = fileChooser.showOpenDialog(null);
        }
        file = fileChooser.getSelectedFile();
        return fileChooser.getSelectedFile().getName();
    }

    public List<String> scanFile() {
        try (Stream<String> lines = Files.lines(Paths.get(file.getPath()))) {
            orgFile = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orgFile;
    }

    public List<String> searchFile(String searchWord) {
        searchedSentences.clear();
        try (Stream<String> lines = Files.lines(Paths.get(file.getPath()))) {
            String paragraph = lines.collect(Collectors.joining("\n"));
            searchedSentences = Arrays.stream(paragraph.split("[.!?]"))
                    .map(String::trim)
                    .filter(word -> word.toLowerCase().contains(searchWord.toLowerCase())) // Filter by keyword
                    .collect(Collectors.toList());
            if (searchedSentences.isEmpty()) {JOptionPane.showMessageDialog(null, "No results found for \"" + searchWord + "\".");}
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchedSentences;

    }

    public void clearFile(){
        this.file = null;
    }

    public boolean fileSelected(){
        return file == null;
    }
}
