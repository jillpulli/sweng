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
 * AgileSet instances contain no pair of objects <code>a1</code> and
 * <code>a2</code> such that <code>a1.equals(a2)</code>.
 *
 * @param <T> the type of AgileObjects in this set
 */
public class AgileSet<T extends AgileObject> extends AgileObject
implements Iterable<T> {

    private double currentSize;
    private double inCapacitySize = 0.0;
    private Set<T> items = new HashSet<>();

    /**
     * AgileSet Constructor with zero initial current size.
     * Creates an empty AgileSet with a current size of zero.
     */
    public AgileSet() {
        currentSize = 0.0;
    }

    /**
     * AgileSet Constructor with a specified initial current size.
     * Creates an empty AgileSet with the specified current size value.
     *
     * @param initialCurrentSize this AgileSet's initial current size value
     */
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
     * <p><b>Implementation Note</b><br>
     * A successful add operation results in this AgileSet's internal current
     * size and incapacity size fields being set to a negative number. This is
     * done in order to flag the agile size retrieval methods to recalculate
     * the values on the next call.</p>
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

    /**
     * Performs the given action for each element of this AgileSet until all
     * elements have been processed or the action throws an exception.
     *
     * @param action the action to be performed for each element
     */
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

    /**
     * Returns an iterator over elements of type T.
     *
     * @return an Iterator
     */
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

    /**
     * Creates a Spliterator over the elements described by this Iterable.
     *
     * @return a Spliterator over the elements described by this Iterable
     */
    @Override
    public Spliterator<T> spliterator() {
        return items.spliterator();
    }

    /**
     * Returns a sequential Stream with this AgileSet's collection of
     * AgileObjects as its source.
     *
     * @return a sequential Stream over the elements in this collection
     */
    public Stream<T> stream() {
        return items.stream();
    }
}
