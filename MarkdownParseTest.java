import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.*;

public class MarkdownParseTest {

    private List<List<String>> results;
    private List<String> testFiles;
    private final String baseDirectory = "./test";
    private final String testDirectory = baseDirectory + "/test";
    private final String resultDirectory = baseDirectory + "/result";

    public void init() throws IOException {
        testFiles = new ArrayList<>();
        File[] files = new File(testDirectory).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                testFiles.add(file.getName());
            }
        }

        results = new ArrayList<>();
        for (int i = 0; i < testFiles.size(); i++) {
            String filename = testFiles.get(i);
            String res = Files.readString(Path.of(resultDirectory + '/' + filename.substring(0, filename.lastIndexOf('.'))));
            List<String> result;
            if (res.length() == 0) {
                result = Arrays.asList(); // Or string will be directly returned
            } else {
                result = Arrays.asList(res.trim().split("\n"));
            }
            results.add(result);
        }
    }

    @Test
    public void testGetLinks() {
        try {
            init();
        } catch (Exception e) {
            fail("Failed to initialize test file: " + e.getMessage());
        }

        for (int i = 0; i < testFiles.size(); i++) {
            String currentFile = testFiles.get(i);
            try {
                String markdown = Files.readString(Path.of(testDirectory + '/' + currentFile));
                List<String> ls = MarkdownParse.getLinks(markdown);
                List<String> expected = results.get(i);
                assertEquals("Testing read link from file " + currentFile, ls, expected); // Equals()
            } catch (Exception e) {
                fail("Error reading file " + e.getMessage()); // File reading issue, failed for now
            }
        }
    }
}
