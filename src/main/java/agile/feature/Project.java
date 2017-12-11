package agile.feature;

/**
 * Represents an AgileObject with current size and in-capacity size values.
 * A Project does not represent any features.
 */
public class Project extends AgileObject {

    private String name;
    private double currentSize;
    private double inCapacitySize;

    /**
     * Project Constructor with name.
     * Creates a Project with the specified name and zero values for the
     * current size and in-capacity size.
     *
     * @param name the name of this Project
     */
    Project(String name) {
        this.name = name;
        this.currentSize = 0.0;
        this.inCapacitySize = 0.0;
    }

    /**
     * Project Constructor with name and size values.
     * Creates a Project with the specified name, current size, and
     * in-capacity size.
     *
     * @param name the name of this Project
     * @param currentSize the current size of this Project
     * @param inCapacitySize the in-capacity size of this Project
     */
    Project(String name, double currentSize, double inCapacitySize) {
        this.name = name;
        this.currentSize = currentSize;
        this.inCapacitySize = inCapacitySize;
    }

    @Override
    public double getCurrentSize() {
        return currentSize;
    }

    @Override
    public double getInCapacitySize() {
        return inCapacitySize;
    }

    /**
     * Returns the name of this Project.
     *
     * @return the name of this Project
     */
    public String getName() {
        return name;
    }

    /**
     * Returns zero since a Project does not represent any features.
     *
     * @return zero
     */
    @Override
    public int getNumberOfFeatures() {
        return 0;
    }

    /**
     * Adds the specified value to this Project's current size.
     *
     * @param currentSize the value to add to this Project's current size
     * @return this Project object
     */
    public Project addToCurrentSize(double currentSize) {
        this.currentSize += currentSize;
        return this;
    }

    /**
     * Adds the specified value to this Project's in-capacity size.
     *
     * @param inCapacitySize the value to add to this Project's in-capacity size
     * @return this Project object
     */
    public Project addToInCapacitySize(double inCapacitySize) {
        this.inCapacitySize += inCapacitySize;
        return this;
    }
}
