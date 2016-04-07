package me.algoli.list;

import me.algoli.Iterable;

/**
 * Created by ammar on 1/8/16.
 */
public interface List<E> extends Iterable {
    boolean append(E e);
    boolean prepend(E e);
    boolean insert(int index, E e);
    E get(int index);
    boolean remove(E e);
    void clear();
    boolean isEmpty();
    E set(int index, E element);
    E pop();
    E pop(int i);
    int size();
    void exchange(int i, int j);
    void fill(List<E> list, int start, int end);
}
