package agile.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DelimitedFileParser {

    public static List<Map<String, String>> parse(
            File file, String delimiter) {
        List<Map<String, String>> records = new ArrayList<>();
        List<String> fileData = readLines(file);

        String[] headers = fileData.get(0).split(delimiter);
        int numHeaders = headers.length;

        for (int i = 1, size = fileData.size(); i < size; i++) {
            String[] line = fileData.get(i).split(delimiter, numHeaders);
            Map<String, String> record = new HashMap<>();
            for (int j = 0; j < numHeaders; j++)
                record.put(headers[j], line[j]);
            records.add(record);
        }

        return records;
    }

    private static List<String> readLines(File file) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader =
                new BufferedReader(
                new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null)
                lines.add(line);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return lines;
    }
}
