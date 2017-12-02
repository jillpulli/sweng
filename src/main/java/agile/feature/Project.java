package agile.feature;

public class Project extends AgileObject {

    private String name;
    private double currentSize;
    private double inCapacitySize;

    Project(String name) {
        this.name = name;
        this.currentSize = 0.0;
        this.inCapacitySize = 0.0;
    }

    Project(String name, double currentSize, double inCapacitySize) {
        this.name = name;
        this.currentSize = currentSize;
        this.inCapacitySize = inCapacitySize;
    }

    @Override
    public double getCurrentSize() {
        return currentSize;
    }

    @Override
    public double getInCapacitySize() {
        return inCapacitySize;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getNumberOfFeatures() {
        return 0;
    }

    public Project addToCurrentSize(double currentSize) {
        this.currentSize += currentSize;
        return this;
    }

    public Project addToInCapacitySize(double inCapacitySize) {
        this.inCapacitySize += inCapacitySize;
        return this;
    }
}
