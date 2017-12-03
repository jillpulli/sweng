package agile;

import agile.feature.FeatureFactory;
import agile.feature.ProgramManager;
import agile.util.RecordsIO;

import java.io.File;

public class Main {

    /**
     * Names for exported files.
     */
    private static final String FEAT_PERCENT = "FeatPercentInMatrix.csv";
    private static final String IN_OUT_PERCENT = "InOutPercent.csv";
    private static final String TOTAL_SIZE = "TotalSize.csv";

    /**
     * Imports a CSV file, generates the feature relationships, and
     * exports all spreadsheets to the specified directory.
     *
     * Usage is specified by the following paths:
     *     First argument (args[0]) : path to import file. Must be a file.
     *     Second argument (args[1]) : path to export directory.
     *         Must be a directory.
     */
    public static void main(String[] args) {
        if (!verifyFiles(args)) return;

        ProgramManager manager = FeatureFactory.assemblePrograms(
            RecordsIO.importRecords(args[0]));

        System.out.println(manager.getNumberOfFeatures() + " features found.");

        RecordsIO.exportRecords(args[1] + FEAT_PERCENT,
            manager.getFeatPercentInMatrix());

        RecordsIO.exportRecords(args[1] + TOTAL_SIZE,
            manager.getTotalSizeTable());

        RecordsIO.exportRecords(args[1] + IN_OUT_PERCENT,
            manager.getInOutPercentTable());

        System.out.println("Done!");
    }

    private static boolean verifyFiles(String... pathNames) {
        if (pathNames.length == 0) {
            System.out.println("ERROR: No file names given.");
            return false;
        }

        if (pathNames.length < 2) {
            System.out.println("ERROR: Not enough files given");
            return false;
        }

        File inFile = new File(pathNames[0]);
        if (!inFile.isFile()) {
            System.out.println(String.format(
                "ERROR: Cannot read from '%s': No such file", pathNames[0]));
            return false;
        }

        File outFile = new File(pathNames[1]);
        if (!outFile.isDirectory()) {
            System.out.println(String.format(
                "ERROR: Cannot export files to '%s': Not a directory",
                pathNames[1]));
            return false;
        }

        return true;
    }
}
