package me.algoli.me.algoli.test;

import me.algoli.list.ArrayList;
import me.algoli.list.List;
import me.algoli.sort.MergeSort;
import me.algoli.sort.SortStrategy;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertEquals;

/**
 * Created by aalrashe on 4/7/2016.
 */
public class MergeSortTest {
    private SortStrategy sortStrategy = new MergeSort();

    @Test
    public void nullTest() {
        sortStrategy.sort(null);
    }

    @Test
    public void emptyTest() {
        List<Integer> list = ArrayList.<Integer>create();
        sortStrategy.sort(list);
    }

    @Test
    public void oneTest() {
        List<Integer> list = ArrayList.<Integer>create();
        list.append(1);
        sortStrategy.sort(list);
        assertEquals((int) list.get(0), 1);
    }

    @Test
    public void alreadySortedTest() {
        List<Integer> list = ArrayList.<Integer>create();
        for (int i = 0; i <= 10; i++) {
            list.append(i);
        }
        sortStrategy.sort(list);
        SortStrategy.isSorted(list);
    }

    @Test
    public void reverseSortedTest() {
        List<Integer> list = ArrayList.<Integer>create();
        for (int i = 0; i <= 10; i++) {
            list.prepend(i);
        }
        sortStrategy.sort(list);
        SortStrategy.isSorted(list);
    }

    @Test
    public void switchTwoTest() {
        List<Integer> list = ArrayList.<Integer>create();
        for (int i=1; i<=10; i++) {
            if (i%2 == 0) list.append(i - 1);
            else list.append(i + 1);
        }
        sortStrategy.sort(list);
        SortStrategy.isSorted(list);
    }

    @Test
    public void randomTest() {
        List<Integer> list = ArrayList.<Integer>create();
        Random rand = new Random(77);
        for (int i=0; i<10; i++) {
            list.append(rand.nextInt());
        }
        sortStrategy.sort(list);
        SortStrategy.isSorted(list);
    }
}