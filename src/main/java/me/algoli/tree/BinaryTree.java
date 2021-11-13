package me.algoli.tree;

import me.algoli.Iterable;
import me.algoli.list.ArrayList;
import me.algoli.list.List;
import me.algoli.list.Stack;
import me.algoli.tuple.Tuple;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class BinaryTree<Key, Value> implements BinaryTreeOps<Key, Value> {

    private Node root;

    private BinaryTree() {}

    public static <K, V> BinaryTree<K, V> create() {
        return new BinaryTree<>();
    }

    @Override
    public void insert(Key key, Value val) {
        if (root == null) {
            root = new Node(key, val);
        } else {
            boolean added = false;
            Node candidate = root;
            Node inserted = new Node(key, val);
            Random rand = new Random();
            while (!added) {
                boolean leftChild = rand.nextBoolean();
                if (leftChild) {
                    if (candidate.getLeft() == null) {
                        candidate.setLeft(inserted);
                        added = true;
                    } else {
                        candidate = candidate.getLeft();
                    }
                } else {
                    if (candidate.getRight() == null) {
                        candidate.setRight(inserted);
                        added = true;
                    } else {
                        candidate = candidate.getRight();
                    }
                }
            }
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        else return 1 + size(node.getLeft()) + size(node.getRight());
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Value get(Key key) {
        Node lookup = lookup(key);
        if (lookup == null) {
            return null;
        }
        return lookup.getVal();
    }

    private Node lookup(Key key) {
        List<Node> list = ArrayList.create(1);
        preOrderLookup(root, p -> p.getKey().equals(key), (n) -> {}, (n) -> list.append(n));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Override
    public boolean contains(Key key) {
        return lookup(key) != null;
    }

    @Override
    public void put(Key key, Value val) {
        Node node = lookup(key);
        if (node == null) {
            throw new IllegalArgumentException(String.format("No key [%s] was found to update value", key));
        }
        node.setVal(val);
    }

    @Override
    public void delete(Key key) {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot delete from an empty tree");
        }
        // If leaf, remove.
        // else, get a leaf and replace with deleted node.
        Stack<Tuple<Node, Node>> stack = ArrayList.create();
        Node parent = null;
        Node deleteNode = null;
        Node replaceNode = null;
        Node parentReplaceNode = null;
        stack.push(Tuple.of(root, null));
        while (!stack.isEmpty()) {
            Tuple<Node,Node> tuple = stack.pop();
            Node node = tuple.getLeft();
            if (node.getKey().equals(key)) {
                deleteNode = node;
                parent = tuple.getRight();
                if (replaceNode != null) {
                    break;
                }
            }
            if (node.getLeft() != null) stack.push(Tuple.of(node.getLeft(), node));
            if (node.getRight() != null) stack.push(Tuple.of(node.getRight(), node));
            if (replaceNode == null && node.isLeaf() && !node.getKey().equals(key)) {
                replaceNode = node;
                parentReplaceNode = tuple.getRight();
            }
            if (deleteNode != null && replaceNode != null) {
                break;
            }
        }

        if (parent == null && deleteNode.isLeaf()) {
            // Root and leaf
            root = null;
        } else if (deleteNode.isLeaf()) {
            if (parent.getLeft() != null && parent.getLeft().getKey().equals(key)) {
                parent.setLeft(null);
            }
            if (parent.getRight() != null && parent.getRight().getKey().equals(key)) {
                parent.setRight(null);
            }
        } else {
            // Internal
            deleteNode.setKey(replaceNode.getKey());
            deleteNode.setVal(replaceNode.getVal());
            if (parentReplaceNode.getLeft()!=null && parentReplaceNode.getLeft().getKey().equals(deleteNode.getKey())) {
                parentReplaceNode.setLeft(null);
            } else if (parentReplaceNode.getRight()!=null && parentReplaceNode.getRight().getKey().equals(deleteNode.getKey())) {
                parentReplaceNode.setRight(null);
            }
        }
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        else if (node.isLeaf()) return 1;
        else return Math.max(height(node.getLeft()), height(node.getRight())) + 1;
    }

    @Override
    public Iterable<Key> keys() {
        List<Key> keys = ArrayList.create();
        preOrderLookup(root, node -> true, node -> keys.append(node.getKey()), (n) -> {});
        return keys;
    }

    private void preOrderLookup(Node node, Predicate<Node> stopCondition, Consumer<Node> consumer, Consumer<Node> stopConsumer) {
        if (node == null) return;
        consumer.accept(node);
        if (stopCondition.test(node)) {
            stopConsumer.accept(node);
            return;
        }
        preOrderLookup(node.getLeft(), stopCondition, consumer, stopConsumer);
        preOrderLookup(node.getRight(), stopCondition, consumer, stopConsumer);
    }

    private void inOrderLookup(Node node, Predicate<Node> stopCondition, Consumer<Node> consumer, Consumer<Node> stopConsumer) {
        inOrderLookup(node.getLeft(), stopCondition, consumer, stopConsumer);
        if (node == null) return;
        consumer.accept(node);
        if (stopCondition.test(node)) {
            stopConsumer.accept(node);
            return;
        }
        inOrderLookup(node.getRight(), stopCondition, consumer, stopConsumer);
    }

    private void postOrderLookup(Node node, Predicate<Node> stopCondition, Consumer<Node> consumer, Consumer<Node> stopConsumer) {
        postOrderLookup(node.getLeft(), stopCondition, consumer, stopConsumer);
        postOrderLookup(node.getRight(), stopCondition, consumer, stopConsumer);
        if (node == null) return;
        consumer.accept(node);
        if (stopCondition.test(node)) {
            stopConsumer.accept(node);
            return;
        }
    }

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;

        Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }

        public Node(Key key, Value val, Node left, Node right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }

        Key getKey() {
            return key;
        }

        public void setKey(Key key) {
            this.key = key;
        }

        Value getVal() {
            return val;
        }

        void setVal(Value val) {
            this.val = val;
        }

        Node getLeft() {
            return left;
        }

        void setLeft(Node left) {
            this.left = left;
        }

        Node getRight() {
            return right;
        }

        void setRight(Node right) {
            this.right = right;
        }

        boolean isLeaf() {
            return getRight() == null && getLeft() == null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
