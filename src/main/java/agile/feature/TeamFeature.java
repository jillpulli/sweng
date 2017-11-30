package agile.feature;

/**
 * The TeamFeature class represents the final layer of decomposition of features.
 * Every instance of this class is only responsible for itself. It holds no
 * additional features.
 */
public class TeamFeature extends Feature {

    private double currentSize;
    private boolean inCapacity;

    /**
     * TeamFeature Constructor.
     * Constructs a TeamFeature with a feature key and current size. The
     * TeamFeature may be in or out of capacity.
     *
     * @param key this TeamFeature's unique feature key
     * @param currentSize the size of this TeamFeature
     * @param inCapacity true if this TeamFeature's work is in capacity
     */
    public TeamFeature(String key, double currentSize, boolean inCapacity) {
        super(key);
        this.currentSize = currentSize;
        this.inCapacity = inCapacity;
    }

    @Override
    public double getCurrentSize() {
        return currentSize;
    }

    /**
     * Sets this TeamFeature's current size to the specified value.
     */
    protected void setCurrentSize(double currentSize) {
        this.currentSize = currentSize;
    }

    @Override
    public double getInCapacitySize() {
        if (isInCapacity())
            return currentSize;
        return 0;
    }

    /**
     * Returns true if the work under this TeamFeature is in capacity.
     * Returns false otherwise.
     *
     * @return true if this TeamFeature is in capacity
     */
    public boolean isInCapacity() {
        return inCapacity;
    }
}
