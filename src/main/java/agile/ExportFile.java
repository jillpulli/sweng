package agile;

import agile.feature.ProgramManager;
import agile.util.DataTable;

import java.util.function.Function;

/**
 * The ExportFile class defines a set of CSV files that may be created based on
 * feature relationships under a ProgramManager. Each ExportFile has a file name
 * for the potential file that may be created from it as well as the associated
 * ProgamManager method that will produce data for export.
 */
public enum ExportFile {

    /**
     * Represents a Feature Percent In Matrix table. The export file's name
     * will be "FeatPercentInMatrix.csv".
     */
    FeaturePercent
        ("FeatPercentInMatrix.csv", ProgramManager::makeFeatPercentInMatrix),

    /**
     * Represents an In-Out Percent table. The export file's name will be
     * "InOutPercent.csv".
     */
    InOutPercent
        ("InOutPercent.csv", ProgramManager::makeInOutPercentTable),

    /**
     * Represents a Total Current Size table. The export file's name will be
     * "TotalSize.csv".
     */
    TotalSize
        ("TotalSize.csv", ProgramManager::makeTotalSizeTable);

    private String fileName;
    private Function<ProgramManager, DataTable> tableBuilder;

    /**
     * Creates an ExportFile with the specified file name and function that
     * will produce the desired table for export.
     *
     * @param fileName the name of the file to be exported
     * @param tableBuilder the function that will produce a table for export
     */
    ExportFile(String fileName, Function<ProgramManager, DataTable> tableBuilder) {
        this.fileName = fileName;
        this.tableBuilder = tableBuilder;
    }

    /**
     * Returns the file name associated with this ExportFile.
     *
     * @return the file name associated with this ExportFile
     */
    public String getName() {
        return fileName;
    }

    /**
     * Returns a DataTable using this ExportFile's table-building function.
     * The table's data originates from the specified ProgramManager.
     *
     * @param manager the ProgramManager whose data is to be put in the table
     * @return a DataTable based on this ExportFiles's table-building function
     */
    public DataTable makeTable(ProgramManager manager) {
        return tableBuilder.apply(manager);
    }
}
