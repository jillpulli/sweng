package agile.util;

public enum ExportTable {

    EmptyCell      (""),
    PriorityScore  ("Priority Score"),
    Program        ("CSL Programs"),
    ProgramKey     ("Program Feature Key"),
    Summary        ("Summary"),
    Total          ("Total");

    private final String value;

    ExportTable(String value) {
        this.value = value;
    }

    public static DataTable getFeaturePercentTableBasis() {
        return new DataTable(EmptyCell.value).addHeaders(
            ProgramKey.value,
            Summary.value,
            Program.value,
            PriorityScore.value,
            Total.value
        );
    }

    public static DataTable getProgramTableBasis() {
        return new DataTable(EmptyCell.value).addHeaders(
            Program.value,
            Total.value
        );
    }

    @Override
    public String toString() {
        return value;
    }
}
