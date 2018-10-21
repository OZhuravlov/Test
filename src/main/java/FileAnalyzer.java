import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileAnalyzer {

    private final char[] delims = {'.', '?', '!'};

    public static void main(String[] args) throws IOException {
        if (args.length != 2 || args[0].isEmpty() || args[1].isEmpty()) {
            System.out.println("program arguments are not valid.\nExpected: path_to_file search_string");
            System.exit(-1);
        }
        String fileName = args[0];
        String searchString = args[1];
        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        fileAnalyzer.process(fileName, searchString);
    }

    private void process(String fileName, String searchString) throws IOException {
        File file = new File(fileName);
        if (!(file.exists() && file.isFile())) {
            System.out.println("File " + fileName + " does not exist");
            System.exit(1);
        }

        int occurrenceCount = 0;
        List sentenceList = new ArrayList();
        InputStream fileInputStream = null;
        StringBuilder builder = new StringBuilder();
        try {
            fileInputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(fileInputStream, "UTF-8");
            int c;
            while ((c = reader.read()) != -1) {
                char ch = (char) c;
                builder.append(ch);
                if (isEndOfSentence(ch)) {
                    String sentence = builder.toString();
                    sentence = sentence.replace("\n", " ").replace("\r", " ");
                    if (sentence.contains(searchString)) {
                        occurrenceCount += getOccurrenceCount(sentence, searchString);
                        sentenceList.add(sentence);
                    }
                    builder.setLength(0);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            fileInputStream.close();
        }
        System.out.println("occurrences = " + occurrenceCount);
        for (Object sentence : sentenceList) {
            System.out.println(sentence);
        }
    }

    private int getOccurrenceCount(String sentence, String searchString) {
        int occurrenceCount = 0;
        int lastIndex = 0;
        while (lastIndex != -1) {
            lastIndex = sentence.indexOf(searchString, lastIndex);
            if (lastIndex != -1) {
                occurrenceCount++;
                lastIndex += searchString.length();
            }
        }
        return occurrenceCount;
    }

    private boolean isEndOfSentence(char ch) {
        for (char delim : delims) {
            if (ch == delim) {
                return true;
            }
        }
        return false;
    }
}
