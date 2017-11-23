package agile.feature;

import java.util.HashSet;
import java.util.Set;

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

    public boolean addFeature(Feature feature) {
        return features.add(feature);
    }

    public boolean isEmpty() {
        return features.isEmpty();
    }
}
