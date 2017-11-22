package agile.feature;

import java.util.HashMap;
import java.util.Map;

public class FeatureAggregator implements AgileObject {

    private Map<String, FeatureSet> featureMap = new HashMap<>();

    public AgileObject get(String key) {
        return featureMap.get(key);
    }

    @Override
    public double getCurrentSize() {
        return featureMap
            .values()
            .stream()
            .mapToDouble(set -> set.getCurrentSize())
            .sum();
    }

    @Override
    public double getInCapacitySize() {
        return featureMap
            .values()
            .stream()
            .mapToDouble(set -> set.getInCapacitySize())
            .sum();
    }

    public boolean addFeature(String key, Feature feature) {
        FeatureSet set;

        if (featureMap.containsKey(key))
            set = featureMap.get(key);
        else
            set = new FeatureSet();

        boolean addSuccessful = set.addFeature(feature);
        if (addSuccessful)
            featureMap.put(key, set);

        return addSuccessful;
    }

    public boolean isEmpty() {
        return featureMap.isEmpty();
    }
}
