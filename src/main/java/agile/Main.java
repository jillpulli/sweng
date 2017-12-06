package agile;

import agile.feature.FeatureFactory;
import agile.feature.ProgramManager;
import agile.util.FeatureRecord;
import agile.util.SimpleLogger;
import agile.util.RecordsIO;
import agile.util.TableException;

import java.io.File;
import java.util.List;

public class Main {

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

        List<FeatureRecord> records;
        try {
            records = RecordsIO.importRecords(args[0]);
        }
        catch (TableException ex) {
            LOGGER.error(ex.getMessage());
            return;
        }

        ProgramManager manager = new ProgramManager();

        FeatureFactory.assemblePrograms(manager, records, LOGGER);

        LOGGER.info("Exporting the following files to " + args[1] + ':');
        for (ExportFile file : ExportFile.values()) {
            String name = file.getName();
            RecordsIO.exportRecords(args[1] + name, file.makeTable(manager));
            LOGGER.log("\t" + name);
        }
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
