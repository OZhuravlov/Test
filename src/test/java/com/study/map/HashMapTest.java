package com.study.map;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HashMapTest {
    @Test
    public void testPutAndGet() {
        Map<String, String> map = new HashMap<>();
        assertNull(map.put("user", "Ann"));
        assertEquals("Ann", map.put("user", "john"));
        assertEquals(1, map.size());
    }

    @Test
    public void testPutAndGetNullKey() {
        Map<String, String> map = new HashMap<>();
        assertNull(map.put(null, "john"));
        assertEquals(1, map.size());
        assertEquals("john", map.get(null));
        assertEquals("john", map.put(null, "Ann"));
        assertEquals(1, map.size());
        assertEquals("Ann", map.get(null));
    }

    @Test
    public void testPutAndGetNullValue() {
        Map<String, String> map = new HashMap<>();
        assertNull(map.put("user", null));
        assertEquals(1, map.size());
        assertNull(map.get("user"));
    }

    @Test
    public void testPutIfAbsentAndGet() {
        Map<String, String> map = new HashMap<>();
        assertNull(map.put("user", "Kate"));
        assertEquals(1, map.size());
        assertEquals("Kate", map.putIfAbsent("user", "Sam"));
        assertEquals(1, map.size());
        assertEquals("Kate", map.get("user"));
        assertNull(map.putIfAbsent("password", "test"));
        assertEquals("test", map.get("password"));
        assertEquals(2, map.size());
    }

    @Test
    public void testPutAll() {
        Map<String, String> map = new HashMap<>();
        Map map2 = new HashMap();

        map.put("user", "john");
        map.put("password", "test");

        map2.put("user", "Ann");
        map2.put("email", "test@test.com");

        map.putAll(map2);

        assertEquals(3, map.size());
        assertEquals("Ann", map.get("user"));
        assertEquals("test", map.get("password"));
        assertEquals("test@test.com", map.get("email"));

    }

    @Test
    public void testPutAndRemove() {
        Map<String, String> map = new HashMap<>();
        map.put("password", "test");
        assertEquals("test", map.remove("password"));
        assertEquals(0, map.size());
    }

    @Test
    public void testIterator() {
        Map<String, String> hashMap = new HashMap<>();
        List<String> expectedArray = new ArrayList<>();
        expectedArray.add("A");
        expectedArray.add("B");
        expectedArray.add("C");
        for (String key : expectedArray) {
            hashMap.put(key, "value " + key);
        }
        List array = new ArrayList();
        for (Map.Entry<String, String> entry : hashMap) {
            array.add(entry.getKey());
        }
        assertEquals(hashMap.size(), array.size());
        for (String element: expectedArray) {
            assertTrue(array.contains(element));
        }
    }

    @Test
    public void testCheckAndGrow() {
        Map<String, String> hashMap = new HashMap<>();
        List<String> expectedArray = new ArrayList<>();
        expectedArray.add("A");
        expectedArray.add("B");

        for (String key : expectedArray) {
            hashMap.put(key, "value " + key);
        }
        assertEquals(3, hashMap.getBucketCount());

        expectedArray.add("D");
        hashMap.put("D", "value D" );
        assertEquals(6, hashMap.getBucketCount());

        List<String> array = new ArrayList<>();
        for (Map.Entry<String, String> entry : hashMap) {
            array.add(entry.getKey());
        }

        assertEquals(hashMap.size(), array.size());
        for (String element: expectedArray) {
            assertTrue(array.contains(element));
        }
    }

}