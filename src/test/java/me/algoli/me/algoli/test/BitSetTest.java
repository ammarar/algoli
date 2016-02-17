package me.algoli.me.algoli.test;

import me.algoli.bitset.BitSet;
import org.testng.Assert;
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

    }

    @Test
    public void testOr() throws Exception {

    }

    @Test
    public void testXor() throws Exception {

    }

    @Test
    public void testClear() throws Exception {

    }

    @Test
    public void testClear1() throws Exception {

    }

    @Test
    public void testToggle() throws Exception {

    }

    @Test
    public void testToggle1() throws Exception {

    }

    @Test
    public void testGet() throws Exception {
        Assert.assertEquals(true, BitSet.create(10).set(2).get(2), "Not properly set");
    }

    @Test
    public void testGet1() throws Exception {

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