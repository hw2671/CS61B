public interface Deque<T> {
    int SIZE = 0;
    void addFirst(T item);

    void addLast(T item);

    int size();

    default boolean isEmpty() {
        return SIZE == 0;
    }

    void printDeque();

    T removeFirst();

    T removeLast();

    T get(int i);
}
