package agile.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a spreadsheet consisting of cells arranged by rows and columns.
 * The number of columns must be determined before any data is added to the
 * table.
 * Data is inserted into the table through the use of column header
 * names. The table consists of two parts: the header and body. Any sorting
 * actions performed on the table affect the table's body row positions but
 * leave the header unaffected.
 */
public class DataTable {

    private String emptyCell;
    private Map<String, Integer> headers = new HashMap<>();
    private List<String[]> rows = new ArrayList<>();

    /**
     * DataTable Constructor.
     * Creates an empty DataTable with empty cells containing the specified
     * String value
     * @param emptyCell the value to place in empty data cells
     */
    public DataTable(String emptyCell) {
        this.emptyCell = emptyCell;
    }

    /**
     * Returns the list of DataTable rows. Each row is an array of the same
     * length as the number of columns. Each value in an array is considered
     * a cell in the table.
     *
     * @return the list of rows in this DataTable
     */
    public List<String[]> getBody() {
        return rows;
    }

    /**
     * Returns an array of this DataTable's column headers. The headers are
     * returned in the order in which they were added to the table.
     *
     * @return the array of this DataTable's column headers
     */
    public String[] getHeaders() {
        String[] headerArray = new String[headers.size()];
        for (String name : headers.keySet())
            headerArray[headers.get(name)] = name;
        return headerArray;
    }

    /**
     * Adds the specified headers to this DataTable. The new headers are
     * appended to the existing headers in the order in which they are
     * given. Headers may only be added to a table with no rows in its
     * body.
     *
     * @param headers the column headers to add to this DataTable
     * @return this DataTable
     * @throws TableException if the table body is not empty
     */
    public DataTable addHeaders(String... headers) {
        if (!rows.isEmpty())
            throw new TableException(
                "Headers may only be added to an empty table.");

        int headerIndex = this.headers.size();

        for (String name : headers)
            this.headers.put(name, headerIndex++);

        return this;
    }

    /**
     * Adds a row to this DataTable's body. The row will consist of a number
     * of cells equal to the number of headers. Each cell will contain this
     * DataTable's empty cell value.
     *
     * @return this DataTable
     */
    public DataTable addRow() {
        int numHeaders = headers.size();
        String[] row = new String[numHeaders];

        for (int i = 0; i < numHeaders; i++)
            row[i] = emptyCell;

        rows.add(row);
        return this;
    }

    /**
     * Adds the specified value to the last row in this DataTable's body.
     * The cell in which the value will be placed is determined by the
     * specified column name. The specified value will be converted to a
     * String with a call to the value's <code>toString</code> method.
     *
     * @param column the name of the column in which to place the value
     * @param value the value to be added to the last row of this DataTable
     * @return this DataTable
     * @throws TableException if this DataTable does not contain the specified
     * column name
     */
    public DataTable insertCell(String column, Object value) {
        if (!headers.containsKey(column))
            throw new TableException(String.format(
                "Table does not contain column '%s'.", column));

        rows.get(rows.size() - 1)[headers.get(column)] = value.toString();
        return this;
    }

    /**
     * Reverses the rows of this DataTable's body.
     *
     * @return this DataTable
     */
    public DataTable reverseRows() {
        Collections.reverse(rows);
        return this;
    }

    /**
     * Sorts this DataTable's body rows based on the values in the specified
     * column. The order is determined by the lexicographic order of the values.
     *
     * @param column the name of the column by which to sort
     * @return this DataTable
     */
    public DataTable sortBy(String column) {
        rows.sort(Comparator.comparing(row -> row[headers.get(column)]));
        return this;
    }

    /**
     * Sorts this DataTable's body rows based on the integer values in the
     * specified column
     * @param column the name of the column by which to sort
     * @return this DataTable
     * @throws TableException if there is a problem parsing an integer
     */
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
