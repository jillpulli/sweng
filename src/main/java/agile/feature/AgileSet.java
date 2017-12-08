package agile.feature;

import java.lang.Iterable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * A collection of AgileObjects that contains no duplicates. More formally,
 * AgileSet instances contain no pair of objects a1 and a2 such that
 * a1.equals(a2).
 */
public class AgileSet<T extends AgileObject> extends AgileObject
implements Iterable<T> {

    private double currentSize;
    private double inCapacitySize = 0.0;
    private Set<T> items = new HashSet<>();

    public AgileSet() {
        currentSize = 0.0;
    }

    public AgileSet(double initialCurrentSize) {
        currentSize = initialCurrentSize;
    }

    @Override
    public double getCurrentSize() {
        if (currentSize <= 0.0)
            currentSize = items
                .stream()
                .mapToDouble(AgileObject::getCurrentSize)
                .sum();
        return currentSize;
    }

    @Override
    public double getInCapacitySize() {
        if (inCapacitySize <= 0.0)
            inCapacitySize = items
                .stream()
                .mapToDouble(AgileObject::getInCapacitySize)
                .sum();
        return inCapacitySize;
    }

    @Override
    public int getNumberOfFeatures() {
        return items
            .stream()
            .mapToInt(AgileObject::getNumberOfFeatures)
            .sum();
    }

    /**
     * Adds the specified AgileObject to this set if it is not already present.
     * If the set already contains the object, the call leaves the set unchanged
     * and returns false.
     *
     * @param item the object to be added to this set
     * @return true if this set did not already contain the specified object
     */
    public boolean add(T item) {
        boolean addSuccessful = items.add(item);
        if (addSuccessful) {
            currentSize = -1.0;
            inCapacitySize = -1.0;
        }
        return addSuccessful;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        items.forEach(action);
    }

    /**
     * Returns true if this set contains no objects.
     *
     * @return true if this set contains no objects.
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }

    /**
     * Returns the total number of AgileObjects in this AgileSet.
     *
     * @return the total number of features in this AgileSet
     */
    public int size() {
        return items.size();
    }

    @Override
    public Spliterator<T> spliterator() {
        return items.spliterator();
    }

    public Stream<T> stream() {
        return items.stream();
    }
}
