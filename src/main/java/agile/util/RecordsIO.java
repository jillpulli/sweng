package agile.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class RecordsIO {

    public static List<DataRecord> importRecords(String pathname) {
        List<DataRecord> records = new ArrayList<>();

        try (CSVParser parser = CSVFormat
                .EXCEL
                .withFirstRecordAsHeader()
                .parse(new BufferedReader(new FileReader(pathname)))) {
            for (CSVRecord record : parser) {
                DataRecord feat = new DataRecord(record.toMap());
                if (!feat.getKey().isEmpty())
                    records.add(feat);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return records;
    }

    public static void exportRecords(String target, DataTable table) {
        try (CSVPrinter printer = new CSVPrinter(
                new BufferedWriter(
                    new FileWriter(target)),
                CSVFormat.EXCEL.withHeader(
                    table.getHeaders()))) {
            printer.printRecords(table.getBody());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
