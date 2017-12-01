package agile.feature;

public class Project extends AgileObject {

    private String name;
    private double currentSize;
    private double inCapacitySize;

    public Project(String name, double currentSize, double inCapacitySize) {
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

    @Override
    public int getNumberOfFeatures() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public Project setCurrentSize(double currentSize) {
        this.currentSize = currentSize;
        return this;
    }

    public Project setInCapacitySizse(double inCapacitySize) {
        this.inCapacitySize = inCapacitySize;
        return this;
    };

    public Project addToCurrentSize(double currentSize) {
        this.currentSize += currentSize;
        return this;
    }

    public Project addToInCapacitySize(double inCapacitySize) {
        this.inCapacitySize += inCapacitySize;
        return this;
    }
}
