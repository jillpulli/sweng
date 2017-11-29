package agile.feature;

import java.util.List;

public class DataTable {

    private int numColumns;
    private String[] headers;
    private List<String[]> rows;

    public DataTable(String... headers) {
        this.headers = headers;
        numColumns = headers.length;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void addRow(String[] row) {
        rows.add(row);
    }
}
