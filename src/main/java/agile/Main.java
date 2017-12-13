package agile;

import agile.feature.FeatureFactory;
import agile.feature.ProgramManager;
import agile.util.FeatureRecord;
import agile.util.SimpleLogger;
import agile.util.RecordsIO;
import agile.util.TableException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.File;
import java.lang.Thread;
import java.util.List;

public class Main {

    private static final SimpleLogger LOGGER = new SimpleLogger(System.out);
    private static final Options OPTIONS = new Options()
        .addOption("g", "gui", false, "open the generated user interface")
        .addOption("h", "help", false, "print this message and exit")
        .addOption("i", "input", true, "path to an import file")
        .addOption("o", "output", true, "path to export directory");

    public static void main(String[] args) {
        CommandLine cmd;
        try {
            cmd = new DefaultParser().parse(OPTIONS, args);
        }
        catch (ParseException ex) {
            LOGGER.error("Problem parsing command line arguments.");
            LOGGER.error(ex.getMessage());
            return;
        }

        if (args.length == 0 || cmd.hasOption('h'))
            printHelpAndExit();

        if (cmd.hasOption('g')) {
            //TODO Put the GUI's launch() method here.
            return;
        }

        if (!cmd.hasOption('i')) {
            LOGGER.error("No import file given.");
            return;
        }

        if (!cmd.hasOption('o')) {
            LOGGER.error("No export file given.");
            return;
        }

        File inFile = new File(cmd.getOptionValue('i'));
        if (!inFile.isFile()) {
            LOGGER.error(String.format(
                "Cannot read from '%s': No such file", inFile.getName()));
            return;
        }

        File outFile = new File(cmd.getOptionValue('o'));
        if (!outFile.isDirectory()) {
            LOGGER.error(String.format(
                "Cannot export files to '%s': Not a directory",
                outFile.getName()));
            return;
        }

        List<FeatureRecord> records;
        try {
            LOGGER.info("Reading feature data from " +
                inFile.getAbsolutePath());
            records = RecordsIO.importRecords(inFile);
        }
        catch (TableException ex) {
            LOGGER.error(ex.getMessage());
            return;
        }

        ProgramManager manager = new ProgramManager();
        FeatureFactory.assemblePrograms(manager, records, LOGGER);

        for (ExportFile file : ExportFile.values())
            runExportThread(outFile, file, manager);
    }

    private static void printHelpAndExit() {
        new HelpFormatter().printHelp("java -jar <agile JAR name>", OPTIONS);
        System.exit(-1);
    }

    private static void runExportThread(File directory, ExportFile expFile,
            ProgramManager manager) {
        new Thread(() -> {
            File target = new File(directory, expFile.getName());
            RecordsIO.exportRecords(target, expFile.makeTable(manager));
            LOGGER.info("Exported " + target.getAbsolutePath());
        }).start();
    }

    private Main() { }
}
