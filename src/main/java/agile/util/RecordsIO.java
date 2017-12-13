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
     * specified by the <code>ImportHeader</code> Enum type. Each record, or
     * line, will be converted to a FeatureRecord. A list of the FeatureRecords
     * is returned.
     *
     * @param file the file to be imported
     * @return a list of the imported FeatureRecords
     * @see ImportHeader
     */
    public static List<FeatureRecord> importRecords(File file) {
        List<FeatureRecord> records = new ArrayList<>();

        try (CSVParser parser = CSVFormat
                .EXCEL
                .withFirstRecordAsHeader()
                .parse(new BufferedReader(new FileReader(file)))) {
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

    /**
     * Exports the specified DataTable to the target file location.
     * The table is written to the file in CSV format.
     *
     * @param target the file to export to
     * @param table the table to export as a CSV
     */
    public static void exportRecords(File target, DataTable table) {
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

    /**
     * Used to check if an imported file has the column headers required for
     * Feature building.
     *
     * @param parser the parser of the file to check
     * @throws TableException if the file does not contain a column header
     * @see ImportHeader
     */
    private static void verifyHeaders(CSVParser parser) {
        Set<String> headers = parser.getHeaderMap().keySet();
        for (ImportHeader ih : ImportHeader.values())
            if (!headers.contains(ih.toString()))
                throw new TableException(
                    "Import file does not contain '" + ih + "' column.");
    }
}
