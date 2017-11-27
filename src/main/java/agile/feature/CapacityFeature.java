package agile.feature;

/**
 * The CapacityFeature class represents a feature that is directly responsible
 * for being in or out of capacity. The capacity of a feature refers to the work
 * under that feature. A CapacityFeature also has a current size, which is the
 * size of the work being done under that feature.
 */
public abstract class CapacityFeature extends Feature {

    private double currentSize;
    private boolean inCapacity;

    /**
     * CapacityFeature Constructor.
     * Constructs a CapacityFeature with a feature key and current size. The
     * CapacityFeature may be in or out of capacity.
     *
     * @param key this CapacityFeature's unique feature key
     * @param current the size of this CapacityFeature
     * @param inCapacity true if this CapacityFeature's work is in capacity
     */
    public CapacityFeature(String key, double currentSize, boolean inCapacity) {
        super(key);
        this.currentSize = currentSize;
        this.inCapacity = inCapacity;
    }

    @Override
    public double getCurrentSize() {
        return currentSize;
    }

    /**
     * Returns true if the work under this CapacityFeature is in capacity.
     * Returns false otherwise.
     *
     * @return true if this CapacityFeature is in capacity
     */
    public boolean isInCapacity() {
        return inCapacity;
    }
}
