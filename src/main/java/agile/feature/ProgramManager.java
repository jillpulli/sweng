package agile.feature;

import agile.util.DataTable;
import agile.util.ExportTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class ProgramManager {

    private Map<String, Program> programs = new HashMap<>();
    private Set<String> projects = new HashSet<>();

    public DataTable getFeatPercentInMatrix() {
        DataTable table =
            ExportTable
                .getFeaturePercentTableBasis()
                .addHeaders(getProjectArray());

        programs.values().forEach(program ->
            program.addFeaturePercentTableRows(table));

        return table
            .sortByInt(ExportTable.PriorityScore.toString())
            .reverseRows();
    }

    public DataTable getInOutPercentTable() {
        return makeProgramTable(AgileObject::getTotalInCapacityWork);
    }

    public int getNumberOfFeatures() {
        return programs
            .values()
            .parallelStream()
            .mapToInt(AgileObject::getNumberOfFeatures)
            .sum();
    }

    public DataTable getTotalSizeTable() {
        return makeProgramTable(agileObj ->
            Long.toString(Math.round(agileObj.getCurrentSize())));
    }

    public boolean addFeature(String programName, ProgramFeature feature) {
        if (programs.containsKey(programName))
            return programs.get(programName).add(feature);

        Program program = new Program(programName);
        programs.put(programName, program);
        return program.add(feature);
    }

    public boolean addProject(String project) {
        return projects.add(project);
    }

    private String[] getProjectArray() {
        String[] projectArray = projects.toArray(new String[0]);
        Arrays.sort(projectArray);
        return projectArray;
    }

    private DataTable makeProgramTable(Function<AgileObject, String> function) {
        DataTable table =
            ExportTable
                .getProgramTableBasis()
                .addHeaders(getProjectArray());

        for (Program program : programs.values())
            program.addProgramTableRow(table.addRow(), function);

        return table.sortBy(ExportTable.Program.toString());
    }
}
