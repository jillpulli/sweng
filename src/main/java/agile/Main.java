package agile;

import agile.feature.FeatureFactory;
import agile.feature.ProgramManager;
import agile.util.FeatureRecord;
import agile.util.Printable;
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

    private static final SimpleLogger LOGGER = new SimpleLogger(text ->
        System.out.println(text));
    private static final Options OPTIONS = new Options()
        .addOption("h", "help", false, "print this message and exit")
        .addOption("i", "input", true, "path to an import file")
        .addOption("o", "output", true, "path to export directory");

    public static void main(String[] args) {
        CommandLine cmd;
        try {
            cmd = new DefaultParser().parse(OPTIONS, args);
        }
        catch (ParseException ex) {
            LOGGER.error("Problem parsing command line arguments");
            LOGGER.error(ex.getMessage());
            return;
        }

        if (args.length == 0 || cmd.hasOption('h'))
            printHelpAndExit();

        if (!cmd.hasOption('i')) {
            LOGGER.error("No import file given");
            return;
        }

        if (!cmd.hasOption('o')) {
            LOGGER.error("No export directory given");
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
        ProgramManager manager = new ProgramManager();
        try {
            LOGGER.info("Reading feature data from " +
                inFile.getAbsolutePath());
            records = RecordsIO.importRecords(inFile);
            FeatureFactory.assemblePrograms(manager, records);
        }
        catch (TableException ex) {
            LOGGER.error(ex.getMessage());
            return;
        }

        LOGGER.info(String.format(
            "Created %d features from %d records",
            manager.getNumberOfFeatures(), records.size()));

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
