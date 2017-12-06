package agile;

import agile.feature.FeatureFactory;
import agile.feature.ProgramManager;
import agile.util.SimpleLogger;
import agile.util.RecordsIO;

import java.io.File;

public class Main {

    /**
     * Names for exported files.
     */
    private static final String FEAT_PERCENT = "FeatPercentInMatrix.csv";
    private static final String IN_OUT_PERCENT = "InOutPercent.csv";
    private static final String TOTAL_SIZE = "TotalSize.csv";
    private static final SimpleLogger LOGGER = new SimpleLogger(System.out);

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

        ProgramManager manager = new ProgramManager();

        FeatureFactory.assemblePrograms(
            manager, RecordsIO.importRecords(args[0]), LOGGER);

        RecordsIO.exportRecords(args[1] + FEAT_PERCENT,
            manager.getFeatPercentInMatrix());

        RecordsIO.exportRecords(args[1] + TOTAL_SIZE,
            manager.getTotalSizeTable());

        RecordsIO.exportRecords(args[1] + IN_OUT_PERCENT,
            manager.getInOutPercentTable());

        LOGGER.info("Done!");
    }

    private static boolean verifyFiles(String... pathNames) {
        if (pathNames.length == 0) {
            LOGGER.error("No file names given.");
            return false;
        }

        if (pathNames.length < 2) {
            LOGGER.error("Not enough files given.");
            return false;
        }

        File inFile = new File(pathNames[0]);
        if (!inFile.isFile()) {
            LOGGER.error(String.format(
                "Cannot read from '%s': No such file", pathNames[0]));
            return false;
        }

        File outFile = new File(pathNames[1]);
        if (!outFile.isDirectory()) {
            LOGGER.error(String.format(
                "Cannot export files to '%s': Not a directory",
                pathNames[1]));
            return false;
        }

        return true;
    }
}
