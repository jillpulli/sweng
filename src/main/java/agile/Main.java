package agile;

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
import java.io.Reader;
import java.util.Collections;
import java.util.List;

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
