package me.algoli.me.algoli.test;

import me.algoli.Iterator;
import me.algoli.list.ArrayList;
import me.algoli.list.List;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class ArrayListTest {

    public List<Integer> getList() {
        return ArrayList.create(5);
    }

    @Test
    public void appendTest() {
        List<Integer> list = getList();
        for (int i=0; i<=10; i++) {
            list.append(i);
        }

        int n = 0;
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) {
            assertEquals((int) iterator.next(), n);
            n++;
        }
    }

    @Test
    public void prependTest() {
        List<Integer> list = getList();
        for (int i=0; i<=10; i++) {
            list.prepend(i);
        }

        int n = 10;
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) {
            assertEquals((int) iterator.next(), n);
            n--;
        }
    }

    @Test
    public void insertTest() {
        List<Integer> list = getList();
        for (int i=1; i<=10; i = i + 2) {
            list.append(i);
        }

        for (int i=0; i<=10; i = i + 2) {
            list.insert(i, i);
        }

        int n = 0;
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) {
            assertEquals((int) iterator.next(), n);
            n++;
        }
    }

    @Test
    public void getTest() {
        List<Integer> list = getList();
        for (int i=0; i<=10; i++) {
            list.append(i);
        }

        for(int i=0; i<=10; i++) {
            assertEquals((int) list.get(i), i);
        }
    }

    @Test
    public void removeTest() {
        List<Integer> list = getList();
        for (int i=0; i<=10; i++) {
            list.append(i);
        }

        for(int i=0; i<=10; i=i+2) {
            list.remove(i);
        }

        int n = 1;
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) {
            assertEquals((int) iterator.next(), n);
            n = n + 2;
        }
    }

    @Test
    public void setTest() {
        List<Integer> list = getList();
        for (int i=0; i<=10; i++) {
            list.append(i);
        }

        for(int i=0; i<=10; i++) {
            list.set(i, i + 100);
            assertEquals((int) list.get(i), i + 100);
        }
    }

    @Test
    public void popTest() {
        List<Integer> list = getList();
        for (int i=0; i<=10; i++) {
            list.append(i);
        }

        for(int i=10; i<=0; i--) {
            assertEquals( i, (int) list.pop());
        }
    }

    @Test
    public void popAtTest() {
        List<Integer> list = getList();
        for (int i=0; i<=10; i++) {
            list.append(i);
        }

        for(int i=0; i<=5; i = i + 2) {
            assertEquals( i, (int) list.pop(i) - i/2);
        }
    }
}
