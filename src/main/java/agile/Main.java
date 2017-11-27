package agile;

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
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Assume args[0] is a path to the desired file.
        if (args.length == 0) {
            System.out.println("ERROR: No filename given.");
            return;
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println(String.format(
                "ERROR: Cannot access '%s': No such file", args[0]));
            return;
        }

        List<CSVRecord> records = importRecords(file);
        System.out.println(
            String.format("There are %d records.", records.size()));
    }

    public static List<CSVRecord> importRecords(File file) {
        List<CSVRecord> records = Collections.EMPTY_LIST;

        try (CSVParser parser = CSVFormat
                .EXCEL
                .withFirstRecordAsHeader()
                .parse(new BufferedReader(new FileReader(file)))) {
            return parser.getRecords();
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
