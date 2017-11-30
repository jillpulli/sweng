package agile.feature;

import agile.util.DataTable;

import java.util.ArrayList;
import java.util.List;

public class ProgramManager extends AgileAggregator<String, ProgramFeature> {

    public DataTable getTotalSizeTable() {
        DataTable table = new DataTable("CSL Programs", "Overall");
        for (String program : keySet())
            table
                .addRow()
                .insertCell("CSL Programs", program)
                .insertCell("Overall", get(program).getCurrentSize());
        return table;
    }
}
