package agile.feature;

public class ProgramFeature extends Feature {

    private String summary;
    private int priorityScore;
    private FeatureAggregator projects = new FeatureAggregator();

    public ProgramFeature(String key, String summary, int priorityScore) {
        super(key);
        this.summary = summary;
        this.priorityScore = priorityScore;
    }

    public String getSummary() {
        return summary;
    }

    public int getPriorityScore() {
        return priorityScore;
    }

    public AgileObject getProject(String projectName) {
        return projects.get(projectName);
    }

    @Override
    public double getCurrentSize() {
        return projects.getCurrentSize();
    }

    @Override
    public double getInCapacitySize() {
        return projects.getInCapacitySize();
    }

    public boolean addFeature(String projectName, Feature feature) {
        return projects.addFeature(projectName, feature);
    }

    public boolean isEmpty() {
        return projects.isEmpty();
    }
}
