package agile;

import agile.model.DataRecord;
import agile.util.DelimitedFileParser;

import java.io.File;
import java.util.List;
import java.util.Map;

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

        System.out.println("Reading data from file: " + file.getName());

        DataRecord<String, String> records =
            DelimitedFileParser.parse(file, "\t");

        System.out.println("Number of records: " + records.size());
    }
}
