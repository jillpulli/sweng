package agile.feature;

public class TeamFeature extends CapacityFeature {

    private double currentSize;

    public TeamFeature(String key, boolean inCapacity, double currentSize) {
        super(key, inCapacity);
        this.currentSize = currentSize;
    }

    @Override
    public double getCurrentSize() {
        return currentSize;
    }

    @Override
    public double getInCapacitySize() {
        if (isInCapacity())
            return currentSize;
        return 0;
    }
}
