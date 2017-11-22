package agile.feature;

public interface FeatureCollection {

    public boolean addFeature(Feature feature);

    public int getNumberOfFeatures();

    public boolean isEmpty();

    public boolean removeFeature(Feature feature);
}
