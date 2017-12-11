package agile.feature;

import agile.util.FeatureRecord;
import agile.util.SimpleLogger;

import java.util.List;

/**
 * A factory class for building Feature objects from data records.
 * Capable of assembling the Feature hierarchy based on the Level value in
 * each record.
 */
public class FeatureFactory {

    /**
     * Assembles the Feature hierarchy represented in the specified list of
     * records and places it into the specified ProgramManager. Feature
     * types are determined by the Level value in a given FeatureRecord. All
     * features of a higher Level are stored in the previous feature of a lower
     * level.
     * <p>The levels go as follows:
     * <ul>
     *      <li><b>Level 0:</b> Program Feature</li>
     *      <li><b>Level 1:</b> Product Feature</li>
     *      <li><b>Level 2:</b> Team Feature</li>
     * </ul>
     *
     * Once all viable Feature objects have been saved to the specified
     * ProgramManager, the ProgramManager's record of all of its project
     * names is initialized with a call to its <code>buildProjectArray()</code>
     * method.
     *
     * @param manager the manager to which to save the Feature hierarchy
     * @param records the records from which to create the Feature hierarchy
     * @param logger the logger to log assembly progress, warnings, and errors
     */
    public static void assemblePrograms(ProgramManager manager,
            List<FeatureRecord> records, SimpleLogger logger) {
        ProgramFeature currentProgram = ProgramFeature.EMPTY_PROGRAM_FEATURE;
        ProductFeature currentProduct = ProductFeature.EMPTY_PRODUCT_FEATURE;
        int index = 0;
        int numberOfRecords = records.size();
        FeatureRecord current;

        while (index < numberOfRecords &&
                !(current = records.get(index)).getLevel().equals("0")) {
            logSkip(current, logger, "No parent Program Feature");
            index++;
        }

        while (index < numberOfRecords) {
            current = records.get(index++);
            boolean isUnique = false;

            switch (current.getLevel()) {
                case "0":
                    isUnique = manager.addFeature(
                        current.getProgram(),
                        (currentProgram = createProgramFeature(current)));
                    break;
                case "1":
                    isUnique = currentProgram.addFeature(
                        current.getProject(),
                        (currentProduct = createProductFeature(current)));
                    break;
                case "2":
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

        manager.buildProjectArray();

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

    private FeatureFactory() { }
}
