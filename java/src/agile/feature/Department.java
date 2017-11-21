package agile.feature;

public class Department {

    private String name;
    private List<Feature> features;

    public Department(String name, List<Feature> features) {
        this.name = name;
        this.features = features;
    }

    public String getName() {
        return name;
    }

    public double getSizeOfFeatures() {
        double size = 0;
        for (Feature feat : features)
            size += feat.getCurrentSize();
        return size;
    }

    public double getInCapacitySize() {
        double capSize = 0;
        for (Feature feat : features)
            capSize += feat.getInCapacitySize();
        return capSize;
    }
}
