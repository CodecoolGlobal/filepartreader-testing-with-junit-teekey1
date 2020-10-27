import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileWordAnalyzerTests {

    private FileWordAnalyzer fileWordAnalyzer;

    @Mock
    static private FilePartReader filePartReader;

    @BeforeEach
    public void init() throws IOException {
        filePartReader = mock(FilePartReader.class);
        when(filePartReader.readLines()).thenReturn("test line 1\n" +
                "testing readline 2 tenet");
        fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
    }

    @Test
    public void getWordsOrderedAlphabetically_TestFile_RightOrder() throws IOException {
        List<String> words = new ArrayList<>(Arrays.asList("1", "2", "line", "readline", "tenet", "test", "testing"));
        assertEquals(words, fileWordAnalyzer.getWordsOrderedAlphabetically());
    }

    @Test
    public void getWordsContainingSubstring_TestFile_Test() throws IOException {
        List<String> words = new ArrayList<>(Collections.singletonList("testing"));
        assertEquals(words, fileWordAnalyzer.getWordsContainingSubstring("ing"));
    }

    @Test
    public void getStringsWhichPalindromes_TestFile_Txt() throws IOException {
        List<String> words = new ArrayList<>(Collections.singletonList("tenet"));
        assertEquals(words, fileWordAnalyzer.getStringsWhichPalindromes());
    }
}
