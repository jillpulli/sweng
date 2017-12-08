package agile.feature;

/**
 *  Represents a feature that may be in or out of capacity.
 */
public abstract class CapacityFeature extends Feature {

    private boolean inCapacity;

    /**
     * CapacityFeature Constructor.
     * Constructs a CapacityFeature with a unique key that is either in
     * or out of capacity.
     *
     * @param key this CapacityFeature's unique feature key
     * @param inCapacity whether this CapacityFeature is in capacity
     */
    CapacityFeature(String key, boolean inCapacity) {
        super(key);
        this.inCapacity = inCapacity;
    }

    /**
     * Returns this CapacityFeature's current size if this feature is in
     * capacity and returns zero otherwise.
     *
     * @return current size if in capacity; zero otherwise
     */
    @Override
    public double getInCapacitySize() {
        if (inCapacity)
            return getCurrentSize();
        return 0.0;
    }
}
