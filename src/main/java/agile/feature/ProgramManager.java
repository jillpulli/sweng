package agile.feature;

import agile.util.DataTable;
import agile.util.ExportTable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Represents a collection of Program objects. This class contains various
 * methods for generating DataTables based the Programs contained within a
 * given ProgramManager.
 */
public class ProgramManager {

    private Map<String, Program> programs = new HashMap<>();
    private String[] projects;

    public static DataTable makeFeatPercentInMatrix(ProgramManager manager) {
        DataTable table =
            ExportTable
                .getFeaturePercentTableBasis()
                .addHeaders(manager.projects);

        manager.getPrograms().forEach(program ->
            program.addFeaturePercentTableRows(table));

        return table
            .sortByInt(ExportTable.PriorityScore.toString())
            .reverseRows();
    }

    public static DataTable makeInOutPercentTable(ProgramManager manager) {
        return manager.makeProgramTable(AgileObject::getTotalInCapacityWork);
    }

    public static DataTable makeTotalSizeTable(ProgramManager manager) {
        return manager.makeProgramTable(agileObj ->
            Long.toString(Math.round(agileObj.getCurrentSize())));
    }

    /**
     * Returns the total number of Features in this ProgramManager.
     *
     * @return the total number of Features in this ProgramManager
     */
    public int getNumberOfFeatures() {
        return programs
            .values()
            .stream()
            .mapToInt(Program::getNumberOfFeatures)
            .sum();
    }

    public boolean addFeature(String programName, ProgramFeature feature) {
        if (programs.containsKey(programName))
            return programs.get(programName).add(feature);

        Program program = new Program(programName);
        programs.put(programName, program);
        return program.add(feature);
    }

    void buildProjectArray() {
        projects = programs
            .values()
            .stream()
            .flatMap(program -> program.getProjects().stream())
            .map(Project::getName)
            .distinct()
            .sorted()
            .toArray(String[]::new);
    }

    private Collection<Program> getPrograms() {
        return programs.values();
    }

    private DataTable makeProgramTable(Function<AgileObject, String> function) {
        DataTable table =
            ExportTable
                .getProgramTableBasis()
                .addHeaders(projects);

        for (Program program : programs.values())
            program.addProgramTableRow(table.addRow(), function);

        return table.sortBy(ExportTable.Program.toString());
    }
}
