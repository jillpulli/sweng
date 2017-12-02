package agile.feature;

/**
 * The Feature class represents a subset of work in an agile planning environment.
 * A feature instance is identified by a unique feature key. Two features
 * with matching feature keys are considered to be the same feature.
 */
public abstract class Feature extends AgileObject {

    private String key;

    /**
     * Feature Constructor.
     * Constructs a Feature with a unique feature key.
     *
     * @param key this Feature's unique feature key
     */
    Feature(String key) {
        this.key = key;
    }

    /**
     * Returns this Feature's unique feature key.
     *
     * @return this Feature's unique feature key
     */
    String getKey() {
        return key;
    }

    /**
     * Compares this Feature to the specified object. The result is true if and
     * only if the argument is not null and is a Feature object that has the same
     * key as this object.
     *
     * @param obj the object to compare this Feature against
     * @return true if the given object represents a Feature with the exact same
     * feature key as this Feature.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Feature))
            return false;
        Feature other = (Feature) obj;
        return key.equals(other.getKey());
    }

    /**
     * Returns a hash code for this Feature. The hash code is equal to this
     * Feature's key's hash code.
     *
     * @return a hash code value for this Feature
     */
    @Override
    public int hashCode() {
        return key.hashCode();
    }
}
