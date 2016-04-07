package me.algoli.sort;

import me.algoli.list.ArrayList;
import me.algoli.list.List;

/**
 * Created by ammar on 2/22/16.
 */
public class MergeBottomUpSort implements SortStrategy {

    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        if (!SortStrategy.needsSorting(list)) return;
        List<T> aux = ArrayList.create(list.size());
        for (int i=1; i<list.size(); i *= 2) {
            for (int j=0; j<list.size() - i; j += 2*i) {
                merge(list, j, j + i - 1, Math.min(j + i + i - 1, list.size() - 1), aux);
            }
        }
    }

    private <T extends Comparable<? super T>> void merge(List<T> list, int start, int mid, int end, List<T> aux) {
        //algs4 improvement
        if (list.get(mid).compareTo(list.get(mid+1)) <= 0) return;
        aux.fill(list, start, end);
        int i = start; int j = mid+1;
        for (int k=start; k<=end; k++) {
            if (i > mid)                { list.set(k, aux.get(j)); j++;}
            else if (j > end)           { list.set(k, aux.get(i)); i++;}
            else if (aux.get(i).compareTo(aux.get(j)) < 0) { list.set(k, aux.get(i)); i++;}
            else { list.set(k, aux.get(j)); j++;}
        }
    }
}
