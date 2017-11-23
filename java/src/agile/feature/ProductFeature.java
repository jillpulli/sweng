package agile.feature;

public class ProductFeature extends CapacityFeature {

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

    public boolean addFeature(Feature feature) {
        return features.addFeature(feature);
    }

    public boolean isEmpty() {
        return features.isEmpty();
    }
}
