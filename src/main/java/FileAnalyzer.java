import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileAnalyzer {

    public static void main(String[] args) throws IOException {
        if (args.length != 2 || args[0].isEmpty() || args[1].isEmpty()) {
            System.out.println("program arguments are not valid.\nExpected: path_to_file search_string");
            System.exit(-1);
        }
        String fileName = args[0];
        String searchString = args[1];
        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        List<String> sentences = fileAnalyzer.readSentences(fileName);

        List<String> filteredSentences = fileAnalyzer.filter(sentences, searchString);
        int countOccurance = fileAnalyzer.countOccurrence(filteredSentences, searchString);

        System.out.println("occurrences = " + countOccurance);
        for (Object sentence : filteredSentences) {
            System.out.println(sentence);
        }

    }

    List<String> readSentences(String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        File file = new File(fileName);
        if (!(file.exists() && file.isFile())) {
            System.out.println("File " + fileName + " does not exist");
            System.exit(1);
        }
        return readSentences(fileInputStream);
    }

    List<String> readSentences(InputStream inputStream) throws IOException {
        List<String> sentences = new ArrayList<>();
        String delimsRegexp = "(\\.|!|\\?)\\s*";
        try {
            byte[] contents = new byte[1024];
            int count;
            while((count = inputStream.read(contents)) != -1) {
                String data = new String(contents, 0, count).replaceAll("\n", " ").replaceAll("\r", " ");
                String[] splittedData = data.split(delimsRegexp);
                for (String sentence : splittedData) {
                    if(!sentence.isEmpty()) {
                        sentences.add(sentence);
                    }
                }
            }
            return sentences;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            inputStream.close();
        }
    }

    List<String> filter(List<String> sentences, String searchString) {
        List<String> filteredSentences = new ArrayList<>();
        for (String sentence: sentences) {
            if(sentence.contains(searchString)){
                filteredSentences.add(sentence);
            }
        }
        return filteredSentences;
    }

    int countOccurrence(List<String> sentences, String searchString) {
        int occurrenceCount = 0;
        for (String sentence : sentences) {
            int lastIndex = 0;
            while (lastIndex != -1) {
                lastIndex = sentence.indexOf(searchString, lastIndex);
                if (lastIndex != -1) {
                    occurrenceCount++;
                    lastIndex += searchString.length();
                }
            }
        }
        return occurrenceCount;
    }

}