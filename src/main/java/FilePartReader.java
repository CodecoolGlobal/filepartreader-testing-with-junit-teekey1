import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilePartReader {
    private String filePath;
    private Integer fromLine;
    private Integer toLine;

    public FilePartReader() {
        setup("testText.txt", 1, 1);
    }

    public void setup(String filePath, Integer fromLine, Integer toLine) {
        if (fromLine < 1 || toLine < fromLine) {
            throw new IllegalArgumentException("wrong arguments");
        }
        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;
    }

    String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public String readLines() throws IOException {
        String[] lines = read().split("\n");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = fromLine; i <= Math.min(toLine, lines.length); i++) {
            stringBuilder.append(lines[i - 1]).append("\n");
        }
        return stringBuilder.toString();
    }

    public String accessGetLines() throws IOException {
        return read();
    }
}
