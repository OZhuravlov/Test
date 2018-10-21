package com.study.array;

public interface Queue {
    void enqueue(Object value);

    Object dequeue();

    Object peek();

    int size();
}