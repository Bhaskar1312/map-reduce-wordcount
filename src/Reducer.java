import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Reducer {
    public static void main(String[] args) throws IOException {
        String inputFilePath = args[0]; //"output.txt";
        System.out.println(Path.of(inputFilePath).toAbsolutePath());
        List<String> lines = Files.readAllLines(Path.of(inputFilePath));

        Map<String, Integer> map = new HashMap<>();
        for(var line: lines) {
            String[] words = line.split("\t");
            Objects.requireNonNull(words[1]); // assert len to be 2?
            String word = words[0];
            int val = Integer.valueOf(words[1]);
            map.put(word, map.getOrDefault(word, 0)+val);
        }

        String outputFilePath = args[1]; //"reducer.txt";
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(outputFilePath));) {
            for (var entry : map.entrySet()) {
                System.out.println(entry.getKey() + "\t" + entry.getValue());
                writer.write(entry.getKey() + "\t" + entry.getValue());
                writer.newLine();
            }
        }
    }
}