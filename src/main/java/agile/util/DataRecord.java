package agile.util;

import java.lang.NumberFormatException;
import java.util.Map;

public class DataRecord {

    private Map<String, String> record;

    public DataRecord(Map<String, String> record) {
        this.record = record;
    }

    public double getCurrentSize() {
        return readDouble(record.get("Current Size"));
    }

    public boolean getInCapacity() {
        return record.get("Fix Version/s").contains("InCapacity");
    }

    public String getKey() {
        return record.get("Key");
    }

    public int getLevel() {
        return readInt(record.get("Level"));
    }

    public int getPriorityScore() {
        return readInt(record.get("Priority Score"));
    }

    public String getProgram() {
        return record.get("CSL Programs");
    }

    public String getProject() {
        return record.get("Project");
    }

    public String getSummary() {
        return record.get("Summary");
    }

    private double readDouble(String string) {
        try {
            return Double.parseDouble(string);
        }
        catch (NumberFormatException ex) {
            return 0.0;
        }
    }

    private int readInt(String string) {
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
}
