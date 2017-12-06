package agile;

import agile.feature.ProgramManager;
import agile.util.DataTable;

import java.util.function.Function;

public enum ExportFile {

    FeaturePercent
        ("FeatPercentInMatrix.csv", ProgramManager::makeFeatPercentInMatrix),
    InOutPercent
        ("InOutPercent.csv", ProgramManager::makeInOutPercentTable),
    TotalSize
        ("TotalSize.csv", ProgramManager::makeTotalSizeTable);

    private String fileName;
    private Function<ProgramManager, DataTable> tableBuilder;

    ExportFile(String fileName, Function<ProgramManager, DataTable> tableBuilder) {
        this.fileName = fileName;
        this.tableBuilder = tableBuilder;
    }

    public String getName() {
        return fileName;
    }

    public DataTable makeTable(ProgramManager manager) {
        return tableBuilder.apply(manager);
    }
}
