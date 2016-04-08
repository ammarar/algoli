package me.algoli.sort;

import me.algoli.functions.Shuffle;
import me.algoli.list.List;

/**
 * Created by ammar on 2/22/16.
 */
public class QuickSort implements SortStrategy {
    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        if (!SortStrategy.needsSorting(list)) return;
        Shuffle.knuthShuffle(list);
        sort(list, 0, list.size()-1);
    }

    private <T extends Comparable<? super T>> void sort(List<T> list, int start, int end) {
        if (start >= end) return;
        int partition = partition(list, start, end);
        sort(list, start, partition-1);
        sort(list, partition+1, end);
    }

    public static <T extends Comparable<? super T>> int partition(List<T> list, int start, int end) {
        int partitionIndex = start;
        int less = start + 1;
        int more = end;
        while(true) {
            if (list.get(less).compareTo(list.get(partitionIndex)) < 0) {
                less++;
                if (less == end) break;
            }
            if (list.get(more).compareTo(list.get(partitionIndex)) > 0) {
                more--;
                if (more == start) break;
            }
            if (less >= more) break;
            list.exchange(less, more);
        }
        list.exchange(partitionIndex, more);
        return more;
    }
}
