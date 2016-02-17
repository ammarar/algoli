package me.algoli.list;

import me.algoli.Iterator;

/**
 * Created by ammar on 1/8/16.
 */
public class LinkedList<E> implements List, Queue, Stack{

    private Node<E> head;
    private Node<E> tail;

    private LinkedList() {

    }


    @Override
    public boolean append(Object o) {
        return false;
    }

    @Override
    public boolean insert(int index, Object o) {
        return false;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void push(Object o) {

    }

    @Override
    public Object pop() {
        return null;
    }

    @Override
    public Object pop(int i) {
        return null;
    }

    @Override
    public void enqueue(Object o) {

    }

    @Override
    public Object dequeue() {
        return null;
    }

    @Override
    public Object peek() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    private static class Node<E> {
        private E val;
        private Node next;
        private Node prev;

        private Node() {}
        private Node(E val, Node next, Node prev) {
            this.val = val; this.next = next; this.prev = prev;
        }

        public static Node create() {return new Node();}
        public static <E> Node<E> create(E val, Node next, Node prev) {
            return new Node<>(val, next, prev);
        }

        public Node getNext() {
            return next;
        }
        public E getVal() {
            return val;
        }

        public Node getPrev() {
            return prev;
        }
    }
}
