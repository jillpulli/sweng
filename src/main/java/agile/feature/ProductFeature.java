package agile.feature;

import java.util.Collections;
import java.util.Map;

/**
 * The ProductFeature class represents a TeamFeature that is capable of
 * storing additional features. A ProductFeature's in-capacity size is determined
 * by all of the features it contains.
 */
public class ProductFeature extends TeamFeature {

    public static final ProductFeature EMPTY_PRODUCT_FEATURE =
        new ProductFeature("", 0.0, false);

    private AgileSet<Feature> features = new AgileSet();

    /**
     * ProductFeature Constructor.
     * Constructs a ProductFeature with a feature key and current size. The
     * ProductFeature may be in or out of capacity.
     *
     * @param key this ProductFeature's unique feature key
     * @param currentSize the size of this ProductFeature
     * @param inCapacity true if this ProductFeature's work is in capacity
     */
    public ProductFeature(String key, double currentSize, boolean inCapacity) {
        super(key, currentSize, inCapacity);
    }

    @Override
    public double getCurrentSize() {
        double curr = super.getCurrentSize();
        if (curr < 0)
            curr = features.getCurrentSize();
        setCurrentSize(curr);
        return curr;
    }

    @Override
    public double getInCapacitySize() {
        if (!features.isEmpty())
            return features.getInCapacitySize();
        return super.getInCapacitySize();
    }

    /**
     * Adds the specified feature to this ProductFeature if it is not already
     * present.
     *
     * @param feature the feature to be added to this ProductFeature
     * @return true if this instance did not already contain the specified feature
     */
    public boolean addFeature(Feature feature) {
        setCurrentSize(-1.0);
        return features.add(feature);
    }

    /**
     * Returns true if this ProductFeature contains no features.
     *
     * @return true if this ProductFeature contains no features
     */
    public boolean isEmpty() {
        return features.isEmpty();
    }

    @Override
    public Map<String, String> toEntry() {
        return Collections.EMPTY_MAP;
    }
}
