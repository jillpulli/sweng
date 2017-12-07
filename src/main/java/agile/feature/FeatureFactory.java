package agile.feature;

import agile.util.FeatureRecord;
import agile.util.SimpleLogger;

import java.util.List;

public class FeatureFactory {

    public static void assemblePrograms(ProgramManager manager,
            List<FeatureRecord> records, SimpleLogger logger) {
        ProgramFeature currentProgram = ProgramFeature.EMPTY_PROGRAM_FEATURE;
        ProductFeature currentProduct = ProductFeature.EMPTY_PRODUCT_FEATURE;
        int index = 0;
        int numberOfRecords = records.size();
        FeatureRecord current;

        while (index < numberOfRecords &&
                (current = records.get(index)).getLevel() != 0) {
            logSkip(current, logger, "No parent Program Feature");
            index++;
        }

        while (index < numberOfRecords) {
            current = records.get(index++);
            boolean isUnique = false;

            switch (current.getLevel()) {
                case 0:
                    isUnique = manager.addFeature(
                        current.getProgram(),
                        (currentProgram = createProgramFeature(current)));
                    break;
                case 1:
                    String project = current.getProject();
                    manager.addProject(project);
                    isUnique = currentProgram.addFeature(
                        project,
                        (currentProduct = createProductFeature(current)));
                    break;
                case 2:
                    isUnique =
                        currentProduct.addFeature(createTeamFeature(current));
                    break;
                default:
                    isUnique = true;
                    logSkip(current, logger, "Bad 'Level' value");
            }

            if (!isUnique)
                logSkip(current, logger, "Duplicate");
        }

        logger.info(String.format(
            "Created %d features from %d records.",
            manager.getNumberOfFeatures(), numberOfRecords));
    }

    private static ProductFeature createProductFeature(FeatureRecord record) {
        return new ProductFeature(
            record.getKey(),
            record.getInCapacity(),
            record.getCurrentSize());
    }

    private static ProgramFeature createProgramFeature(FeatureRecord record) {
        return new ProgramFeature(
            record.getKey(),
            record.getSummary(),
            record.getPriorityScore(),
            record.getCurrentSize());
    }

    private static TeamFeature createTeamFeature(FeatureRecord record) {
        return new TeamFeature(
            record.getKey(),
            record.getInCapacity(),
            record.getCurrentSize());
    }

    private static void logSkip(FeatureRecord record, SimpleLogger logger,
            String reason) {
        logger.warning(String.format(
            "Skipping record %s: %s", record.getKey(), reason));
    }
}
