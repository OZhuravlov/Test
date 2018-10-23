package com.study.list;

import java.util.Iterator;
import java.util.StringJoiner;

public class ArrayList<E> implements List<E>, Iterable<E> {

    private E[] array;
    private int size = 0;

    public ArrayList() {
        array = (E[]) new Object[10];
    }

    @Override
    public void add(E value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    @Override
    public void add(E value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        ensureCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E value = array[index];
        if(index < size){
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        array[size - 1] = null;
        size--;
        return value;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public E set(E value, int index) {
        checkIndex(index);
        array[index] = value;
        return array[index];
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if(array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E value) {
        for (int i = size-1; i >= 0; i--) {
            if(array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndex(int index) {
        if(index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void ensureCapacity() {
        if(size == array.length){
            E[] newArray = (E[]) new Object[array.length * 3 / 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "ArrayList [", "]");

        for (E element : this) {
            stringJoiner.add(element.toString());
        }
        return stringJoiner.toString();
    }

    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator {
        int i = 0;

        @Override
        public boolean hasNext() {
            if (size == 0 || i >= size) {
                return false;
            }
            return true;
        }

        @Override
        public E next() {
            return array[i++];
        }
    }
}