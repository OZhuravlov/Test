package com.study.array;

import java.util.NoSuchElementException;

public class ArrayQueue implements Queue {

    private Object[] array;
    private int shift = 0;
    private int size = 0;

    public ArrayQueue() {
        array = new Object[5];
    }

    @Override
    public void enqueue(Object value) {
        if (shift + size == array.length) {
           Object[] newArray = new Object[(shift + size) * 3 / 2];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[shift + i];
            }
            array = newArray;
            shift = 0;
        }
        array[size] = value;
        size++;
    }

    @Override
    public Object dequeue() {
        if (size == 0) {
            NoSuchElementException noSuchElementException = new NoSuchElementException();
            throw noSuchElementException;
        }
        Object value = array[shift];
        shift++;
        size--;
        return value;
    }

    @Override
    public Object peek() {
        if (size == 0) {
            NoSuchElementException noSuchElementException = new NoSuchElementException();
            throw noSuchElementException;
        }
        return array[shift];
    }

    @Override
    public int size() {
        return size;
    }
}
