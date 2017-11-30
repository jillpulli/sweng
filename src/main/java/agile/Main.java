package agile;

import agile.assembly.FeatureFactory;
import agile.feature.ProgramManager;
import agile.util.RecordsIO;

import java.io.File;

public class Main {

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

        RecordsIO.exportRecords(args[1] + "TotalSize.csv",
            manager.getTotalSizeTable().sort("CSL Programs"));
    }

    public static boolean verifyFiles(String[] pathnames) {
        if (pathnames.length == 0) {
            System.out.println("ERROR: No filenames given.");
            return false;
        }

        if (pathnames.length < 2) {
            System.out.println("ERROR: Not enough files given");
            return false;
        }

        File inFile = new File(pathnames[0]);
        if (!inFile.isFile()) {
            System.out.println(String.format(
                "ERROR: Cannot read from '%s': No such file", pathnames[0]));
            return false;
        }

        File outFile = new File(pathnames[1]);
        if (!outFile.isDirectory()) {
            System.out.println(String.format(
                "ERROR: Cannot export files to '%s': Not a directory",
                pathnames[1]));
            return false;
        }

        return true;
    }
}
