package agile.feature;

/**
 * The AgileObject class represents any entity in an agile planning environment.
 */
public abstract class AgileObject {

    /**
     * Returns the current size of the work under this AgileObject.
     *
     * @return the current size of the work under this AgileObject
     */
    abstract double getCurrentSize();

    /**
     * Returns the amount of in capacity work under this AgileObject.
     *
     * @return the amount of in capacity work under this AgileObject
     */
    abstract double getInCapacitySize();

    abstract int getNumberOfFeatures();

    /**
     * Returns the percentage of in capacity work under this AgileObject.
     * This value is the in capacity work divided by the current size.
     */
    public String getTotalInCapacityWork() {
        long percentage = Math.round((getInCapacitySize() / getCurrentSize()) * 100);
        return Long.toString(percentage) + '%';
    }
}
