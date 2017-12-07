package agile.feature;

import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FeatureTest extends TestCase {

    private final Feature teamOne = new TeamFeature("TEAM-1", true, 80.0);
    private final Feature teamTwo = new TeamFeature("TEAM-2", false, 100.0);
    private final Feature teamThree = new TeamFeature("TEAM-3", true, 40.0);

    public FeatureTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(FeatureTest.class);
    }

    public void testTeamFeature() {
        assertEquals("TEAM-2", teamTwo.getKey());
        assertEquals(100.0, teamTwo.getCurrentSize());
        assertEquals(0.0, teamTwo.getInCapacitySize());

        assertEquals(teamThree.getCurrentSize(), teamThree.getInCapacitySize());

        assertEquals("0%", teamTwo.getTotalInCapacityWork());
        assertEquals("100%", teamOne.getTotalInCapacityWork());
    }

    public void testAgileSet() {
        AgileSet<Feature> set = new AgileSet<>(90.0);

        assertTrue(set.isEmpty());
        assertEquals(90.0, set.getCurrentSize());
        assertEquals(0.0, set.getInCapacitySize());

        assertTrue(set.add(teamOne));
        assertTrue(set.add(teamTwo));
        assertTrue(set.add(teamThree));

        assertEquals(220.0, set.getCurrentSize());
        assertEquals(120.0, set.getInCapacitySize());

        assertEquals(3, set.size());
        assertFalse(set.add(new ProductFeature("TEAM-1", true, 50.0)));
        assertEquals(3, set.size());

        assertEquals(3, set.getNumberOfFeatures());

        assertEquals("55%", set.getTotalInCapacityWork());

        double sum = 0;
        for (Feature feat : set)
            sum += feat.getCurrentSize();
        assertEquals(220.0, sum);
    }

    public void testProductFeature() {
        ProductFeature prod = new ProductFeature("PROD-1", false, 40.0);

        assertEquals(40.0, prod.getCurrentSize());
        assertEquals(0.0, prod.getInCapacitySize());
        assertEquals(1, prod.getNumberOfFeatures());

        assertTrue(prod.addFeature(teamOne));
        assertTrue(prod.addFeature(teamTwo));
        assertTrue(prod.addFeature(teamThree));

        assertEquals(220.0, prod.getCurrentSize());
        assertEquals(120.0, prod.getInCapacitySize());

        assertEquals(4, prod.getNumberOfFeatures());
    }

    public void testProgramFeature() {
        ProgramFeature prog =
            new ProgramFeature("PROG-1", "SUMMARY REDACTED", 3000, 120.0);

        assertEquals("SUMMARY REDACTED", prog.getSummary());
        assertEquals(3000, prog.getPriorityScore());
        assertEquals(120.0, prog.getCurrentSize());
        assertEquals(0.0, prog.getInCapacitySize());
        assertEquals(1, prog.getNumberOfFeatures());

        assertTrue(prog.addFeature(
            "DEPT-A", new ProductFeature("PROD-1", true, 20.0)));
        assertTrue(prog.addFeature(
            "DEPT-A", new ProductFeature("PROD-2", false, 30.0)));
        assertTrue(prog.addFeature(
            "DEPT-B", new ProductFeature("PROD-3", true, 40.0)));

        assertEquals(90.0, prog.getCurrentSize());
        assertEquals(60.0, prog.getInCapacitySize());
        assertEquals(4, prog.getNumberOfFeatures());

        assertFalse(prog.addFeature(
            "DEPT-A", new ProductFeature("PROD-1", false, 50.0)));

        assertEquals(90.0, prog.getCurrentSize());
        assertEquals(4, prog.getNumberOfFeatures());

        Set<Project> projects = prog.getProjectSet();
        assertEquals(2, projects.size());
    }
}
