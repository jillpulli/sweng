package agile.feature;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The AgileAggregator class represents a collection of one-to-many relationships
 * between a key and a set of AgileObjects. Every key maps to a set containing
 * 1 to n objects. Every object under a particular key is unique. Every key
 * under a AgileAggregator instance is also unique.
 *
 * @param <K> the type of keys maintained by this AgileAggregator
 * @param <V> the type of AgileObjects being stored under each key
 */
public class AgileAggregator<K,V extends AgileObject> extends AgileObject {

    private Map<K, AgileSet<V>> map = new HashMap<>();

    /**
     * Returns the set of AgileObjects being stored under the specified key.
     * A null value is returned if this AgileAggregator contains no mapping
     * for the key.
     *
     * @param key the key whose associated set of AgileObjects is to be returned
     * @return the set of AgileObjects under the specified key
     */
    public AgileSet<V> get(K key) {
        return map.get(key);
    }

    @Override
    public double getCurrentSize() {
        return map
            .values()
            .parallelStream()
            .mapToDouble(AgileSet::getCurrentSize)
            .sum();
    }

    @Override
    public double getInCapacitySize() {
        return map
            .values()
            .parallelStream()
            .mapToDouble(AgileSet::getInCapacitySize)
            .sum();
    }

    /**
     * Returns the total number of keys being stored under this AgileAggregator.
     *
     * @return the total number of keys being stored under this AgileAggregator
     */
    public int getNumberOfKeys() {
        return map.size();
    }

    /**
     * Returns the total number of AgileObjects in this AgileAggregator.
     *
     * @return the total number of AgileObjects in this AgileAggregator
     */
    public int getNumberOfObjects() {
        return map
            .values()
            .parallelStream()
            .mapToInt(AgileSet::size)
            .sum();
    }

    /**
     * Adds the specified AgileObject to the set mapped to the specified key.
     * If a key matching the argument does not exist, one will be created and
     * added to this AgileAggregator. The object will only be added to an
     * existing key's set if that object is not already present in that set.
     *
     * @param key the key representing the set to which to add the specified feature
     * @param item the AgileObject to be added to the given key's set
     * @return true if the given key's set does not already contain the given feature
     */
    public boolean add(K key, V item) {
        boolean addSuccessful = false;

        if (map.containsKey(key))
            addSuccessful = map.get(key).add(item);
        else {
            AgileSet set = new AgileSet();
            addSuccessful = set.add(item);
            if (addSuccessful)
                map.put(key, set);
        }

        return addSuccessful;
    }

    /**
     * Returns true if this AgileAggregator contains no key-set mappings.
     *
     * @return true if this AgileAggregator contains no key-set mappings
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Returns the set of keys being stored under this AgileAggregator.
     *
     * @return the set of keys being stored under this AgileAggregator
     */
    public Set<K> keySet() {
        return map.keySet();
    }
}
