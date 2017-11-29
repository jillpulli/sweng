package agile;

import agile.assembly.FeatureFactory;
import agile.feature.ProgramManager;
import agile.util.RecordsIO;

import java.io.File;

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

        ProgramManager manager = FeatureFactory.assemblePrograms(
            RecordsIO.importRecords(file));

        RecordsIO.exportRecords(
            new File("/Users/genya/Desktop/TotalSize.csv"),
            manager.getTotalSizeTable(),
            "CSL Programs", "Overall"
        );
    }
}
