package agile;

import agile.feature.FeatureFactory;
import agile.feature.ProgramManager;
import agile.util.FeatureRecord;
import agile.util.SimpleLogger;
import agile.util.RecordsIO;
import agile.util.TableException;

import java.io.File;
import java.lang.Thread;
import java.util.List;


public class Main {

    private static final SimpleLogger LOGGER = new SimpleLogger(System.out);

    /**
     * Imports a CSV file, generates the feature relationships, and
     * exports all spreadsheets to the specified directory.
     *
     * Usage is specified by the following paths:
     * <ul>
     * <li>First argument (<code>args[0]</code>) : path to import file.
     *     Must be a file.</li>
     * <li>Second argument (<code>args[1]</code>) : path to export directory.
     *     Must be a directory.</li>
     * </ul>
     *
     * @param args the import file and export director file paths
     */
    public static void main(String[] args) {


        if (!verifyFiles(args)) return;

        List<FeatureRecord> records;
        try {
            File inFile = new File(args[0]);
            LOGGER.info("Reading feature data from " +
                inFile.getAbsolutePath());
            records = RecordsIO.importRecords(args[0]);
        }
        catch (TableException ex) {
            LOGGER.error(ex.getMessage());
            return;
        }

        ProgramManager manager = new ProgramManager();
        FeatureFactory.assemblePrograms(manager, records, LOGGER);

        for (ExportFile file : ExportFile.values())
            runExportThread(args[1], file, manager);
    }

    private static void runExportThread(String directory, ExportFile file,
            ProgramManager manager) {
        new Thread(() -> {
            String path = directory + file.getName();
            RecordsIO.exportRecords(path, file.makeTable(manager));
            LOGGER.info("Exported " + path);
        }).start();
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

    private Main() { }
}
