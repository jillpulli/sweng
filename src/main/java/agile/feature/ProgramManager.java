package agile.feature;

import java.util.ArrayList;
import java.util.List;

public class ProgramManager extends AgileAggregator<String, ProgramFeature> {

    public List<String[]> getTotalSizeTable() {
        List<String[]> table = new ArrayList<>();
        for (String program : keySet()) {
            String[] row = {
                program,
                Double.toString(get(program).getCurrentSize())
            };
            table.add(row);
        }
        return table;
    }
}
