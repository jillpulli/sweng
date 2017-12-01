package agile.util;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTable {

    private List<String> headers;
    private List<Map<String, String>> rows = new ArrayList<>();

    public DataTable(String... headers) {
        this.headers = new ArrayList<String>(Arrays.asList(headers));
    }

    public List<String> getHeaders() {
        return headers;
    }

    public int getNumberOfColumns() {
        return headers.size();
    }

    public int getNumberOfRows() {
        return rows.size();
    }

    public Map<String, String> getRow(int row) {
        return rows.get(row);
    }

    public DataTable addHeaders(String... headers) {
        for (int i = 0, len = headers.length; i < len; i++)
            this.headers.add(headers[i]);
        return this;
    }

    public DataTable addRow() {
        rows.add(new HashMap<String, String>());
        return this;
    }

    public DataTable addRow(Map<String, String> row) {
        rows.add(row);
        return this;
    }

    public DataTable addRow(int index, Map<String, String> row) {
        rows.add(index, row);
        return this;
    }

    protected List<String[]> generateTable() {
        List<String[]> table = new ArrayList<>();
        int numColumns = headers.size();
        for (Map<String, String> row : rows) {
            String[] rowArray = new String[numColumns];
            for (int i = 0; i < numColumns; i++)
                rowArray[i] = row.getOrDefault(headers.get(i), "N/A");
            table.add(rowArray);
        }
        return table;
    }

    public DataTable insertCell(int row, String column, Object value) {
        rows.get(row).put(column, value.toString());
        return this;
    }

    public DataTable insertCell(String column, Object value) {
        return insertCell(rows.size() - 1, column, value);
    }

    public DataTable reverse() {
        Collections.reverse(rows);
        return this;
    }

    public DataTable sort(String sortColumn) {
        rows.sort((a, b) -> a.get(sortColumn).compareTo(b.get(sortColumn)));
        return this;
    }

    public DataTable sortByDouble(String sortColumn) {
        rows.sort((a, b) ->
            Double.compare(
                Double.parseDouble(a.get(sortColumn)),
                Double.parseDouble(b.get(sortColumn))));
        return this;
    }

    public DataTable sortByInt(String sortColumn) {
        rows.sort((a, b) ->
            Integer.compare(
                Integer.parseInt(a.get(sortColumn)),
                Integer.parseInt(b.get(sortColumn))));
        return this;
    }
}
