package agile.feature;

import agile.util.DataTable;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProgramManager extends AgileAggregator<String, ProgramFeature> {

    private Set<String> projects = new HashSet<String>();
    private Map<String, Collection<Project>> projectsByProgram =
        new HashMap<>();

    public String[] getProjectArray() {
        String[] projectArray = projects.toArray(new String[0]);
        Arrays.sort(projectArray);
        return projectArray;
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

    private void buildProjectsByProgram() {
        for (String program : keySet()) {
            Map<String, Project> projectMap = new HashMap<>();
            List<Project> allProjects =
                get(program)
                    .stream()
                    .flatMap(feature -> feature.getProjectSet().stream())
                    .collect(Collectors.toList());
            for (Project project : allProjects) {
                String name = project.getName();
                if (!projectMap.containsKey(name))
                    projectMap.put(name, new Project(name, 0, 0));
                Project progProject = projectMap.get(name);
                progProject.addToCurrentSize(project.getCurrentSize());
                progProject.addToInCapacitySize(project.getInCapacitySize());
            }
            projectsByProgram.put(program, projectMap.values());
        }
    }

    private DataTable getProgramTableBasis() {
        return new DataTable("CSL Programs", "Overall");
    }
}
