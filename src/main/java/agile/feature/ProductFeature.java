package agile.feature;

/**
 * The ProductFeature class represents a TeamFeature that is capable of
 * storing additional features. A ProductFeature's in-capacity size is determined
 * by all of the features it contains.
 */
public class ProductFeature extends CapacityFeature {

    public static final ProductFeature EMPTY_PRODUCT_FEATURE =
        new ProductFeature("", false, 0.0);

    private AgileSet<Feature> features;

    /**
     * ProductFeature Constructor.
     * Constructs a ProductFeature with a feature key and current size. The
     * ProductFeature may be in or out of capacity.
     *
     * @param key this ProductFeature's unique feature key
     * @param currentSize the size of this ProductFeature
     * @param inCapacity true if this ProductFeature's work is in capacity
     */
    ProductFeature(String key, boolean inCapacity, double currentSize) {
        super(key, inCapacity);
        features = new AgileSet<Feature>(currentSize);
    }

    @Override
    public double getCurrentSize() {
        return features.getCurrentSize();
    }

    @Override
    public double getInCapacitySize() {
        if (features.isEmpty())
            return super.getInCapacitySize();
        return features.getInCapacitySize();
    }

    @Override
    public int getNumberOfFeatures() {
        return features.getNumberOfFeatures() + 1;
    }

    /**
     * Adds the specified feature to this ProductFeature if it is not already
     * present.
     *
     * @param feature the feature to be added to this ProductFeature
     * @return true if this instance did not already contain the specified feature
     */
    public boolean addFeature(Feature feature) {
        return features.add(feature);
    }
}
