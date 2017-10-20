package agile.util;

import agile.model.DataRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DelimitedFileParser {

    public static DataRecord<String, String> parse(File file, String delimiter) {
        DataRecord<String, String> dataRecord = new DataRecord<>();

        try (BufferedReader reader =
                new BufferedReader(
                new FileReader(file))) {
            dataRecord.setHeaders(reader.readLine().split(delimiter));
            int numHeaders = dataRecord.getNumberOfHeaders();

            String line;
            while ((line = reader.readLine()) != null)
                dataRecord.addRecord(line.split(delimiter, numHeaders));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return dataRecord;
    }
}
