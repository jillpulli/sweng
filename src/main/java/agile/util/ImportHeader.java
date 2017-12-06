package agile.util;

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

    ImportHeader(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
