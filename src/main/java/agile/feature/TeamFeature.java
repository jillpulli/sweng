package agile.feature;

/**
 * The TeamFeature class represents the final layer of decomposition of features.
 * Every instance of this class is only responsible for itself. It holds no
 * additional features.
 */
public class TeamFeature extends CapacityFeature {

    private double currentSize;

    /**
     * TeamFeature Constructor.
     * Constructs a TeamFeature with a feature key and current size. The
     * TeamFeature may be in or out of capacity.
     *
     * @param key this TeamFeature's unique feature key
     * @param inCapacity true if this TeamFeature's work is in capacity
     * @param currentSize the size of this TeamFeature
     */
    TeamFeature(String key, boolean inCapacity, double currentSize) {
        super(key, inCapacity);
        this.currentSize = currentSize;
    }

    @Override
    public double getCurrentSize() {
        return currentSize;
    }

    @Override
    public int getNumberOfFeatures() {
        return 1;
    }
}
