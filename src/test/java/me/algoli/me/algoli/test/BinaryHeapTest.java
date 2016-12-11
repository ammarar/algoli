package me.algoli.me.algoli.test;

import me.algoli.list.BinaryHeap;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Created by ammar on 12/1/16.
 */
public class BinaryHeapTest {

    @Test
    public void binaryHeapCreate() {
        BinaryHeap<Integer> heap = BinaryHeap.create();
        assertEquals(heap.size(), 0);
    }

    @Test
    public void numbersAddition() {
        BinaryHeap<Integer> heap = BinaryHeap.create();
        heap.add(2);
        heap.add(5);
        heap.add(3);
        heap.add(1);
        assertEquals(heap.remove().intValue(), 1);
        assertEquals(heap.remove().intValue(), 2);
        assertEquals(heap.remove().intValue(), 3);
        assertEquals(heap.remove().intValue(), 5);
    }
}
