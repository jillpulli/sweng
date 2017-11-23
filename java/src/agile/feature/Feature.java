package agile.feature;

public abstract class Feature implements AgileObject {

    private String key;

    public Feature(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Feature))
            return false;
        Feature other = (Feature) obj;
        return key.equals(other.getKey());
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }
}
