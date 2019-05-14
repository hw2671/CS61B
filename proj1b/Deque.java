public interface Deque<T> {
    int size = 0;
    public void addFirst(T item);

    public void addLast(T item);

    public int size();

    public default boolean isEmpty() {
        return size == 0;
    }

    public void printDeque();

    public T removeFirst();

    public T removeLast();

    public T get(int i);
}
