package agile.assembly;

import agile.feature.ProductFeature;
import agile.feature.ProgramFeature;
import agile.feature.ProgramManager;
import agile.feature.TeamFeature;
import agile.util.DataRecord;

import java.util.List;

public class FeatureFactory {

    public static ProgramManager assemblePrograms (List<DataRecord> records) {
        ProgramManager programs = new ProgramManager();

        ProgramFeature currentProgram = ProgramFeature.EMPTY_PROGRAM_FEATURE;
        ProductFeature currentProduct = ProductFeature.EMPTY_PRODUCT_FEATURE;

        for (DataRecord record : records)
            switch (record.getLevel()) {
                case 0:
                    programs.add(
                        record.getProgram(),
                        (currentProgram = createProgramFeature(record)));
                    break;
                case 1:
                    String project = record.getProject();
                    programs.addProject(project);
                    currentProgram.addFeature(
                        project,
                        (currentProduct = createProductFeature(record)));
                    break;
                case 2:
                    currentProduct.addFeature(createTeamFeature(record));
                    break;
            }

        return programs;
    }

    public static ProductFeature createProductFeature(DataRecord record) {
        return new ProductFeature(
            record.getKey(),
            record.getCurrentSize(),
            record.getInCapacity());
    }

    public static ProgramFeature createProgramFeature(DataRecord record) {
        return new ProgramFeature(
            record.getKey(),
            record.getSummary(),
            record.getPriorityScore());
    }

    public static TeamFeature createTeamFeature(DataRecord record) {
        return new TeamFeature(
            record.getKey(),
            record.getCurrentSize(),
            record.getInCapacity());
    }
}
