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

    public boolean isEmpty() {
        return featureMap.isEmpty();
    }
}
