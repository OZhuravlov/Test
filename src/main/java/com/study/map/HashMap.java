package com.study.map;

import java.util.*;

import static java.lang.Math.abs;

public class HashMap<K, V> implements Map<K, V>, Iterable<Map.Entry<K, V>> {

    private final static int DEFAULT_INITIAL_BUCKETS_QUANTITY = 3;
    private final static double DEFAULT_LOAD_FACTOR = 0.5;
    private List<Map.Entry<K, V>>[] buckets;
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
        buckets = (List<Map.Entry<K, V>>[]) new List[bucketsQuantity];
    }

    // Both key and value could be null
    @Override
    public V put(K key, V value) {
        checkAndGrow();
        int index = getIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new ArrayList<>();
        }
        List bucket = buckets[index];
        if (!containsKey(key)) {
            bucket.add(new Entry(key, value));
            size++;
            return null;
        }
        Map.Entry<K, V> entry = getEntry(key, bucket);
        V oldValue = entry.getValue();
        entry.setValue(value);
        return oldValue;
    }

    @Override
    public void putAll(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map
                ) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if (!containsKey(key)) {
            return put(key, value);
        }
        return get(key);
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }
        int index = getIndex(key);
        List<Map.Entry<K, V>> bucket = buckets[index];
        Map.Entry<K, V> entry = getEntry(key, bucket);
        return entry.getValue();
    }

    @Override
    public V remove(K key) {
        int index = getIndex(key);
        List<Map.Entry<K, V>> bucket = buckets[index];
        Iterator<Map.Entry<K, V>> bucketIterator = bucket.iterator();
        while (bucketIterator.hasNext()) {
            Map.Entry<K, V> entry = bucketIterator.next();
            if ((key == null && entry.getKey() == null) || Objects.equals(key, entry.getKey())) {
                bucketIterator.remove();
                size--;
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        int index = getIndex(key);
        List<Map.Entry<K, V>> bucket = buckets[index];
        return getEntry(key, bucket) != null;
    }

    private void checkAndGrow() {
        if (size <= loadFactor * buckets.length) {
            return;
        }
        int newBucketsCapacity = buckets.length * 2;
        List<Map.Entry<K, V>>[] newBuckets = (List<Map.Entry<K, V>>[]) new List[newBucketsCapacity];
        for (Map.Entry<K, V> entry : this) {
            int newBucketIndex = (entry.getKey() == null) ? 0 : abs(entry.getKey().hashCode() % newBucketsCapacity);
            if (newBuckets[newBucketIndex] == null) {
                newBuckets[newBucketIndex] = new ArrayList();
            }
            newBuckets[newBucketIndex].add(entry);
        }
        buckets = newBuckets;
    }

    private int getIndex(K key) {
        return (key == null) ? 0 : abs(key.hashCode() % buckets.length);
    }

    private Map.Entry getEntry(K key, List<Map.Entry<K, V>> bucket) {
        if (bucket == null) {
            return null;
        }
        for (Map.Entry<K, V> entry : bucket) {
            if ((key == null && entry.getKey() == null) || Objects.equals(entry.getKey(), key)) {
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

    public static class Entry<K, V> implements Map.Entry<K, V> {

        private K key;
        private V value;

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Entry(K key, V value) {
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