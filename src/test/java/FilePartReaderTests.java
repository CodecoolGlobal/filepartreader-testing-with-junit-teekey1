import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilePartReaderTests {

    FilePartReader filePartReader;

    @BeforeEach
    void setReader() {
        filePartReader = new FilePartReader();
        filePartReader.setup("src/test/java/testText.txt", 1, 1);
    }

    @Test
    public void should_return_whole_file_text() throws IOException {
        String fileContent = filePartReader.read();
        assertEquals("test line 1\ntesting readline 2 tenet", fileContent);
    }

    @Test
    public void should_return_given_line() throws IOException {
        filePartReader.setup("src/test/java/testText.txt", 2, 2);
        assertEquals("testing readline 2 tenet\n", filePartReader.readLines());
    }

    @Test
    public void should_throw_InvalidArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> filePartReader.setup("", 0, -1));
    }
}
