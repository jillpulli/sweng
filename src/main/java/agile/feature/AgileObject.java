package agile.feature;

/**
 * The AgileObject class represents any object in an agile planning environment.
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

    /**
     * Returns the percentage of in capacity work under this AgileObject.
     * This value is the in capacity work divided by the current size.
     */
    String getTotalInCapacityWork() {
        double percentage = (getInCapacitySize() / getCurrentSize()) * 100;
        String percentString = new Double(percentage).toString();
        return percentString.substring(0, percentString.indexOf('.')) + '%';
    }
}
