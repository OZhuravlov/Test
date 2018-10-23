package com.study.map;

public interface Map<K,V> extends Iterable<Map.Entry<K, V>> {
    V put(K key, V value);

    void putAll(Map<K, V> map);

    V putIfAbsent(K key, V value);

    V get(K key);

    V remove(K key);

    int size();

    boolean containsKey(K key);

    int getBucketCount();


    interface Entry<K, V>{

        K getKey();

        V getValue();

        void setValue(V value);

    }
}
