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
import java.util.stream.Collectors;

public class ProgramManager extends AgileAggregator<String, ProgramFeature> {

    private Set<String> projects = new HashSet<String>();
    private Map<String, Collection<Project>> projectsByProgram =
        new HashMap<>();

    public Set<String> getProjects() {
        return projects;
    }

    public String[] getProjectArray() {
        String[] projectArray = projects.toArray(new String[0]);
        Arrays.sort(projectArray);
        return projectArray;
    }

    public DataTable getTotalSizeTable() {
        if (projectsByProgram.isEmpty()) buildProjectsByProgram();
        DataTable table = getProgramTableBasis().addHeaders(getProjectArray());
        for (String program : keySet()) {
            table
                .addRow()
                .insertCell("CSL Programs", program)
                .insertCell("Overall", Math.round(get(program).getCurrentSize()));
            Collection<Project> projects = projectsByProgram.get(program);
            for (Project project : projects)
                table.insertCell(project.getName(), Math.round(project.getCurrentSize()));
        }
        return table;
    }

    public boolean addProject(String project) {
        return projects.add(project);
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
