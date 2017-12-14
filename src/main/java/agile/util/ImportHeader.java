package agile.util;

/**
 * The ImportHeader class defines a set of column headers that may be present
 * in an imported data set.
 */
public enum ImportHeader {

    Capacity       ("Fix Version/s"),
    CurrentSize    ("Current Size"),
    Key            ("Key"),
    Level          ("Level"),
    PriorityScore  ("Priority Score"),
    Program        ("CSL Programs"),
    Project        ("Project"),
    Summary        ("Summary");

    private final String value;

    /**
     * Creates an ImportHeader with the specified value.
     *
     * @param value the value to associate with this ImportHeader
     */
    ImportHeader(String value) {
        this.value = value;
    }

    /**
     * Returns the value associated with this ImportHeader.
     */
    @Override
    public String toString() {
        return value;
    }
}
