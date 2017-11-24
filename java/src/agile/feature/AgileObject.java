package agile.feature;

/**
 * The AgileObject class represents any object in an agile planning environment.
 */
public interface AgileObject {

    /**
     * Returns the current size of the work under this AgileObject.
     *
     * @return the current size of the work under this AgileObject
     */
    double getCurrentSize();

    /**
     * Returns the amount of in capacity work under this AgileObject.
     *
     * @return the amount of in capacity work under this AgileObject
     */
    double getInCapacitySize();
}
