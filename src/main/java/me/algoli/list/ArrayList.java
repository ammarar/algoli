package me.algoli.list;

import me.algoli.Iterator;

/**
 * Created by ammar on 1/8/16.
 */
public class ArrayList<E> implements List<E>, Queue<E>, Stack<E>  {

    private E[] items;
    private static final int DEFAULT_CAPACITY = 10;
    private int current = 0;
    private int capacity = DEFAULT_CAPACITY;

    private ArrayList() {
        items = createArrayList(DEFAULT_CAPACITY);
    }
    private ArrayList(int initialCapacity) {
        assert initialCapacity > 0;
        items = createArrayList(initialCapacity);
        capacity = initialCapacity;
    }

    @SuppressWarnings("unchecked")
    private E[] createArrayList(int initialCapacity) {
        return (E[]) new Object[initialCapacity];
    }

    public static ArrayList create() { return new ArrayList(); }
    public static ArrayList create(int initialCapacity) {
        return new ArrayList(initialCapacity);
    }

    @Override
    public boolean append(E e) {
        insert(current, e);
        return true;
    }

    @Override
    public boolean prepend(E e) {
        insert(0, e);
        return true;
    }

    @Override
    public boolean insert(int index, E e) {
        if (index < 0 || index > current) {
            throw new IllegalArgumentException(
                    String.format("Parameter index=[%d] is greater than the largest index element [%d]", index,
                            current));
        }
        // Resize if needed, leaving index empty
        if (current == capacity) expand(index);
        // Add e
        items[index] = e;
        current++;
        return true;
    }

    @SuppressWarnings("unchecked")
    private void expand(int emptyIndex) {
        assert emptyIndex >= 0 && emptyIndex <= current;
        int newCapacity = items.length * 2;
        E[] newItems = (E[]) new Object[newCapacity];
        if (emptyIndex == 0) {
            System.arraycopy(items, emptyIndex, newItems, emptyIndex + 1, items.length);
        } else if (emptyIndex == current) {
            System.arraycopy(items, 0, newItems, 0, items.length);
        } else {
            System.arraycopy(items, 0, newItems, 0, emptyIndex - 1);
            System.arraycopy(items, emptyIndex, newItems, emptyIndex + 1, items.length - emptyIndex);
        }
        items = newItems;
        capacity = newCapacity;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= current) {
            throw new IllegalArgumentException("");
        }
        return items[index];
    }

    @Override
    public boolean remove(E e) {
        // Resize if needed, taking out the element e
        return false;
    }

    @Override
    public void clear() {
        this.items = createArrayList(DEFAULT_CAPACITY);
    }

    @Override
    public boolean isEmpty() {
        return current == 0;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= current) {
            throw new IllegalArgumentException("");
        }
        items[index] = element;
        return element;
    }

    @Override
    public void push(E e) {
        append(e);
    }

    @Override
    public E pop() {
        if (current <= 0) {
            throw new IllegalArgumentException("");
        }
        return pop(current-1);
    }

    @Override
    public E pop(int i) {
        if (i < 0 || i >= current) {
            throw new IllegalArgumentException("");
        }

        if (current == 0) {
            throw new IllegalArgumentException("");
        }

        E item = items[i];
        // if shrinkable, remove i.
        if (current - 1 < items.length / 4) {
            shrink(i);
        } else {
            // else shift elements removing i.
            shift(i);
        }

        current--;
        return item;
    }

    private void shift(int index) {
        System.arraycopy(items, 0, items, 0, index);
        System.arraycopy(items, index + 1, items, index, items.length);
    }

    @SuppressWarnings("unchecked")
    private void shrink(int index) {
        int newCapacity = items.length / 4;
        E[] newItems = (E[]) new Object[newCapacity];
        System.arraycopy(items, 0, newItems, 0, index);
        System.arraycopy(items, index + 1, newItems, index, newItems.length);
        items = newItems;
        capacity = newCapacity;
    }

    @Override
    public void enqueue(E e) {
        append(e);
    }

    @Override
    public E dequeue() {
        return pop(0);
    }

    @Override
    public E peek() { return items[0]; }

    @Override
    public int size() {
        return current + 1;
    }

    @Override
    public void exchange(int i, int j) {
        if (i == j) return;
        E temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    @Override
    public Iterator iterator() {
        return new ArrayListIterator();
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        Iterator<E> iterator = this.iterator();
        b.append("[");
        while (iterator.hasNext()) {
            b.append(iterator.next());
            if (iterator.hasNext()) {
                b.append(", ");
            }
        }
        b.append("]");
        return b.toString();
    }

    private class ArrayListIterator implements Iterator<E> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < current ;
        }

        @Override
        public E next() {
            E nextItem = items[currentIndex];
            currentIndex++;
            return nextItem;
        }
    }
}
