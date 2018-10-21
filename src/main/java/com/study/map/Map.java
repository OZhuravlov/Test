package com.study.map;

public interface Map extends Iterable {
    Object put(Object key, Object value);

    void putAll(Map map);

    Object putIfAbsent(Object key, Object value);

    Object get(Object key);

    Object remove(Object key);

    int size();

    boolean containsKey(Object key);
}