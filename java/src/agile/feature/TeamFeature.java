package agile.feature;

/**
 * The TeamFeature class represents the final layer of decomposition of features.
 * Every instance of this class is only responsible for itself. It holds no
 * additional features.
 */
public class TeamFeature extends CapacityFeature {

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
        super(key, currentSize, inCapacity);
    }

    @Override
    public double getInCapacitySize() {
        if (isInCapacity())
            return getCurrentSize();
        return 0;
    }
}
