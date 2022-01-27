import static org.junit.Assert.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Arrays;

import org.junit.*;

public class MarkdownParseTest {
    @Test
    public void testGetLinks() {
        try {
            String markdown = Files.readString(Path.of("./test-file.md"));
            List<String> ls = MarkdownParse.getLinks(markdown);
            List<String> expected = Arrays.asList("https://something.com", "some-page.html");
            assertEquals("Testing read link from file x", ls, expected);
        } catch (Exception e) {
            fail();
        }
    }
}