/*
Invariants (notes):
- ArrayDeque is full is frontIndex == backIndex
- ArrayDeque is empty is frontIndex == (size / 2) and backIndex == (size / 2 + 1)
 */
public class ArrayDeque<T> {
    private int size = 0, frontIndex = 0, backIndex = 0;
    private T[] array;
    private final int SCALE_FACTOR = 2, INITIAL_SIZE = 8;

    public ArrayDeque() {
        array = (T[]) new Object[INITIAL_SIZE];
    }

    public ArrayDeque(ArrayDeque other) {
        T[] newArray = (T[]) new Object[other.array.length];
        for (int i = 0; i < other.size(); i++) {
            newArray[i] = (T) other.get(i);
            backIndex = i + 1;
        }

        frontIndex = newArray.length - 1;
        array = newArray;
        size = other.size();
    }

    public int size() {
        return size;
    }

    private int addOne(int num) {
        return (num + 1) % array.length;
    }

    private int minusOne(int num) {
        return num == 0 ? array.length - 1 : num - 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if (size == array.length) {
            return true;
        }
        return false;
    }

    public void addLast(T i) {
        if (!this.isFull()) {
            if (backIndex == 0 && array[backIndex] != null) {
                backIndex = addOne(backIndex);
            }
            array[backIndex] = i;
            size++;
            if (size != array.length) {
                backIndex = addOne(backIndex);
            }
        } else {
            this.increaseSize();
            array[backIndex] = i;
            backIndex = addOne(backIndex);
            size++;
        }
    }

    public void addFirst(T i) {
        if (!this.isFull()) {
            if (frontIndex == 0 && array[frontIndex] != null) {
                frontIndex = minusOne(frontIndex);
            }
            array[frontIndex] = i;
            size++;
            if (size != array.length) {
                frontIndex = minusOne(frontIndex);
            }
        } else {
            this.increaseSize();
            array[frontIndex] = i;
            size++;
            frontIndex = minusOne(frontIndex);
        }
    }

    public T remove(int index) {
        T i = array[index];
        array[index] = null;
        return i;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else if (isFull()) {
            size--;
            return remove(frontIndex);
        } else {
            frontIndex = addOne(frontIndex);
            size--;

            if (usageRatioExceeded()) {
                decreaseSize();
            }

            return remove(frontIndex);
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else if (isFull()) {
            return remove(backIndex);
        } else {
            if (backIndex == 0) {
                size--;
                return remove(backIndex);
            }
            backIndex = minusOne(backIndex);
            size--;

            if (usageRatioExceeded()) {
                decreaseSize();
            }

            return remove(backIndex);
        }
    }

    public T get(int index) {
        if (index > size || index < 0) {
            return null;
        }

        if ((frontIndex == 0 && array[frontIndex] != null) || isFull()) {
            return array[(frontIndex + index) % array.length];
        } else {
            return array[((addOne(frontIndex) + index) % array.length)];
        }

    }

    public void increaseSize() {
        T[] newArray = (T[]) new Object[array.length * SCALE_FACTOR];
        for (int i = 0; i < size; i++) {
            newArray[i] = get(i);
        }

        frontIndex = newArray.length - 1;
        backIndex = size;
        array = newArray;
    }

    public void decreaseSize() {
        T[] newArray = (T[]) new Object[array.length / 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = get(i);
        }

        frontIndex = newArray.length - 1;
        backIndex = size;
        array = newArray;
    }

    public boolean usageRatioExceeded() {
        double sizeDouble = (double) this.size;
        double arrLenDouble = (double) this.array.length;

        double R = sizeDouble / arrLenDouble;
        System.out.println(R);
        if (array.length > 16 && R < 0.25) {
            return true;
        }
        return false;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println("");
    }
}
