package agile.util;

/**
 * Represents a set of column headers for exported data and methods for
 * creating DataTable templates for export.
 */
public enum ExportHeader {

    EmptyCell      ("NA"),
    PriorityScore  ("Priority Score"),
    Program        ("CSL Programs"),
    ProgramKey     ("Program Feature Key"),
    Summary        ("Summary"),
    Total          ("Total");

    private final String value;

    /**
     * Creates an ExportHeader with the specified value.
     *
     * @param value the value to associate with this ExportHeader
     */
    ExportHeader(String value) {
        this.value = value;
    }

    /**
     * Returns an empty DataTable with the following headers:
     * <ul>
     *      <li>ProgramKey</li>
     *      <li>Summary</li>
     *      <li>Program</li>
     *      <li>PriorityScore</li>
     *      <li>Total</li>
     * </ul>
     */
    public static DataTable getFeaturePercentTableBasis() {
        return new DataTable(EmptyCell.value).addHeaders(
            ProgramKey.value,
            Summary.value,
            Program.value,
            PriorityScore.value,
            Total.value
        );
    }

    /**
     * Returns an empty DataTable with the following headers:
     * <ul>
     *      <li>Program</li>
     *      <li>Total</li>
     * </ul>
     */
    public static DataTable getProgramTableBasis() {
        return new DataTable(EmptyCell.value).addHeaders(
            Program.value,
            Total.value
        );
    }

    /**
     * Returns the value associated with this ExportHeader.
     *
     * @return the value associated with this ExportHeader
     */
    @Override
    public String toString() {
        return value;
    }
}
