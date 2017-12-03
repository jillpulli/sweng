package agile.feature;

import agile.util.DataTable;
import agile.util.ExportTable;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class ProgramManager extends AgileAggregator<String, ProgramFeature> {

    private Set<String> projects = new HashSet<>();
    private Map<String, Collection<Project>> projectsByProgram =
        new HashMap<>();

    public DataTable getFeatPercentInMatrix() {
        DataTable table =
            ExportTable
                .getFeaturePercentTableBasis()
                .addHeaders(getProjectArray());

        for (String program : keySet())
            get(program).forEach(feature ->
                feature
                    .addFeaturePercentEntries(table.addRow())
                    .insertCell(ExportTable.Program.toString(), program));

        return table
            .sortByInt(ExportTable.PriorityScore.toString())
            .reverseRows();
    }

    public DataTable getInOutPercentTable() {
        return makeProgramTable(AgileObject::getTotalInCapacityWork);
    }

    public DataTable getTotalSizeTable() {
        return makeProgramTable(agileObj ->
            Long.toString(Math.round(agileObj.getCurrentSize())));
    }

    public boolean addProject(String project) {
        return projects.add(project);
    }

    private String[] getProjectArray() {
        String[] projectArray = projects.toArray(new String[0]);
        Arrays.sort(projectArray);
        return projectArray;
    }

    void buildProjectsByProgram() {
        for (String program : keySet()) {
            Map<String, Project> projectMap = new HashMap<>();

            get(program)
                .stream()
                .flatMap(feature -> feature.getProjectSet().stream())
                .forEach(project -> {
                    String name = project.getName();

                    if (!projectMap.containsKey(name))
                        projectMap.put(name, new Project(name));

                    projectMap.get(name)
                        .addToCurrentSize(project.getCurrentSize())
                        .addToInCapacitySize(project.getInCapacitySize());
                });

            projectsByProgram.put(program, projectMap.values());
        }
    }

    private DataTable makeProgramTable(Function<AgileObject, String> function) {
        DataTable table =
            ExportTable
                .getProgramTableBasis()
                .addHeaders(getProjectArray());

        for (String program : keySet()) {
            table
                .addRow()
                .insertCell(ExportTable.Program.toString(), program)
                .insertCell(ExportTable.Total.toString(),
                    function.apply(get(program)));

            Collection<Project> progProjects = projectsByProgram.get(program);
            for (Project project : progProjects)
                table.insertCell(project.getName(),
                    function.apply(project));
        }

        return table.sortBy(ExportTable.Program.toString());
    }
}
