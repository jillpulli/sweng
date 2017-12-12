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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Provides a set of methods for importing and exporting CSV files.
 */
public class RecordsIO {

    /**
     * Imports the file specified by the given path. For a successful import,
     * the file must be of CSV format and must contain the column headers
     * specified by the <code>ImportHeaders</code> Enum type.
     *
     * @param pathname the path to the file to be imported
     * @return a
     */
    public static List<FeatureRecord> importRecords(String pathname) {
        List<FeatureRecord> records = new ArrayList<>();

        try (CSVParser parser = CSVFormat
                .EXCEL
                .withFirstRecordAsHeader()
                .parse(new BufferedReader(new FileReader(pathname)))) {
            verifyHeaders(parser);
            for (CSVRecord record : parser) {
                FeatureRecord feat = new FeatureRecord(record.toMap());
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

    private static void verifyHeaders(CSVParser parser) {
        Set<String> headers = parser.getHeaderMap().keySet();
        for (ImportHeader it : ImportHeader.values())
            if (!headers.contains(it.toString()))
                throw new TableException(
                    "Import file does not contain '" + it + "' column.");
    }
}
