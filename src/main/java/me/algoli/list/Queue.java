package me.algoli.list;

public interface Queue<E> {
    void enqueue(E e);
    E dequeue();
    E peek();
    int size();
}
