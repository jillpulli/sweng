package agile.feature;

public class ProgramFeature {

    private String key;
    private List<Department> departments;
    private int priorityScore;

    public ProgramFeature(String key, List<Department> departments,
            int priorityScore) {
        this.key = key;
        this.departments = departments;
        this.priorityScore = priorityScore;
    }

    public String getKey() {
        return key;
    }

    public int getPriorityScore() {
        return priorityScore;
    }

    public double getCurrentSize() {
        double currSize = 0;
        for (Department dep : departments)
            currSize += dep.getSizeOfFeatures();
        return currSize;
    }

    public double getInCapacitySize() {
        double capSize = 0;
        for (Department dep : departments)
            capSize += dep.getInCapacitySize();
        return capSize;
    }
}
