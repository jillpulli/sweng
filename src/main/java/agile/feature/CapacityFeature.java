package agile.feature;

public abstract class CapacityFeature extends Feature {

    private boolean inCapacity;

    CapacityFeature(String key, boolean inCapacity) {
        super(key);
        this.inCapacity = inCapacity;
    }

    @Override
    public double getInCapacitySize() {
        if (inCapacity)
            return getCurrentSize();
        return 0.0;
    }
}
