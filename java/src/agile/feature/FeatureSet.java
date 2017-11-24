package agile.feature;

import java.util.HashSet;
import java.util.Set;

/**
 * A collection of features that contains no duplicates. More formally,
 * FeatureSet instances contain no pair of features f1 and f2 such that
 * f1.equals(f2).
 */
public class FeatureSet implements AgileObject {

    private Set<Feature> features = new HashSet<>();

    @Override
    public double getCurrentSize() {
        return features
            .parallelStream()
            .mapToDouble(feature -> feature.getCurrentSize())
            .sum();
    }

    @Override
    public double getInCapacitySize() {
        return features
            .parallelStream()
            .mapToDouble(feature -> feature.getInCapacitySize())
            .sum();
    }

    /**
     * Adds the specified feature to this set if it is not already present.
     * If the set already contains the feature, the call leaves the set unchanged
     * and returns false.
     *
     * @param feature the feature to be added to this set
     * @return true if this set did not already contain the specified feature
     */
    public boolean addFeature(Feature feature) {
        return features.add(feature);
    }

    /**
     * Returns true if this set contains no features.
     *
     * @return true if this set contains no features.
     */
    public boolean isEmpty() {
        return features.isEmpty();
    }
}
