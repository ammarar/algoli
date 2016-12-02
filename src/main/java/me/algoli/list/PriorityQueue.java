package me.algoli.list;

/**
 * Created by ammar on 11/22/16.
 */
public interface PriorityQueue<E extends Comparable<E>> {
    boolean add(E e);
    E remove();
    E peek();
    int size();
}