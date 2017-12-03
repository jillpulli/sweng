package agile.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTable {

    private String emptyCell;
    private Map<String, Integer> headers = new HashMap<>();
    private List<String[]> rows = new ArrayList<>();

    public DataTable(String emptyCell) {
        this.emptyCell = emptyCell;
    }

    public List<String[]> getBody() {
        return rows;
    }

    public String[] getHeaders() {
        String[] headerArray = new String[headers.size()];
        for (String name : headers.keySet())
            headerArray[headers.get(name)] = name;
        return headerArray;
    }

    public DataTable addHeaders(String... headers) {
        if (!rows.isEmpty())
            throw new TableException(
                "Headers may only be added to an empty table.");

        int headerIndex = this.headers.size();

        for (String name : headers)
            this.headers.put(name, headerIndex++);

        return this;
    }

    public DataTable addRow() {
        int numHeaders = headers.size();
        String[] row = new String[numHeaders];

        for (int i = 0; i < numHeaders; i++)
            row[i] = emptyCell;

        rows.add(row);
        return this;
    }

    public DataTable insertCell(String column, Object value) {
        if (!headers.containsKey(column))
            throw new TableException(String.format(
                "Table does not contain column '%s'.", column));

        rows.get(rows.size() - 1)[headers.get(column)] = value.toString();
        return this;
    }

    public DataTable reverseRows() {
        Collections.reverse(rows);
        return this;
    }

    public DataTable sortBy(String column) {
        rows.sort(Comparator.comparing(row -> row[headers.get(column)]));
        return this;
    }

    public DataTable sortByInt(String column) {
        try {
            rows.sort(Comparator.comparingInt(row ->
                Integer.parseInt(row[headers.get(column)])));
            return this;
        }
        catch (NumberFormatException ex) {
            throw new TableException(String.format(
                "Cannot parse Integer for sorting by column '%s'.", column));
        }
    }
}
