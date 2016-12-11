package me.algoli.me.algoli.test;

import me.algoli.tree.BinaryTree;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.stream.IntStream;

/**
 * Created by ammar on 12/5/16.
 */
public class BinaryTreeTest {

    @Test
    public void insertTest() {
        BinaryTree<Integer, Integer> tree = BinaryTree.create();
        IntStream.range(1, 11).forEach(n -> tree.insert(n, n));
        assertEquals(tree.size(), 10);
    }

    @Test
    public void getTest() {
        BinaryTree<Integer, Integer> tree = BinaryTree.create();
        IntStream.range(1, 11).forEach(n -> tree.insert(n, n));
        for (int i=1; i<= 10; i++) {
            assertEquals(tree.get(i), Integer.valueOf(i));
        }
    }

    @Test
    public void containsTest() {
        BinaryTree<Integer, Integer> tree = BinaryTree.create();
        IntStream.range(1, 11).forEach(n -> tree.insert(n, n));
        for (int i=1; i<= 10; i++) {
            assertTrue(tree.contains(i));
        }
        assertFalse(tree.contains(0));
    }

    @Test
    public void putTest() {
        BinaryTree<Integer, Integer> tree = BinaryTree.create();
        IntStream.range(1, 11).forEach(n -> tree.insert(n, n));
        tree.put(5, 15);
        tree.put(7, 17);
        assertEquals(tree.get(5), Integer.valueOf(15));
        assertEquals(tree.get(6), Integer.valueOf(6));
    }

    @Test
    public void deleteTest() {
        BinaryTree<Integer, Integer> tree = BinaryTree.create();
        IntStream.range(1, 11).forEach(n -> tree.insert(n, n));
        tree.delete(7);
        tree.delete(1);
        assertFalse(tree.contains(7));
        assertFalse(tree.contains(1));
    }
}
