package me.algoli.list;

import me.algoli.Iterator;

public class LinkedList<E> implements List<E>, Queue<E>, Stack<E> {

    private int length;
    private Node<E> head;
    private Node<E> tail;

    private LinkedList() {

    }

    public static <E> LinkedList<E> create() { return new LinkedList<>(); }


    @Override
    public boolean append(E e) {
        if (isEmpty()) {
            initialize(e);
        } else {
            Node<E> node = Node.create(e);
            tail.append(node);
            tail = node;
        }
        length++;
        return true;
    }

    @Override
    public boolean prepend(E e) {
        if (isEmpty()) {
            initialize(e);
        } else {
            Node<E> node = Node.create(e);
            head.prepend(node);
            head = node;
        }
        length++;
        return true;
    }

    private void initialize(E e) {
        Node<E> node = Node.create(e);
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
            Node runner = nodeAt(index - 1);
            if (runner != null) {
                runner.append(Node.create(e));
                result = true;
                length++;
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
        Node<E> runner = head;
        for (; runner != null && count > 0; runner = runner.next) count--;
        return runner;
    }

    @Override
    public boolean remove(E e) {
        Node<E> node = find(e);
        if (node == null) {
            return false;
        }
        updateHeadTail(node);
        node.remove();
        length--;
        return true;
    }

    private Node<E> find(E e) {
        Node runner = head;
        for(; runner != null && !runner.getVal().equals(e); runner = runner.next);
        return runner;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        length = 0;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = nodeAt(index);
        if (node == null) {
            return null;
        }
        node.setVal(element);
        return element;
    }

    @Override
    public void push(E e) {
        prepend(e);
    }

    @Override
    public E pop() {
        Node<E> node = tail;
        tail = node.getPrev();
        node.remove();
        return node.getVal();
    }

    @Override
    public E pop(int i) {
        Node<E> node = nodeAt(i);
        if (node == null) {
            return null;
        }
        updateHeadTail(node);
        node.remove();
        return node.getVal();
    }

    private void updateHeadTail(Node<E> node) {
        if (node == null) return;
        if (node == head) {
            head = head.getNext();
        }
        if (node == tail) {
            tail = tail.getPrev();
        }
    }

    @Override
    public void enqueue(E e) {
        append(e);
    }

    @Override
    public E dequeue() {
        return pop(0);
    }

    @Override
    public E peek() {
        if (head == null) {
            throw new NullPointerException("List is empty");
        }
        return head.getVal();
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public void exchange(int i, int j) {
        if (i == j) {
            return;
        }
        Node<E> inode = nodeAt(i);
        Node<E> jnode = nodeAt(j);
        E temp = inode.getVal();
        inode.setVal(jnode.getVal());
        jnode.setVal(temp);
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator();
    }

    @Override
    public String toString() {
        return "[" + (head != null? head.toString() : "") + "]";
    }

    private static class Node<X> {
        private X val;
        private Node<X> next;
        private Node<X> prev;

        private Node() {}
        private Node(X val, Node<X> next, Node<X> prev) {
            this.val = val; this.next = next; this.prev = prev;
        }

        public static <X> Node<X> create() {return new Node<>();}
        public static <X> Node<X> create(X e) {return new Node<>(e, null, null);}
        public static <X> Node<X> create(X val, Node<X> next, Node<X> prev) {
            return new Node<>(val, next, prev);
        }

        public Node<X> getNext() {
            return next;
        }
        public X getVal() {
            return val;
        }

        public Node<X> getPrev() {
            return prev;
        }

        public Node<X> append(Node<X> node) {
            Node<X> after = this.getNext();
            this.next = node;
            node.prev = this;
            if (after != null) {
                after.prev = node;
                node.next = after;
            }
            return node;
        }

        public Node<X> prepend(Node<X> node) {
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

        public void setVal(X val) {
            this.val = val;
        }

        @Override
        public String toString() { return val != null ? val.toString()
                + (next != null ? ", " + next.toString() : "") : "";}
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node<E> node;

        public LinkedListIterator() {
            node = head;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            Node<E> curr = node;
            node = node.getNext();
            return curr.getVal();
        }
    }
}
