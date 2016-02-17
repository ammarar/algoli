package me.algoli.list;

/**
 * Created by ammar on 1/10/16.
 */
public interface Queue<E> {
    void enqueue(E e);
    E dequeue();
    E peek();
    int size();
}
