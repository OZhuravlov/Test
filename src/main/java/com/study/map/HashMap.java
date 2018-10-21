package com.study.map;

import java.util.*;

import static java.lang.Math.abs;

public class HashMap implements Map, Iterable {

    private final static int DEFAULT_INITIAL_BUCKETS_QUANTITY = 3;
    private final static double DEFAULT_LOAD_FACTOR = 0.5;
    private List[] buckets;
    private int size;

    private double loadFactor;

    public HashMap() {
        this(DEFAULT_INITIAL_BUCKETS_QUANTITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int bucketsQuantity, double loadFactor) {
        if (bucketsQuantity <= 0) {
            throw new IllegalArgumentException("Initial buckets must be > 0");
        }
        if (loadFactor <= 0 || loadFactor >= 1) {
            throw new IllegalArgumentException("loadFactor must be in range(0 ; 1]");
        }
        this.loadFactor = loadFactor;
        buckets = new List[bucketsQuantity];
    }

    // Both key and value could be null
    @Override
    public Object put(Object key, Object value) {
        checkAndGrow();
        int index = getIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new ArrayList();
        }
        List bucket = buckets[index];
        if (!containsKey(key)) {
            bucket.add(new Entry(key, value));
            size++;
            return null;
        }
        Entry entry = getEntry(key, bucket);
        Object oldValue = entry.value;
        entry.value = value;
        return oldValue;
    }

    @Override
    public void putAll(Map map) {
        for (Object entryObject : map
        ) {
            Entry entry = (Entry) entryObject;
            put(entry.key, entry.value);
        }
    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
        if (!containsKey(key)) {
            return put(key, value);
        }
        return get(key);
    }

    @Override
    public Object get(Object key) {
        if (!containsKey(key)) {
            return null;
        }
        int index = getIndex(key);
        List bucket = buckets[index];
        Entry entry = getEntry(key, bucket);
        return entry.value;
    }

    @Override
    public Object remove(Object key) {
        int index = getIndex(key);
        List bucket = buckets[index];
        Iterator bucketIterator = bucket.iterator();
        while (bucketIterator.hasNext()) {
            Entry entry = (Entry) bucketIterator.next();
            if ((key == null && entry.key == null) || Objects.equals(key, entry.key)) {
                bucketIterator.remove();
                size--;
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = getIndex(key);
        List bucket = buckets[index];
        return getEntry(key, bucket) != null;
    }

    private void checkAndGrow() {
        if (size <= loadFactor * buckets.length) {
            return;
        }
        int newBucketsCapacity = buckets.length * 2;
        List[] newBuckets = new List[newBucketsCapacity];
        for (Object entryObj : this) {
            Entry entry = (Entry) entryObj;
            int newBucketIndex = (entry.key == null) ? 0 : abs(entry.key.hashCode() % newBucketsCapacity);
            if (newBuckets[newBucketIndex] == null) {
                newBuckets[newBucketIndex] = new ArrayList();
            }
            newBuckets[newBucketIndex].add(entry);
        }
        buckets = newBuckets;
    }

    private int getIndex(Object key) {
        return (key == null) ? 0 : abs(key.hashCode() % buckets.length);
    }

    private Entry getEntry(Object key, List bucket) {
        if (bucket == null) {
            return null;
        }
        for (Object entryObject : bucket) {
            Entry entry = (Entry) entryObject;
            if ((key == null && entry.key == null) || Objects.equals(entry.key, key)) {
                return entry;
            }

        }
        return null;
    }

    public int getBucketCount() {
        return buckets.length;
    }

    public double getLoadFactor() {
        return loadFactor;
    }

    public void setLoadFactor(double loadFactor) {
        this.loadFactor = loadFactor;
    }

    @Override
    public Iterator iterator() {
        return new MyKeyIterator();
    }

    public static class Entry {

        private Object key;
        private Object value;

        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    private class MyKeyIterator implements Iterator {
        int currentBucketIndex;
        Iterator bucketIterator = buckets[currentBucketIndex].iterator();
        int count;

        @Override
        public boolean hasNext() {
            return count != size;
        }

        @Override
        public Object next() {
            if (count == size) {
                throw new NoSuchElementException();
            }
            boolean hasNext = bucketIterator.hasNext();
            if (!hasNext) {
                currentBucketIndex++;
                while (buckets[currentBucketIndex] == null) {
                    currentBucketIndex++;
                }
                bucketIterator = buckets[currentBucketIndex].iterator();
            }
            count++;
            return bucketIterator.next();
        }

    }
}