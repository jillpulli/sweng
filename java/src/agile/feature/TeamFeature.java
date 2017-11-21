package agile.feature;

public class TeamFeature {

    private double currentSize;
    private boolean inCapacity;


    public TeamFeature(double currentSize, boolean inCapacity) {
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
        if (inCapacity)
            return currentSize;
        return 0;
    }
}
