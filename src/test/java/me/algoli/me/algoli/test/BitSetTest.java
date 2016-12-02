package me.algoli.me.algoli.test;

import me.algoli.bitset.BitSet;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Created by ammar on 1/10/16.
 */
public class BitSetTest {

    @Test
    public void testCreate() throws Exception {
        BitSet bs = BitSet.create();
        assertTrue(bs.get(0) == false, "Is not null");
    }

    @Test
    public void testCreate1() throws Exception {
        BitSet bs = BitSet.create(200);
        assertEquals(bs.length(), 4);
    }

    @Test
    public void testCreate2() throws Exception {
        BitSet bs = BitSet.create(new long[] {7, 1}, 2);
    }

    @Test
    public void testAnd() throws Exception {
        BitSet bs1 = BitSet.create().set(0, true);
        BitSet bs2 = BitSet.create().set(0, false);
        assertFalse(bs1.and(bs2).get(0));
    }

    @Test
    public void testOr() throws Exception {
        BitSet bs1 = BitSet.create().set(0, true);
        BitSet bs2 = BitSet.create().set(0, false);
        assertTrue(bs1.or(bs2).get(0));
    }

    @Test
    public void testXor() throws Exception {
        BitSet bs1 = BitSet.create().set(0, true);
        BitSet bs2 = BitSet.create().set(0, false);
        assertTrue(bs1.xor(bs2).get(0));
    }

    @Test
    public void testClear() throws Exception {
        BitSet bs1 = BitSet.create().set(1, 5, true);
        assertFalse(bs1.clear(3).get(3));
    }

    @Test
    public void testClear1() throws Exception {
        BitSet bs1 = BitSet.create(90);
        assertFalse(bs1.set(0, 89, true).clear(1, 15).get(13));
    }

    @Test
    public void testToggle() throws Exception {
        BitSet toggle = BitSet.create(90).toggle(1);
        assertTrue(toggle.get(1));
        assertFalse(toggle.get(2));
    }

    @Test
    public void testToggle1() throws Exception {
        BitSet toggle = BitSet.create(90).toggle(1, 70);
        assertTrue(toggle.get(1));
        assertTrue(toggle.get(30));
        assertFalse(toggle.get(71));
    }

    @Test
    public void testGet() throws Exception {
        assertEquals(true, BitSet.create(10).set(2).get(2), "Not properly set");
    }

    @Test
    public void testGet1() throws Exception {
        BitSet bs1 = BitSet.create(90).set(2, 77, true).get(1, 5);
    }

    @Test
    public void testSet() throws Exception {

    }

    @Test
    public void testSet1() throws Exception {

    }

    @Test
    public void testSet2() throws Exception {

    }

    @Test
    public void testLength() throws Exception {

    }

    @Test
    public void testSize() throws Exception {

    }
}