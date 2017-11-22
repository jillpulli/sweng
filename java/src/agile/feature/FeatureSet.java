package agile.feature;

public class FeatureSet implements FeatureCollection {

    private Set<Feature> features = new HashSet<>();

    public double getCurrentSize() {
        return features
            .stream()
            .mapToDouble(feature -> feature.getCurrentSize())
            .sum();
    }

    @Override
    public int getNumberOfFeatures() {
        return features.size();
    }

    @Override
    public double getInCapacitySize() {
        return features
            .stream()
            .mapToDouble(feature -> feature.getInCapacitySize())
            .sum();
    }

    @Override
    public boolean isEmpty() {
        return features.isEmpty();
    }

    @Override
    public boolean addFeature(Feature feature) {
        return features.add(feature);
    }

    @Override
    public boolean removeFeature(Feature feature) {
        return features.remove(feature);
    }
}
