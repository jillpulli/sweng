package agile.feature;

public class ProductFeature {

    private double currentSize;
    private boolean inCapacity;
    private List<Feature> features;

    public ProductFeature(double currentSize, boolean inCapacity) {
        this.currentSize = currentSize;
        this.inCapacity = inCapacity;
    }

    public double getCurrentSize() {
        return currentSize;
    }

    public boolean isInCapacity() {
        return inCapacity;
    }

    public double getInCapacitySize() {
        if (features.isEmpty())
            return super.getInCapacitySize();
        else {
            double capSize = 0;
            for (Feature feat : features)
                capSize += feat.getInCapacitySize();
            return capSize;
        }
    }
}
