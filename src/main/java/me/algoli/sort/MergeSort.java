package me.algoli.sort;

import me.algoli.list.ArrayList;
import me.algoli.list.List;

public class MergeSort implements SortStrategy {

    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        if (!SortStrategy.needsSorting(list)) return;
        mergeSort(list, 0, list.size() - 1, ArrayList.create(list.size()));
    }

    private <T extends Comparable<? super T>> void mergeSort(List<T> list, int start, int end, ArrayList<T> aux) {
        if (start >= end) return;
        int mid = start + (end - start)/2;
        mergeSort(list, start, mid, aux);
        mergeSort(list, mid+1, end, aux);
        merge(list, start, mid, end, aux);
    }

    protected <T extends Comparable<? super T>> void merge(List<T> list, int start, int mid, int end, List<T> aux) {
        //algs4 improvement
        if (list.get(mid).compareTo(list.get(mid+1)) <= 0) return;
        aux.fill(list, start, end);
        int i = start; int j = mid+1;
        for (int k=start; k<=end; k++) {
            if (i > mid)                { list.set(k, aux.get(j)); j++;}
            else if (j > end)           { list.set(k, aux.get(i)); i++;}
            else if (aux.get(j).compareTo(aux.get(i)) < 0) { list.set(k, aux.get(j)); j++;}
            else { list.set(k, aux.get(i)); i++;}
        }
    }
}
