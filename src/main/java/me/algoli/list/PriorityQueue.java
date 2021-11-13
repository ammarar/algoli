package me.algoli.list;

public interface PriorityQueue<E extends Comparable<? super E>> {
    boolean add(E e);
    E remove();
    E peek();
    int size();
}