package com.study.list;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayListTest {
    @Test
    public void testAddAndGetAndRemove() {
        List arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        assertEquals(3, arrayList.size());

        assertEquals("B", arrayList.get(1));
        assertEquals("C", arrayList.get(2));
        assertEquals("B", arrayList.remove(1));
        assertEquals("C", arrayList.get(1));
        assertEquals("C", arrayList.remove(1));
        assertEquals("A", arrayList.remove(0));
        assertEquals(0, arrayList.size());
        arrayList.add("D");

        assertEquals(1, arrayList.size());
        assertEquals("D", arrayList.remove(0));
        assertEquals(0, arrayList.size());
    }

    @Test
    public void testAddAndGetByIndex() {
        List arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        assertEquals("A", arrayList.get(0));
        arrayList.add("D", 0); // D A B C
        assertEquals("D", arrayList.get(0));
        arrayList.add("E", 4); // D A B C E
        assertEquals("E", arrayList.get(4));
        arrayList.add("F", 2); // D A F B C E
        assertEquals("F", arrayList.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveNonExistingElement() {
        List arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.remove(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOnEmptyList() {
        List arrayList = new ArrayList();
        arrayList.remove(0);
    }

    @Test
    public void testAddAndSet() {
        List arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        assertEquals("AA", arrayList.set("AA", 0));
        arrayList.set("BB", 1);
        assertEquals("BB", arrayList.get(1));
        assertEquals("CC", arrayList.set("CC", 2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testClear() {
        List arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        assertEquals(3, arrayList.size());
        arrayList.clear();
        assertEquals(0, arrayList.size());
        arrayList.get(0);
    }

    @Test
    public void testIsEmply() {
        List arrayList = new ArrayList();
        assertTrue("isEmpty", arrayList.isEmpty());
        arrayList.add("A");
        assertFalse("isNotEmpty", arrayList.isEmpty());
    }

    @Test
    public void testContains() {
        List arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        assertTrue("ContainsTrue", arrayList.contains("C"));
        assertTrue("ContainsTrue", arrayList.contains("A"));
        assertFalse("ContainsFalse", arrayList.contains("D"));
    }

    @Test
    public void testIndexOf() {
        List arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("B");
        arrayList.add("C");

        assertEquals(1, arrayList.indexOf("B"));
        assertEquals(-1, arrayList.indexOf("D"));
    }

    @Test
    public void testLastIndexOf() {
        List arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("B");
        arrayList.add("C");

        assertEquals(2, arrayList.lastIndexOf("B"));
        assertEquals(-1, arrayList.lastIndexOf("D"));
    }

    @Test
    public void testToString() {
        List arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        assertEquals("ArrayList [A, B, C]", arrayList.toString());
    }

    @Test
    public void testIteration() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.add("D");
        arrayList.add("E");

        StringBuilder builder = new StringBuilder();
        for (Object value:arrayList){
            builder.append(value);
        }
        assertEquals("ABCDE", builder.toString());
    }
}