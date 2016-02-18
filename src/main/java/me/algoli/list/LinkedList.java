package me.algoli.list;

import me.algoli.Iterator;

/**
 * Created by ammar on 1/8/16.
 */
public class LinkedList<E> implements List<E>, Queue<E>, Stack<E> {

    private Node<E> head;
    private Node<E> tail;

    private LinkedList() {

    }


    @Override
    public boolean append(E e) {
        if (isEmpty()) {
            initialize(e);
        } else {
            Node node = Node.create(e);
            tail.append(node);
            tail = node;
        }
        return true;
    }

    @Override
    public boolean prepend(E e) {
        if (isEmpty()) {
            initialize(e);
        } else {
            Node node = Node.create(e);
            head.prepend(node);
            head = node;
        }
        return true;
    }

    private void initialize(E e) {
        Node node = Node.create(e);
        this.head = node;
        this.tail = node;
    }

    @Override
    public boolean insert(final int index, E e) {
        boolean result = false;
        if (index == 0) {
            prepend(e);
            result = true;
        } else {
            Node runner = nodeAt(index);
            if (runner != null) {
                runner.append(Node.create(e));
                result = true;
            }
        }
        return result;
    }

    @Override
    public E get(int index) {
        return nodeAt(index).getVal();
    }

    private Node<E> nodeAt(final int index) {
        int count = index;
        Node runner = head;
        for (; runner != null && count > 0; runner = runner.next) count--;
        return runner;
    }

    @Override
    public boolean remove(E e) {
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
        public static <E> Node create(E e) {return new Node(e, null, null);}
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

        public Node append(Node node) {
            Node after = node.getNext();
            this.next = node;
            node.prev = this;
            if (after != null) {
                after.prev = node;
                node.next = after;
            }
            return node;
        }

        public Node prepend(Node node) {
            this.prev = node;
            node.next = this;
            return node;
        }

        public void remove() {
            if (this.prev != null) {
                this.prev.next = this.next;
            }

            if (this.next != null) {
                this.next.prev = this.prev;
            }

            this.next = null;
            this.prev = null;
        }

    }
}
