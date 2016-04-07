package me.algoli.functions;

import me.algoli.list.List;

import java.util.Random;

/**
 * Created by ammar on 2/22/16.
 */
public class Shuffle {

    private Shuffle() {};

    public static <T> void knuthShuffle(List<Comparable<T>> list) {
        Random rand = new Random(100);
        for (int i = 1; i < list.size(); i++) {
            int random = rand.nextInt(i + 1);
            list.exchange(i, random);
        }
    }
}
