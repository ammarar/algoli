package me.algoli.list;

import java.util.Iterator;

/**
 * Created by ammar on 1/8/16.
 */
public interface AlgoliList<E> {

    boolean add(E e);
    boolean add(int index, E e);
    E get(int index);
    boolean remove(E e);
    void clear();
    boolean isEmpty();
    E set(int index, E element);
    Iterator<E> iterator();
}
