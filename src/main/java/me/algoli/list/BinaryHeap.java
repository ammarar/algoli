package me.algoli.list;

/**
 * Created by ammar on 11/22/16.
 */
public class BinaryHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    private List<E> list;

    private BinaryHeap(int initialCapacity) {
        list = ArrayList.create(initialCapacity);
    }

    private BinaryHeap() {
        list = LinkedList.create();
    }

    public static <T extends Comparable<T>> BinaryHeap<T> create(int initialCapacity) {
        return new BinaryHeap<>(initialCapacity);
    }

    public static <T extends Comparable<T>> BinaryHeap<T> create() {
        return new BinaryHeap<>();
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            throw new IllegalStateException("No such element");
        }
        E e = list.pop(0);
        if (size() > 0) {
            list.prepend(list.pop());
            sink(0);
        }
        return e;
    }

    @Override
    public E peek() {
        return get(0);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean add(E e) {
        list.append(e);
        swim(size()-1);
        return true;
    }

    private void swim(int index) {
        int k = index;
        while (get(k).compareTo(get(parentOf(k))) < 0 && k > 0) {
            exchange(k, parentOf(k));
            k = parentOf(k);
        }
    }

    private static int parentOf(int i) {
        return (i - 1)/2;
    }

    private static int leftChildOf(int i) {
        return 2 * i + 1;
    }

    private static int rightChildOf(int i) {
        return 2 * i + 2;
    }

    private void sink(int index) {
        int k = index;
        while (k < size() && leftChildOf(k) < size()) {
            int lesserChild = leftChildOf(k);
            if (lesserChild < size()) {
                if (rightChildOf(k) < size() && get(lesserChild).compareTo(get(rightChildOf(k))) > 0) {
                    lesserChild = rightChildOf(k);
                }
            }
            exchange(index, lesserChild);
            k = lesserChild;
        }
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    private E get(int i) {
        return list.get(i);
    }

    private void exchange(int i, int j) {
        list.exchange(i, j);
    }
}
