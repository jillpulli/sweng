package agile.feature;

import agile.util.DataTable;
import agile.util.ExportTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The ProgramFeature class represents a feature that contains other features
 * grouped by project. Different projects are represented by their names.
 * A single project may have one or many features.
 */
public class ProgramFeature extends Feature {

    /**
     * An out-of-capacity ProgramFeature with an empty string as the feature
     * key and zeor current size.
     */
    public static final ProgramFeature EMPTY_PROGRAM_FEATURE =
        new ProgramFeature("", "", 0, 0.0);

    private double currentSize;
    private double inCapacitySize;
    private String summary;
    private int priorityScore;
    private Map<String, AgileSet<Feature>> projects = new HashMap<>();

    /**
     * ProgramFeature Constructor.
     * Constructs a ProgramFeature with a feature key, summary, and priority
     * score.
     *
     * @param key this ProgramFeature's unique feature key
     * @param summary a summary of the work under this ProgramFeature
     * @param priorityScore the priority of the work under this ProgramFeature
     * @param currentSize this ProgramFeature's initial current size
     */
    ProgramFeature(String key, String summary, int priorityScore,
            double currentSize) {
        super(key);
        this.summary = summary;
        this.priorityScore = priorityScore;
        this.currentSize = currentSize;
    }

    @Override
    public double getCurrentSize() {
        if (currentSize <= 0.0)
            currentSize = projects
                .values()
                .parallelStream()
                .mapToDouble(AgileSet::getCurrentSize)
                .sum();
        return currentSize;
    }

    @Override
    public double getInCapacitySize() {
        if (inCapacitySize <= 0.0)
            inCapacitySize = projects
                .values()
                .parallelStream()
                .mapToDouble(AgileSet::getInCapacitySize)
                .sum();
        return inCapacitySize;
    }

    @Override
    public int getNumberOfFeatures() {
        return projects
            .values()
            .parallelStream()
            .mapToInt(AgileSet::getNumberOfFeatures)
            .sum() + 1;
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

    /**
     * Returns a set of the Projects under this ProgramFeature.
     * At the time of this method call, each Project will have a current size
     * and in-capacity size matching the projects being represented by this
     * ProgramFeature. Note that manipulating this set or the Projects inside
     * has no effect on this ProgramFeature.
     *
     * @return a set of Project objects representing the projects under this
     * ProgramFeature
     */
    public Set<Project> getProjectSet() {
        Set<Project> set = new HashSet<>();
        for (String name : projects.keySet()) {
            AgileSet<Feature> project = projects.get(name);
            set.add(new Project(
                name,
                project.getCurrentSize(),
                project.getInCapacitySize()
            ));
        }
        return set;
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
        boolean addSuccessful = false;

        if (projects.containsKey(projectName))
            addSuccessful = projects.get(projectName).add(feature);
        else {
            AgileSet<Feature> set = new AgileSet<>();
            projects.put(projectName, set);
            addSuccessful = set.add(feature);
        }

        if (addSuccessful) {
            currentSize = -1.0;
            inCapacitySize = -1.0;
        }

        return addSuccessful;
    }

    /**
     * Adds this ProgramFeature's feature key, summary, priority score, and
     * percentage of total in-capacity work to the last row of the specified
     * DataTable. This method call assumes the table has a row already inserted
     * and has headers for the previously-mentioned fields.
     *
     * @param table the table to which to add the information to
     * @return the specified DataTable
     */
    public DataTable addFeaturePercentEntries(DataTable table) {
        table
            .insertCell(ExportTable.ProgramKey.toString(), getKey())
            .insertCell(ExportTable.Summary.toString(), summary)
            .insertCell(ExportTable.PriorityScore.toString(), priorityScore)
            .insertCell(ExportTable.Total.toString(), getTotalInCapacityWork());

        for (String project : projects.keySet())
            table.insertCell(project,
                projects.get(project).getTotalInCapacityWork());

        return table;
    }
}
