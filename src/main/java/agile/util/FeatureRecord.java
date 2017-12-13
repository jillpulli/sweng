package agile.util;

import java.lang.NumberFormatException;
import java.util.Map;

/**
 * Represents a single imported record that returns data for the creation of
 * features.
 */
public class FeatureRecord {

    private Map<String, String> record;

    /**
     * FeatureRecord Constructor.
     * Creates a FeatureRecord containing the given record map. All data will
     * be pulled from this map.
     *
     * @param record the record map from which to extract data
     */
    public FeatureRecord(Map<String, String> record) {
        this.record = record;
    }

    /**
     * Returns the current size value from this FeatureRecord.
     * If there is a problem parsing the number, zero is returned.
     *
     * @return the current size value from this FeatureRecord
     */
    public double getCurrentSize() {
        return readDouble(ImportHeader.CurrentSize);
    }

    /**
     * Returns whether the data in this FeatureRecord is in capacity.
     * Specifically, returns whether the String "InCapacity" is in the
     * data.
     *
     * @return true if data is in capacity; false otherwise
     */
    public boolean getInCapacity() {
        return read(ImportHeader.Capacity).contains("InCapacity");
    }

    /**
     * Returns the key value from this FeatureRecord.
     *
     * @return the key value from this FeatureRecord
     */
    public String getKey() {
        return read(ImportHeader.Key);
    }

    /**
     * Returns the level value from this FeatureRecord.
     *
     * @return the level value from this FeatureRecord
     */
    public String getLevel() {
        return read(ImportHeader.Level);
    }

    /**
     * Returns the priority score value from this FeatureRecord.
     *
     * @return the
     */
    public int getPriorityScore() {
        return readInt(ImportHeader.PriorityScore);
    }

    /**
     * Returns the program name from this FeatureRecord.
     *
     * @return the program name from this FeatureRecord
     */
    public String getProgram() {
        return read(ImportHeader.Program);
    }

    /**
     * Returns the project name from this FeatureRecord.
     *
     * @return the project name from this FeatureRecord
     */
    public String getProject() {
        return read(ImportHeader.Project);
    }

    /**
     * Returns the summary from this FeatureRecord.
     *
     * @return the summary from this FeatureRecord
     */
    public String getSummary() {
        return read(ImportHeader.Summary);
    }

    /**
     * Uses the specified ImportHeader value as a key to retrieve
     * the associated value from this FeatureRecord record map.
     *
     * @param key the data to retrieve
     * @return the associated value
     */
    private String read(ImportHeader key) {
        return record.get(key.toString());
    }

    /**
     * Returns the parsed double from the value associated with the
     * specified key. If there is a problem parsing the number, zero is
     * returned.
     *
     * @param key the data to retrieve
     * @return the parsed number if successful; zero otherwise
     */
    private double readDouble(ImportHeader key) {
        try {
            return Double.parseDouble(read(key));
        }
        catch (NumberFormatException ex) {
            return 0.0;
        }
    }

    /**
     * Returns the parsed integer from the value associated with the
     * specified key. If there is a problem parsing the number, zero is
     * returned.
     *
     * @param key the data to retrieve
     * @return the parsed number if successful; zero otherwise
     */
    private int readInt(ImportHeader key) {
        try {
            return Integer.parseInt(read(key));
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
}
