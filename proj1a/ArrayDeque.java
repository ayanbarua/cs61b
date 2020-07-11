public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int first;
    private final int FINAL_LENGTH = 8;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = (items.length - size) / 2;
        nextLast = nextFirst + 1;
        first = nextFirst;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int tempLength = items.length - first;
        System.arraycopy(items, first, a, (a.length / 2) - 1, tempLength);
        if (tempLength != items.length) {
            System.arraycopy(items, 0, a, ((a.length / 2) - 1) + tempLength, first);
        }
        items = a;
        nextFirst = minusOne((a.length / 2) - 1);
        first = plusOne(nextFirst);
        nextLast = plusOne(nextFirst + size);
    }

    private void resizeDown(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (capacity < FINAL_LENGTH) {
            return;
        }
        if (first + size > items.length) {
            int left = (first + size) - items.length;
            int tempLength = items.length - first;
            System.arraycopy(items, first, a, (a.length / 2) - 1, tempLength);
            System.arraycopy(items, nextLast - 1, a, ((a.length / 2) - 1) + tempLength, left);
        } else {
            System.arraycopy(items, first, a, (a.length / 2) - 1, size);
        }
        items = a;
        nextFirst = minusOne((a.length / 2) - 1);
        first = plusOne(nextFirst);
        nextLast = plusOne(nextFirst + size);

    }

    private boolean isFull() {
        return (size == items.length);
    }

    public void addFirst(T item) {
        if (isFull()) {
            resize(size * 2);
        }

        first = nextFirst;
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        if (isFull()) {
            resize(size * 2);
        }

        items[nextLast] = item;
        if (items[first] == null) {
            first = nextLast;
        }
        nextLast = plusOne(nextLast);
        size += 1;
    }

    private void checkResize() {
        if ((double) size / items.length < 0.25) {
            resizeDown(items.length / 2);
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        nextLast = minusOne(nextLast);
        T item = items[nextLast];
        items[nextLast] = null;
        size--;
        checkResize();
        return item;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T item = items[first];
        items[first] = null;
        first = plusOne(first);
        nextFirst = plusOne(nextFirst);
        size -= 1;
        checkResize();
        return item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        } else if (first + index < items.length) {
            return items[first + index];
        } else {
            return items[first + index - items.length];
        }
    }

    public int size() {
        return size;
    }

    private int minusOne(int x) {
        if (x == 0) {
            return items.length - 1;
        }
        return x - 1;
    }

    private int plusOne(int x) {
        if (x == items.length - 1) {
            return 0;
        }
        return x + 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}
