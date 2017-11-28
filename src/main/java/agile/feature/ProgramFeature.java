package agile.feature;

/**
 * The ProgramFeature class represents a feature that contains other features
 * grouped by project. Different projects are represented by their names.
 * A single project may have one or many features.
 */
public class ProgramFeature extends Feature {

    private String summary;
    private int priorityScore;
    private FeatureAggregator<String> projects = new FeatureAggregator<>();

    /**
     * ProgramFeature Constructor.
     * Constructs a ProgramFeature with a feature key, summary, and priority
     * score.
     *
     * @param key this ProgramFeature's unique feature key
     * @param summary a summary of the work under this ProgramFeature
     * @param priorityScore the priority of the work under this ProgramFeature
     */
    public ProgramFeature(String key, String summary, int priorityScore) {
        super(key);
        this.summary = summary;
        this.priorityScore = priorityScore;
    }

    @Override
    public double getCurrentSize() {
        return projects.getCurrentSize();
    }

    @Override
    public double getInCapacitySize() {
        return projects.getInCapacitySize();
    }

    /**
     * Returns the priority of the work being done under this ProgramFeature.
     * The priority score is a ranking of the feature's priority score.
     * A priority score is a natural number from 0 to n.
     *
     * @return the priority of the work under this ProgramFeature
     */
    public int getPriorityScore() {
        return priorityScore;
    }

    /**
     * Returns an object representing the features under the project with the
     * specified project name. A null value is returned if there is no project
     * with this name under this ProgramFeature.
     *
     * @param projectName the name whose associated agile object is to be returned
     * @return an object representing the features under the specified project
     */
    public AgileObject getProject(String projectName) {
        return projects.get(projectName);
    }

    public Set<String> getProjectNames() {
        return projects.keySet();
    }

    /**
     * Returns a summary of the work being done under this ProgramFeature.
     *
     * @return a summary of the work under this ProgramFeature
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Adds the specified feature to the specified project under this
     * ProgramFeature. If a project with the specified name does not exist,
     * one will be created. The feature will only be added to an existing project
     * if it that feature is not already present under that project under this
     * ProgramFeature.
     *
     * @param projectName the project to which to added the given feature
     * @param feature the feature to be added to the given project
     * @return true if the given project does not already contain the specified
     * feature
     */
    public boolean addFeature(String projectName, Feature feature) {
        return projects.addFeature(projectName, feature);
    }

    /**
     * Returns true if this ProgramFeature contains no projects.
     *
     * @return true if this ProgramFeature contains no projects.
     */
    public boolean isEmpty() {
        return projects.isEmpty();
    }
}
