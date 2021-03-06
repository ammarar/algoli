package me.algoli.list;

import me.algoli.Iterator;

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

    public static <T> ArrayList<T> create() { return new ArrayList<>(); }
    public static <T> ArrayList<T> create(int initialCapacity) {
        return new ArrayList<>(initialCapacity);
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
        if (current == capacity) {
            expand(index);
        } else if (index < current) {
            shift(index);
        }
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
            throw new IllegalArgumentException(
                    String.format("No elements at index %d out of %d", index, size()));
        }
        return items[index];
    }

    @Override
    public boolean remove(E e) {
        // Resize if needed, taking out the element e
        int removeIndex = indexOf(e);
        if (removeIndex < 0) {
            return false;
        }
        pop(removeIndex);
        return true;
    }

    private int indexOf(E e) {
        int result = -1;
        for (int i=0; i < current; i++) {
            if (e.equals(get(i))) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public void clear() {
        this.items = createArrayList(DEFAULT_CAPACITY);
        this.current = 0;
        this.capacity = DEFAULT_CAPACITY;
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
            throw new IllegalArgumentException(
                    String.format("Attempting to remove index=[%d] while size is [%d]", i, current));
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
            removeAt(i);
        }

        current--;
        return item;
    }

    private void removeAt(int index) {
        if (index > 0) {
            System.arraycopy(items, 0, items, 0, index - 1);
        }
        System.arraycopy(items, index + 1, items, index, items.length - index - 1);
    }

    private void shift(int index) {
        if (index > 0) {
            System.arraycopy(items, 0, items, 0, index);
        }
        System.arraycopy(items, index, items, index + 1, items.length - index - 1);
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
        return current;
    }

    @Override
    public void exchange(int i, int j) {
        if (i == j) return;
        E temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public void fill(List<E> list, int start, int end) {
        if (end - start < 0) {
            throw new IllegalArgumentException(
                    String.format("Illegal arguments for start=[%d] and end=[%d]", start, end));
        }

        if (end >= items.length) {expand(end + 1);}

        for (int i=start; i <= end; i++) {
            items[i] = list.get(i);
        }
        current = end + 1;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("[");
        if (!isEmpty()) {
            Iterator<E> iterator = this.iterator();
            while (iterator.hasNext()) {
                b.append(iterator.next());
                if (iterator.hasNext()) {
                    b.append(", ");
                }
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
