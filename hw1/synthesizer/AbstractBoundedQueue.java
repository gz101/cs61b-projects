package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    /**
     * Getter method for capacity variable.
     */
    @Override
    public int capacity() {
        return capacity;
    }

    /**
     * Getter method for fillCount variable.
     */
    @Override
    public int fillCount() {
        return fillCount;
    }
}
