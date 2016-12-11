package me.algoli.sort;

import me.algoli.Iterator;
import me.algoli.list.BinaryHeap;
import me.algoli.list.List;

/**
 * Created by ammar on 12/1/16.
 */
public class HeapSort implements SortStrategy {

    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        if (!SortStrategy.needsSorting(list)) return;
        BinaryHeap<T> heap = BinaryHeap.create();
        Iterator<T> iter = list.iterator();
        while (iter.hasNext()) {
            heap.add(iter.next());
        }
        list.clear();
        while (!heap.isEmpty()) {
            list.append(heap.remove());
        }
    }
}
