package me.algoli.tree;

import me.algoli.Iterable;

/**
 * Created by ammar on 12/4/16.
 */
public interface BinaryTreeOps<Key, Value> {

    void insert(Key key, Value val);
    int size();
    boolean isEmpty();
    Value get(Key key);
    boolean contains(Key key);
    void put(Key key, Value val);
    void delete(Key key);
    int height();
    Iterable<Key> keys();
}
