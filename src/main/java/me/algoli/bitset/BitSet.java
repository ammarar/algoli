package me.algoli.bitset;

import java.util.Arrays;

/**
 * Created by ammar on 1/9/16.
 */
public class BitSet {

    private final int UNIT_SIZE = 64;
//    private final int BIT_MASK = 1  (UNIT_SIZE - 1);

    long[] values = null;
    int usedLength = 0;

    private BitSet() {
        this.values = new long[1];
    }

    private BitSet(int nbits) {
        int numberOfUnits = (int) Math.ceil((double)nbits/UNIT_SIZE);
        values = new long[numberOfUnits];
        usedLength = numberOfUnits;
    }

    private BitSet(long[] newVals, int length) {
        this.values = Arrays.copyOf(newVals, length);
        this.usedLength = length;
    }

    public static BitSet create() {
        return new BitSet();
    }

    public static BitSet create(int nbits) {
        return new BitSet(nbits);
    }

    public static BitSet create(long[] newVals, int length) {
        return new BitSet(newVals, length);
    }

    public BitSet and(BitSet set) {
        return null;
    }

    public BitSet or(BitSet set) {
        return null;
    }

    public BitSet xor(BitSet set) {
        return null;
    }

    public BitSet clear(int index) {
        return null;
    }

    public BitSet clear(int from, int to) {
        return null;
    }

    public BitSet toggle(int index) {
        return null;
    }

    public BitSet toggle(int from, int to) {
        return null;
    }

    public boolean get(int index) {
        assert index >= 0 : String.format("index [%s] has to larger than or equal to 0", index);
        assert index <= size() : String.format("index [%s] is greater than the size [%s]", index, size());
        int arrayIndex = index / UNIT_SIZE;
        int indexInside = index % UNIT_SIZE;
        boolean result = ((values[arrayIndex] >> indexInside) & 1) == 1;
        return result;
    }

    public BitSet get(int from, int to) {
        return null;
    }

    public BitSet set(int index) {
        assert index >= 0 : String.format("index [%s] has to larger than or equal to 0", index);
        assert index <= size() : String.format("index [%s] is greater than the size [%s]", index, size());
        int arrayIndex = index / UNIT_SIZE;
        int indexInside = index % UNIT_SIZE;
        long[] newVals = Arrays.copyOf(values, values.length);
        newVals[arrayIndex] = newVals[arrayIndex] | (0x1 << indexInside);
        return create(newVals, newVals.length);
    }

    public BitSet set(int index, boolean value) {
        return null;
    }

    public BitSet set(int from, int to, boolean value) {
        return null;
    }

    public int length() {
        return usedLength;
    }

    public int size() {
        return values.length * UNIT_SIZE;
    }
}
