import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileWordAnalyzer {
    private FilePartReader filePartReader;

    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    private String[] getWords() throws IOException {
        return filePartReader.readLines().replaceAll("\\p{P}", "").split("\\s+");
    }

    public List<String> getWordsOrderedAlphabetically () throws IOException {
        String[] words = getWords();
        Arrays.sort(words, Collator.getInstance());
        return Arrays.asList(words);
    }

    public List<String> getWordsContainingSubstring (String subString) throws IOException {
        List<String> wordsContaining = new ArrayList<>();
        for (String word : getWords()) {
            if (word.contains(subString)) {
                wordsContaining.add(word);
            }
        }
        return wordsContaining;
    }

    public List<String> getStringsWhichPalindromes () throws IOException {
        List<String> palindromes = new ArrayList<>();
        for (String word : getWords()) {
            if (checkIfPalindrome(word)) {
                palindromes.add(word);
            }
        }
        return palindromes;
    }

    private Boolean checkIfPalindrome(String string) {
        if (string.length()==1) {
            return false;
        }
        string = string.toLowerCase();
        char[] letters = string.toCharArray();
        int wordLength = letters.length;
        for (char letter : letters) {
            if (letter != letters[--wordLength]) {
                return false;
            }
        }
        return true;
    }
}
