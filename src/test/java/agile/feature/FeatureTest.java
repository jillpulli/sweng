package agile.feature;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FeatureTest extends TestCase {

    private final Feature teamOne = new TeamFeature("TEAM-1", 80.0, true);
    private final Feature teamTwo = new TeamFeature("TEAM-2", 100.0, false);
    private final Feature teamThree = new TeamFeature("TEAM-3", 40.0, true);

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

        assertEquals("100%", teamOne.getTotalInCapacityWork());
    }

    public void testFeatureSet() {
        FeatureSet set = new FeatureSet();
        assertTrue(set.isEmpty());

        assertTrue(set.addFeature(teamOne));
        assertTrue(set.addFeature(teamTwo));
        assertTrue(set.addFeature(teamThree));

        assertEquals(220.0, set.getCurrentSize());
        assertEquals(120.0, set.getInCapacitySize());

        assertEquals(3, set.getNumberOfFeatures());
        assertFalse(set.addFeature(new ProductFeature("TEAM-1", 50.0, true)));
        assertEquals(3, set.getNumberOfFeatures());

        assertEquals("54%", set.getTotalInCapacityWork());
    }

    public void testProductFeature() {
        ProductFeature prod = new ProductFeature("PROD-1", 40.0, false);

        assertEquals(40.0, prod.getCurrentSize());
        assertEquals(0.0, prod.getInCapacitySize());

        prod.addFeature(teamOne);
        prod.addFeature(teamTwo);
        prod.addFeature(teamThree);

        assertEquals(220.0, prod.getCurrentSize());
        assertEquals(120.0, prod.getInCapacitySize());
    }

    public void testFeatureAggregator() {
        FeatureAggregator agg = new FeatureAggregator();

        assertTrue(agg.addFeature("DEP-1", teamOne));
        assertTrue(agg.addFeature("DEP-1", teamTwo));
        assertTrue(agg.addFeature("DEP-1", teamThree));

        assertEquals(3, agg.getNumberOfFeatures());
        assertFalse(agg.addFeature("DEP-1", new TeamFeature("TEAM-1", 10.0, false)));
        assertEquals(3, agg.getNumberOfFeatures());

        assertEquals(agg.getCurrentSize(), agg.get("DEP-1").getCurrentSize());

        assertTrue(agg.addFeature("DEP-2", new ProductFeature("PROD-1", 40.0, true)));

        assertEquals(220.0, agg.get("DEP-1").getCurrentSize());
        assertEquals(40.0, agg.get("DEP-2").getCurrentSize());
        assertEquals(260.0, agg.getCurrentSize());
        assertEquals(160.0, agg.getInCapacitySize());
    }
}
