import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class FileHandler {
    private File file;
    private File stopFile;
    private JFileChooser fileChooser;
    private Map<String, Integer> wordMap;
    private String[] stopWords;
    private List<String> stopWordList;

    public FileHandler() {
        String filePath = System.getProperty("user.dir") + "\\src\\stopWords.txt";
        try {
            stopWordList = Files.readAllLines(Paths.get(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String chooseFile() {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int result = fileChooser.showOpenDialog(null);
        while (result != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "Please select a file");
            result = fileChooser.showOpenDialog(null);
        }
        file = fileChooser.getSelectedFile();
        return fileChooser.getSelectedFile().getName();
    }

    public Map<String,Integer> scanFile() {
        wordMap = new TreeMap<String, Integer>();

        try {
            String line;
            InputStream in = new BufferedInputStream(Files.newInputStream(file.toPath(), StandardOpenOption.CREATE));
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase();
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = word.replaceAll("[^a-zA-Z]", "");
                    if (!isStopWord(word)) {
                        if (wordMap.containsKey(word)) {
                            wordMap.put(word, wordMap.get(word) + 1);
                        } else {
                            wordMap.put(word, 1);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        wordMap.remove("");
        return wordMap;
    }

    public boolean isStopWord(String word) {
        return stopWordList.contains(word);
    }

    public void saveFile() {
        String filePath = System.getProperty("user.dir") + "\\src\\writerTestData.txt";

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(Paths.get(filePath), StandardOpenOption.CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for(Map.Entry<String, Integer> entry : wordMap.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearFile(){
        this.file = null;
    }


}
