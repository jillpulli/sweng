package agile.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class RecordsIO {

    public static List<DataRecord> importRecords(File file) {
        List<DataRecord> records = new ArrayList<>();

        try (CSVParser parser = CSVFormat
                .EXCEL
                .withFirstRecordAsHeader()
                .parse(new BufferedReader(new FileReader(file)))) {
            for (CSVRecord record : parser)
                records.add(new DataRecord(record.toMap()));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return records;
    }

    public static void exportRecords(File target, Iterable<?> records,
            String... headers) {
        try (CSVPrinter printer = new CSVPrinter(
                new BufferedWriter(new FileWriter(target)),
                CSVFormat.EXCEL.withHeader(headers))) {
            printer.printRecords(records);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
