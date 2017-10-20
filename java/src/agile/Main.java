package agile;

import agile.model.DataRecord;
import agile.util.DelimitedFileParser;
import agile.view.TextIO;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        // Assume args[0] is a relative path to the desired file.
        if (args.length == 0) {
            System.out.println("No filename given.");
            return;
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println(
                "Cannot access '" + args[0] + "': No such file");
            return;
        }

        System.out.println("\nReading data from file: " + file.getName());
        DataRecord<String, String> records =
            DelimitedFileParser.parse(file, "\t");
        String[] headers = records.getHeaders();
        int numRecords = records.size();
        System.out.println("System ready with " + numRecords + " records.");

        TextIO<DataRecord<String, String>> program = new TextIO<>();
        program.run(records, (rec) -> {
            System.out.println();
            int row = program.getIntegerInput(
                "Enter row number (" + 0 + " to " + (numRecords - 1) + ")",
                0, numRecords
            );
            System.out.println("\n-- Columns --");
            program.printUnorderedList(headers);
            String column = program.getInput("Enter a column name");
            System.out.println();
            String value = rec.getDataValue(row, column);
            if (value != null)
                if (value.isEmpty()) System.out.println("<No value>");
                else System.out.println(value);
            else
                System.out.println("\tError: No such column");
            // Only exit on EOF.
            return true;
        });
    }
}
