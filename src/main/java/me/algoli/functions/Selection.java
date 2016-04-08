package me.algoli.functions;

import me.algoli.list.List;
import me.algoli.sort.QuickSort;

/**
 * Created by aalrashe on 4/8/2016.
 */
public class Selection {
    private Selection() {}
    public static <T extends Comparable<? super T>> T select(List<T> list, int k) {
        int less = 0;
        int more = list.size() - 1;
        while (more > less) {
            int partition = QuickSort.partition(list, less, more);
            if (partition < k) less = partition + 1;
            else if (partition > k) more = partition - 1;
            else return list.get(partition);
        }
        return list.get(k);
    }
}
