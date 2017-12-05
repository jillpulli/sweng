package agile.feature;

import agile.util.DataTable;
import agile.util.ExportTable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Program extends AgileSet<ProgramFeature> {

    private String name;
    private Map<String, Project> projects = new HashMap<>();

    public Program(String name) {
        this.name = name;
    }

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

    public DataTable addFeaturePercentTableRows(DataTable table) {
        this.stream()
            .forEach(feature ->
                feature.addFeaturePercentEntries(
                    table
                        .addRow()
                        .insertCell(ExportTable.Program.toString(), name)));

        return table;
    }

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
