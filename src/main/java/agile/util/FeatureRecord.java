package agile.util;

import java.lang.NumberFormatException;
import java.util.Map;

public class FeatureRecord {

    private Map<String, String> record;

    public FeatureRecord(Map<String, String> record) {
        this.record = record;
    }

    public double getCurrentSize() {
        return readDouble(ImportHeader.CurrentSize);
    }

    public boolean getInCapacity() {
        return read(ImportHeader.Capacity).contains("InCapacity");
    }

    public String getKey() {
        return read(ImportHeader.Key);
    }

    public String getLevel() {
        return read(ImportHeader.Level);
    }

    public int getPriorityScore() {
        return readInt(ImportHeader.PriorityScore);
    }

    public String getProgram() {
        return read(ImportHeader.Program);
    }

    public String getProject() {
        return read(ImportHeader.Project);
    }

    public String getSummary() {
        return read(ImportHeader.Summary);
    }

    private String read(ImportHeader value) {
        return record.get(value.toString());
    }

    private double readDouble(ImportHeader value) {
        try {
            return Double.parseDouble(read(value));
        }
        catch (NumberFormatException ex) {
            return 0.0;
        }
    }

    private int readInt(ImportHeader value) {
        try {
            return Integer.parseInt(read(value));
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
}
