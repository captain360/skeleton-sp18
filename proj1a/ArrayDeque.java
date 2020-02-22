/* Invariants :-
 * 0. When the list is empty diff b/w nextFront and nextLast should be 1.
 * 1. nextFront points to the index where next first item is added.
 * 2. nextLast points to the index where next last item is added.
 * 3. First item exists at plusOne(nextFront)
 * 4. Last item exists at minusOne(nextLast)
 * 5. When an item is added to the front, nextFront becomes minusOne(nextFront).
 * 6. When an item is added to the back , nextLast becomes plusOne(nextLast).
 * 7. When an item is removed from front, nextFront becomes plusOne(nextFront).
 * 8. When an item is removed from back, nextLast becomes minusOne(nextLast).
 * 9. At any moment , size is the number of items in the list.
 * */


public class ArrayDeque<T> {
    private int size;
    private int nextFront;
    private int nextLast;
    private T[] items;
    private static final int RFACTOR = 2;
    private static final double MIN_USAGE_RATIO = 0.3;


    //Creates an empty deque.
    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        size = 0;
        nextFront = 4;
        nextLast = 5;

    }

    private int plusOne(int index) {
        if (index + 1 > items.length - 1) {
            return 0;
        }
        return index + 1;
    }

    private int minusOne(int index) {
        if (index - 1 < 0) {
            return items.length - 1;
        }
        return index - 1;
    }

    //Adds the item to the front of the deque.
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }
        items[nextFront] = item;
        nextFront = minusOne(nextFront);
        size++;
    }

    //Adds the item to the end of the deque.
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;

    }

    //Returns and removes the first item in deque if it exists else return null
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        T item = items[plusOne(nextFront)];
        items[plusOne(nextFront)] = null;
        nextFront = plusOne(nextFront);
        size--;

        if (size > 16 && size < items.length * MIN_USAGE_RATIO) {
            resize(items.length / 2);
        }

        return item;
    }

    //Returns and removes the last item in deque if it exists else return null
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        T item = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        size--;

        if (size > 16 && size < items.length * MIN_USAGE_RATIO) {
            resize(items.length / 2);
        }
        return item;
    }


    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        int firstIndex = plusOne(nextFront);
        for (int i = 0; i < index; i++) {
            firstIndex = plusOne(firstIndex);
        }
        return items[firstIndex];
    }

    public void printDeque() {

        int start = plusOne(nextFront);
        for (int i = 0; i < size; i++) {
            System.out.print(items[start] + " ");
            start = plusOne(start);
        }
    }


    private void resize(int capacity) {

        T[] newArray = (T[]) new Object[capacity];

        int start = plusOne(nextFront);
        for (int i = 0; i < size; i++) {
            newArray[i] = items[start];
            start = plusOne(start);
        }

        items = newArray;
        nextFront = items.length - 1;
        nextLast = size;
    }
}

