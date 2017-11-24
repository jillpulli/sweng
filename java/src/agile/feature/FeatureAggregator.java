package agile.feature;

import java.util.HashMap;
import java.util.Map;

/**
 * The FeatureAggregator class represents a collection of one-to-many relationships
 * between a key and a set of features. Every key maps to a set containing
 * 1 to n features. Every feature under a particular key is unique. Every key
 * under a FeatureAggregator instance is also unique.
 */
public class FeatureAggregator implements AgileObject {

    private Map<String, FeatureSet> featureMap = new HashMap<>();

    /**
     * Returns an object representing the set of features mapped to the specified
     * key. A null value is returned if this FeatureAggregator contains no mapping
     * for the key.
     *
     * @param key the key whose associated agile object is to be returned
     * @return an object representing the features under the specified key
     */
    public AgileObject get(String key) {
        return featureMap.get(key);
    }

    @Override
    public double getCurrentSize() {
        return featureMap
            .values()
            .parallelStream()
            .mapToDouble(set -> set.getCurrentSize())
            .sum();
    }

    @Override
    public double getInCapacitySize() {
        return featureMap
            .values()
            .parallelStream()
            .mapToDouble(set -> set.getInCapacitySize())
            .sum();
    }

    /**
     * Adds the specified feature to the set mapped to the specified key.
     * If a key matching the argument does not exist, one will be created and
     * added to this FeatureAggregator. The feature will only be added to an
     * existing key's set if that feature is not already present in that set.
     *
     * @param key the key representing the set to which to add the specified feature
     * @param feature the feature to be added to the given key's set
     * @return true if the given key's set does not already contain the given feature
     */
    public boolean addFeature(String key, Feature feature) {
        boolean addSuccessful = false;

        if (featureMap.containsKey(key))
            addSuccessful = featureMap.get(key).addFeature(feature);
        else {
            FeatureSet set = new FeatureSet();
            addSuccessful = set.addFeature(feature);
            if (addSuccessful)
                featureMap.put(key, set);
        }

        return addSuccessful;
    }

    /**
     * Returns true if this FeatureAggregator contains no key-set mappings.
     *
     * @return true if this FeatureAggregator contains no key-set mappings
     */
    public boolean isEmpty() {
        return featureMap.isEmpty();
    }
}
