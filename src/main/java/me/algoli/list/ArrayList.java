package me.algoli.list;

import me.algoli.Iterator;

/**
 * Created by ammar on 1/8/16.
 */
public class ArrayList<E> implements List<E>, Queue<E>, Stack<E>  {

    private E[] items;
    private static final int DEFAULT_CAPACITY = 10;
    private int current = 0;


    private ArrayList() {
        items = createArrayList(DEFAULT_CAPACITY);
    }
    private ArrayList(int initialCapacity) {
        assert initialCapacity > 0;
        items = createArrayList(initialCapacity);
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
        // Resize if needed, leaving a empty place at the end
        // Add e
        return false;
    }

    @Override
    public boolean prepend(E e) {
        // Resize if needed, leaving an empty space at the beginning
        // Add e
        return false;
    }

    @Override
    public boolean insert(int index, E e) {
        // Resize if needed, leaving index empty
        // Add e
        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= current) {
            throw new IllegalArgumentException("");
        }
        return null;
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
        return null;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public E pop(int i) {
        return null;
    }

    @Override
    public void enqueue(E e) {

    }

    @Override
    public E dequeue() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public int size() {
        return current + 1;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
