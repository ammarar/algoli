package me.algoli.list;

/**
 * Created by ammar on 1/10/16.
 */
public interface Stack<E> {
    void push(E e);
    E pop();
    E peek();
    int size();
}
