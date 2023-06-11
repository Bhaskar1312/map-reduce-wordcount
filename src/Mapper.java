import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {
    public static void main(String... args) throws IOException {
        String inputFilePath = args[0]; //"input.txt";
        System.out.println(Path.of(inputFilePath).toAbsolutePath());
        List<String> lines = Files.readAllLines(Path.of(inputFilePath));
        Map<String, Integer> map = new HashMap<>();
        for(var line: lines) {
            String[] words = line.split(" ");
            for(String word: words) {
                map.put(word, map.getOrDefault(word, 0)+1);
            }
        }

        String outputFilePath = args[1]; //"output.txt";
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(outputFilePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND);) {
            for (var kv : map.entrySet()) {
                System.out.println(kv.getKey() + "\t" + kv.getValue());
                writer.write(kv.getKey());
                writer.write('\t');
                writer.write(String.valueOf(kv.getValue()));
                writer.newLine();
            }
        }
    }
}
