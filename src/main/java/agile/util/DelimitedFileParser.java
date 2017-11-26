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

    public static List<Map<String, String>> parse(File file, String delimiter) {
        List<Map<String, String>> records = new ArrayList<>();

        try (BufferedReader reader =
                new BufferedReader(
                new FileReader(file))) {
            String[] headers = reader.readLine().split(delimiter);
            int numHeaders = headers.length;

            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineData = line.split(delimiter, numHeaders);
                Map<String, String> record = new HashMap<>();
                for (int i = 0; i < numHeaders; i++)
                    record.put(headers[i], lineData[i]);
                records.add(record);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return records;
    }
}
