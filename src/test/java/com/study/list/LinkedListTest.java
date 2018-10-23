package com.study.list;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LinkedListTest {
    @Test
    public void testAddAndGetAndRemove() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");

        assertEquals(3, linkedList.size());

        assertEquals("B", linkedList.get(1));
        assertEquals("C", linkedList.get(2));
        assertEquals("B", linkedList.remove(1));
        assertEquals("C", linkedList.get(1));
        assertEquals("C", linkedList.remove(1));
        assertEquals("A", linkedList.remove(0));
        assertEquals(0, linkedList.size());
        linkedList.add("D");

        assertEquals(1, linkedList.size());
        assertEquals("D", linkedList.remove(0));
        assertEquals(0, linkedList.size());
    }

    @Test
    public void testAddAndGetByIndex() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");

        assertEquals("A", linkedList.get(0));
        linkedList.add("D", 0); // D A B C
        assertEquals("D", linkedList.get(0));
        linkedList.add("E", 4); // D A B C E
        assertEquals("E", linkedList.get(4));
        linkedList.add("F", 2); // D A F B C E
        assertEquals("F", linkedList.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveNonExistingElement() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.remove(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOnEmptyList() {
        List<String> linkedList = new LinkedList<>();
        linkedList.remove(0);
    }

    @Test
    public void testAddAndSet() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        assertEquals("AA", linkedList.set("AA", 0));
        linkedList.set("BB", 1);
        assertEquals("BB", linkedList.get(1));
        assertEquals("CC", linkedList.set("CC", 2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testClear() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        assertEquals(3, linkedList.size());
        linkedList.clear();
        assertEquals(0, linkedList.size());
        linkedList.get(0);
    }

    @Test
    public void testIsEmply() {
        List<String> linkedList = new LinkedList<>();
        assertTrue("isEmpty", linkedList.isEmpty());
        linkedList.add("A");
        assertFalse("isNotEmpty", linkedList.isEmpty());
    }

    @Test
    public void testContains() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");

        assertTrue("ContainsTrue", linkedList.contains("C"));
        assertTrue("ContainsTrue", linkedList.contains("A"));
        assertFalse("ContainsFalse", linkedList.contains("D"));
    }

    @Test
    public void testIndexOf() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("B");
        linkedList.add("C");

        assertEquals(1, linkedList.indexOf("B"));
        assertEquals(-1, linkedList.indexOf("D"));
    }

    @Test
    public void testLastIndexOf() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("B");
        linkedList.add("C");

        assertEquals(2, linkedList.lastIndexOf("B"));
        assertEquals(-1, linkedList.lastIndexOf("D"));
    }

    @Test
    public void testToString() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");

        assertEquals("LinkedList [A, B, C]", linkedList.toString());
    }

    @Test
    public void testIteration() {
        List<String> linkedList = new LinkedList<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");
        linkedList.add("E");

        StringBuilder builder = new StringBuilder();
        for (Object value:linkedList){
            builder.append(value);
        }
        assertEquals("ABCDE", builder.toString());
    }
}