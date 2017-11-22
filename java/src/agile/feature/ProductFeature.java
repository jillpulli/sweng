package agile.feature;

public class ProductFeature extends CapacityFeature
implements FeatureCollection {

    private double currentSize;
    private FeatureSet features = new FeatureSet();

    public ProductFeature(String key, boolean inCapacity, double currentSize) {
        super(key, inCapacity);
        this.currentSize = currentSize;
    }

    @Override
    public double getCurrentSize() {
        return currentSize;
    }

    @Override
    public double getInCapacitySize() {
        if (features.isEmpty())
            return features.getInCapacitySize();
        if (isInCapacity())
            return currentSize;
        return 0;
    }

    @Override
    public boolean addFeature(Feature feature) {
        return features.addFeature(feature);
    }

    @Override
    public int getNumberOfFeatures() {
        return features.getNumberOfFeatures();
    }

    @Override
    public boolean isEmpty() {
        return features.isEmpty();
    }

    @Override
    public boolean removeFeature(Feature feature) {
        return features.removeFeature(feature);
    }
}
