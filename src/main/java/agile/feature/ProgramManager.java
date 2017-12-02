package agile.feature;

import agile.util.DataTable;

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
        DataTable table = new DataTable(
            "Program Feature Key",
            "Summary",
            "CSL Programs",
            "Priority Score",
            "Total"
        ).addHeaders(getProjectArray());

        for (String program : keySet())
            get(program).forEach(feature ->
                feature.addFeaturePercentEntry(table.addRow()));

        return table;
    }

    public DataTable getInOutPercentTable() {
        return makeProgramTable(agileObj -> agileObj.getTotalInCapacityWork());
    }

    public DataTable getTotalSizeTable() {
        return makeProgramTable(agileObj ->
            Long.toString(Math.round(agileObj.getCurrentSize())));
    }

    public boolean addProject(String project) {
        return projects.add(project);
    }

    private DataTable getProgramTableBasis() {
        return new DataTable("CSL Programs", "Overall");
    }

    private String[] getProjectArray() {
        String[] projectArray = projects.toArray(new String[0]);
        Arrays.sort(projectArray);
        return projectArray;
    }

    private void buildProjectsByProgram() {
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
        if (projectsByProgram.isEmpty()) buildProjectsByProgram();

        DataTable table = getProgramTableBasis().addHeaders(getProjectArray());
        for (String program : keySet()) {
            table
                .addRow()
                .insertCell("CSL Programs", program)
                .insertCell("Overall", function.apply(get(program)));

            Collection<Project> progProjects = projectsByProgram.get(program);
            for (Project project : progProjects)
                table.insertCell(project.getName(),
                    function.apply(project));
        }
        return table;
    }
}
