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

    private Set<T> items = new HashSet<>();

    @Override
    public double getCurrentSize() {
        return items
            .parallelStream()
            .mapToDouble(T::getCurrentSize)
            .sum();
    }

    @Override
    public double getInCapacitySize() {
        return items
            .parallelStream()
            .mapToDouble(T::getInCapacitySize)
            .sum();
    }

    @Override
    public int getNumberOfFeatures() {
        return items
            .parallelStream()
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
        return items.add(item);
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

    public Stream<T> parallelStream() {
        return items.parallelStream();
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
