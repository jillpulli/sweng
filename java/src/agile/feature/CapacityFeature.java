package agile.feature;

public abstract class CapacityFeature extends Feature {

    private boolean inCapacity;

    public CapacityFeature(String key, boolean inCapacity) {
        super(key);
        this.inCapacity = inCapacity;
    }

    public boolean isInCapacity() {
        return inCapacity;
    }
}
