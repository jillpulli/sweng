package agile.feature;

public class ProgramFeature extends Feature implements FeatureCollection {

    private String summary;
    private int priorityScore;
    private Map<String, FeatureSet> projects = new HashMap<>();

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

    @Override
    public double getCurrentSize() {
        return projects
            .values()
            .stream()
            .mapToDouble(project -> project.getCurrentSize())
            .sum();
    }

    @Override
    public double getInCapacitySize() {
        return projects
            .values()
            .stream()
            .mapToDouble(project -> project.getInCapacitySize())
            .sum();
    }

    @Override
    public boolean isEmpty() {
        return projects.isEmpty();
    }

    @Override
    public int getNumberOfFeatures() {
        return projects
            .values()
            .stream();
            .mapToInt(project -> project.getNumberOfFeatures()
            .sum();
    }

    @Override
    public boolean addFeature(String projectName, Feature feature) {
        FeatureSet project;

        if (projects.containsKey(projectName))
            project = projects.get(projectName);
        else
            project = new FeatureSet();

        boolean addSuccessful = project.addFeature(feature);
        if (addSuccessful)
            projects.put(projectName, project);

        return addSuccessful;
    }

    @Override
    public boolean removeFeature(Feature feature) {
        // TODO Make a way to remove features from projects.
        // If a project is empty, remove that project.
        return true;
    }
}
