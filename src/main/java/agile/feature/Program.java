package agile.feature;

import agile.util.DataTable;
import agile.util.ExportTable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * A Program represents a collection of ProgramFeaures.
 */
public class Program extends AgileSet<ProgramFeature> {

    private String name;
    private Map<String, Project> projects = new HashMap<>();

    /**
     * Program Constructor.
     * Constructs a Program with the given name.
     *
     * @param name the name of this Program
     */
    public Program(String name) {
        this.name = name;
    }

    /**
     * Returns a collection of Project objects containing the per-program
     * project sizes for this Program.
     *
     * @return a collection of Projects containing per-program project sizes
     */
    public Collection<Project> getProjects() {
        if (projects.isEmpty())
            this.stream()
                .flatMap(feature -> feature.getProjectSet().stream())
                .forEach(project -> {
                    String name = project.getName();
                    Project localProj;

                    if (!projects.containsKey(name))
                        projects.put(name, (localProj = new Project(name)));
                    else
                        localProj = projects.get(name);

                    localProj
                        .addToCurrentSize(project.getCurrentSize())
                        .addToInCapacitySize(project.getInCapacitySize());
                });

        return projects.values();
    }

    /**
     * Adds a Feature Percent Table row for each ProgramFeature under this
     * Program to the specified DataTable. The table is assumed to have
     * appropriate headers for the ProgramFeature data.
     *
     * @param table the table to which to add the information to
     * @return the specified DataTable
     * @see ProgramFeature#addFeaturePercentEntries
     */
    public DataTable addFeaturePercentTableRows(DataTable table) {
        this.stream()
            .forEach(feature ->
                feature.addFeaturePercentEntries(
                    table
                        .addRow()
                        .insertCell(ExportTable.Program.toString(), name)));

        return table;
    }

    /**
     * Adds this Program's name and the result of applying the specified
     * function to this Program as the Total. Assumes the table has a row
     * already inserted and has headers for the previously mentioned fields.
     *
     * @param table the table to which to add the information to
     * @param function application of this function will result in the total
     * that will be added to the table
     * @return the specified DataTable
     */
    public DataTable addProgramTableRow(DataTable table,
            Function<AgileObject, String> function) {
        table
            .insertCell(ExportTable.Program.toString(), name)
            .insertCell(
                ExportTable.Total.toString(),
                function.apply(this));

        getProjects().forEach(project ->
            table.insertCell(project.getName(), function.apply(project)));

        return table;
    }
}
