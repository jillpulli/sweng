package agile.feature;

import agile.util.FeatureRecord;
import agile.util.TableException;

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
     */
    public static void assemblePrograms(ProgramManager manager,
            List<FeatureRecord> records) {
        ProgramFeature currentProgram = ProgramFeature.EMPTY_PROGRAM_FEATURE;
        ProductFeature currentProduct = ProductFeature.EMPTY_PRODUCT_FEATURE;

        for (int index = 0, size = records.size(); index < size; index++) {
            FeatureRecord current = records.get(index);
            boolean isUnique;

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
                    throw new TableException("Bad 'Level' value", index);
            }

            if (!isUnique)
                throw new TableException("Duplicate feature detected", index);
        }

        manager.buildProjectArray();
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

    private FeatureFactory() { }
}
