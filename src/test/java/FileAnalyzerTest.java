import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileAnalyzerTest {

    @Test
    public void testCountOccurrence() {
        List<String> data = new ArrayList<>();
        data.add("The pageview stats tool is available from any page, in two ways:" +
                " 1) see the toolbox in the sidebar, which shows page information;" +
                " the external link is in the last section of page information; and" +
                " 2) look behind the page's history tab and select page view statistics.");
        data.add("if you enter that subject's name, there is a good chance there will be at least some views in absence of an actual article.");
        String searchString = "in ";
        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        int count = fileAnalyzer.countOccurrence(data, searchString);

        assertEquals(4, count);
    }

    @Test
    public void testFilter() {
        List<String> data = new ArrayList<>();
        data.add("The pageview stats tool is available from any page, in two ways:");
        data.add(" 1) see the toolbox in the sidebar, which shows page information;");
        data.add(" the external link is in the last section of page information; and");
        data.add(" 2) look behind the page's history tab and select page view statistics.");
        data.add("if you enter that subject's name, there is a good chance there will be at least some views in absence of an actual article.");
        String searchString = "in ";
        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        List<String> filteredData = fileAnalyzer.filter(data, searchString);

        assertEquals(4, filteredData.size());
    }

    @Test
    public void testReadSentences() throws IOException {
        String data = "The pageview stats tool is available from any page, in two ways?" +
                " 1) see the toolbox in the sidebar, which shows page information." +
                " The external link is in the last section of page information; and\n" +
                " 2) look behind the page's history tab and select page view statistics!" +
                " If you enter that subject's name, there is a good chance there will be at least some views in absence of an actual article.";
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());
        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        List<String> sentences = fileAnalyzer.readSentences(inputStream);

        assertEquals(4, sentences.size());
    }
}
